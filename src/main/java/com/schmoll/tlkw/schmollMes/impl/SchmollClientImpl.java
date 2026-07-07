package com.schmoll.tlkw.schmollMes.impl;

import com.schmoll.tlkw.customer.impl.CustomerClientImpl;
import com.schmoll.tlkw.schmollMes.SchmollClient;
import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.utils.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

@Slf4j
@Component
public class SchmollClientImpl implements SchmollClient {

    private static final Charset SOCKET_CHARSET = Charset.forName("GBK");
    private static final long RECONNECT_DELAY_MS = 1000L;
    private static final int CONNECT_TIMEOUT_MS = 3000;

    public volatile boolean bRun = true;
    private volatile Thread runningThread;
    public Socket socket1;
    public PrintStream outStream;
    public BufferedReader in;
    public String address = "127.0.0.1";
    public int port = 8191;
    public String strFromMes = "";
    public InputStream input;
    private StringBuilder jobBuilder;
    private final Object connectionLock = new Object();

    public SchmollClientImpl() {
    }

    public void initConnect() {
        try {
            loadClientConfig();
            openConnection();
        } catch (Exception e) {
            log.error("SchmollClientImpl initConnect failed", e);
            closeConnection();
        }
    }

    @Override
    public void sendToMes(String StringIn) {
        log.info("receiver S80 message ====>{}", StringIn);
        ApplicationContext context = StaticClass.getApplicationContext();
        CustomerClientImpl bean = context.getBean(CustomerClientImpl.class);
        try {
            bean.send(StringIn);
        } catch (Exception e) {
            log.info("send出现问题{}", StringIn, e);
        }
    }

    /**
     * Send Message to S50.
     */
    public String send(String StringIn) {
        if (outStream == null) {
            log.warn("Cannot send message because socket output stream is not initialized");
            return null;
        }

        outStream.println(StringIn);
        if (outStream.checkError()) {
            log.warn("Failed to send message to S80, output stream has error");
            closeConnection();
        }
        return null;
    }

    /**
     * Received message from S50.
     */
    private void receiveDataLoop() {
        BufferedReader reader = in;
        if (reader == null) {
            log.warn("Cannot receive data because socket input stream is not initialized");
            closeConnection();
            return;
        }

        try {
            String line;
            while (bRun && (line = reader.readLine()) != null) {
                strFromMes += line + "\n";
                if ("SMDATEND".equals(line)) {
                    sendToMes(strFromMes);
                    strFromMes = "";
                }
            }

            if (bRun) {
                log.warn("S80 connection closed by remote endpoint");
                closeConnection();
                setClientLight(0);
            }
        } catch (IOException e) {
            if (bRun) {
                strFromMes = "";
                log.warn("S80 receive data stream was closed or disconnected", e);
                closeConnection();
                setClientLight(0);
            }
        } catch (RuntimeException e) {
            strFromMes = "";
            log.error("Unexpected exception while receiving S50 data", e);
            closeConnection();
            setClientLight(0);
        }
    }

    /**
     * Reset client state before reconnecting.
     */
    public void reset() {
        this.bRun = true;
        this.strFromMes = "";
        log.info("Schmoll client state has been reset, ready to reconnect");
    }

    public boolean isRunning() {
        Thread thread = runningThread;
        return bRun && thread != null && thread.isAlive();
    }

    /**
     * Connection task. It keeps reconnecting while bRun is true.
     */
    public void connect() {
        this.runningThread = Thread.currentThread();
        loadClientConfig();
        log.info("Schmoll client connection task started, current thread: {}", runningThread.getName());

        while (bRun) {
            try {
                ensureConnected();
                setClientLight(1);
                receiveDataLoop();
            } catch (IOException e) {
                if (bRun) {
                    log.error("S80 connection or stream initialization failed: {}", e.getMessage(), e);
                    closeConnection();
                    setClientLight(0);
                }
            }

            sleepBeforeReconnect();
        }

        closeConnection();
        log.info("Schmoll client connection loop ended");
    }

    /**
     * Stop connection handler.
     */
    public void destroy() {
        log.info("Stopping Schmoll connection handler and interrupting connection thread");
        bRun = false;

        if (runningThread != null && runningThread.isAlive()) {
            runningThread.interrupt();
            log.debug("Sent interrupt signal to thread: {}", runningThread.getName());
        }

        Thread closeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                closeConnection();
            }
        }, "SchmollClient-close-thread");
        closeThread.setDaemon(true);
        closeThread.start();

        log.info("Schmoll connection handler stop command has been issued");
    }

    /**
     * Change client light status.
     */
    public void setClientLight(int status) {
        if (MainForm.CNCLight == null) {
            log.warn("Cannot set CNC light because MainForm.CNCLight is null");
            return;
        }

        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            MainForm.CNCLight.setLight(status, 0, 0);
        } else {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    if (MainForm.CNCLight != null) {
                        MainForm.CNCLight.setLight(status, 0, 0);
                    }
                }
            });
        }
    }

    private void loadClientConfig() {
        this.address = MainForm.clsPropertySet.getPropertyStr("ClientIP", "127.0.0.1");
        this.port = MainForm.clsPropertySet.getPropertyInt("ClientPort", 8191);
    }

    private void ensureConnected() throws IOException {
        if (isConnectionReady()) {
            return;
        }

        closeConnection();
        openConnection();
    }

    private boolean isConnectionReady() {
        return socket1 != null
                && socket1.isConnected()
                && !socket1.isClosed()
                && in != null
                && outStream != null;
    }

    private void openConnection() throws IOException {
        synchronized (connectionLock) {
            log.info("Connecting to S80 socket {}:{}", address, port);
            socket1 = new Socket();
            socket1.connect(new InetSocketAddress(address, port), CONNECT_TIMEOUT_MS);
            outStream = new PrintStream(socket1.getOutputStream(), true, SOCKET_CHARSET.name());
            input = socket1.getInputStream();
            in = new BufferedReader(new InputStreamReader(input, SOCKET_CHARSET));
            log.info("S80 socket connected successfully");
        }
    }

    private void closeConnection() {
        synchronized (connectionLock) {
            closeReader();
            closeOutputStream();
            closeSocket();

            input = null;
            in = null;
            outStream = null;
            socket1 = null;
        }
    }

    private void closeReader() {
        if (in == null) {
            return;
        }

        try {
            in.close();
        } catch (IOException e) {
            log.warn("Failed to close socket input reader", e);
        }
    }

    private void closeOutputStream() {
        if (outStream != null) {
            outStream.close();
        }
    }

    private void closeSocket() {
        if (socket1 == null || socket1.isClosed()) {
            return;
        }

        try {
            socket1.close();
        } catch (IOException e) {
            log.warn("Failed to close socket", e);
        }
    }

    private void sleepBeforeReconnect() {
        if (!bRun) {
            return;
        }

        try {
            Thread.sleep(RECONNECT_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            if (!bRun) {
                return;
            }
            log.warn("Schmoll reconnect sleep was interrupted", e);
        }
    }
}
