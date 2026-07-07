package com.schmoll.tlkw.utils;


import com.schmoll.tlkw.customer.impl.CustomerClientImpl;
import com.schmoll.tlkw.entity.PcDataCount;
import com.schmoll.tlkw.schmollMes.impl.SchmollClientImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.swing.*;
import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StaticClass {
    public  CustomerClientImpl customerClientImpl;
    public  SchmollClientImpl schmollClientImpl;
    public static String  lastEndTime;
    public static int numberOfRefuelings;
    public static double timeOfRefuelings;
    public static double runTimeForDay;
    public static Long pcDataCountId;
    public static String wipEntity;
    public static HashMap<String,Object> recipeMap = new HashMap<>();
    public static String lotID = "";
    public static HashMap<String,Object> dataMap = new HashMap<>();
    @Setter
    @Getter
    private static ApplicationContext applicationContext;

    public static ClsPropertySet mesAgentPro = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "mesAgent.pro");
    public static ClsPropertySet webSetPro = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "webSet.pro");

    // 2. 新增：定义一个单线程的后台线程池 (守护线程)
    // 这个线程池专门用来执行 SchmollClientImpl 的任务
    private static final ExecutorService SCHMOLL_EXECUTOR = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true); // 设置为守护线程，防止程序无法正常退出
        t.setName("Schmoll-Client-Thread"); // 设置线程名，方便排查日志
        return t;
    });
    private static Future<?> schmollTask;

    public StaticClass(ApplicationContext context) {
        customerClientImpl = context.getBean(CustomerClientImpl.class);
        List<PcDataCount> list = customerClientImpl.dataCountService.list();
        PcDataCount pcDataCount = list.get(0);
        pcDataCountId= pcDataCount.getId();
        schmollClientImpl = context.getBean(SchmollClientImpl.class);
        startSchmollClient(context);
// 2. 关键步骤：在启动前先重置状态！
        if (false) {
        schmollClientImpl.reset();
        SCHMOLL_EXECUTOR.submit(() -> {
            try {
                schmollClientImpl.connect(); // 启动业务逻辑
            } catch (Exception e) {
                log.error("Schmoll 客户端连接任务异常", e);
            }
        });
        }
    }
    // 4. 新增：提供一个优雅关闭线程池的方法 (在程序退出时调用)
    public static synchronized boolean startSchmollClient(ApplicationContext context) {
        final SchmollClientImpl schmollClientImpl = context.getBean(SchmollClientImpl.class);
        if (schmollClientImpl.isRunning()) {
            log.info("Schmoll client connection task is already running");
            return true;
        }

        if (schmollTask != null && !schmollTask.isDone()) {
            log.info("Schmoll client restart has been queued until the old task exits");
        }


        schmollTask = SCHMOLL_EXECUTOR.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    schmollClientImpl.reset();
                    schmollClientImpl.connect();
                } catch (Exception e) {
                    log.error("Schmoll client connection task failed", e);
                }
            }
        });
        return true;
    }

    public static void shutdownSchmollExecutor() {
        SCHMOLL_EXECUTOR.shutdown(); // 不再接受新任务
        try {
            // 等待已提交的任务在 5 秒内完成
            if (!SCHMOLL_EXECUTOR.awaitTermination(5, TimeUnit.SECONDS)) {
                SCHMOLL_EXECUTOR.shutdownNow(); // 强制关闭
            }
        } catch (InterruptedException e) {
            SCHMOLL_EXECUTOR.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    /**
     * @param values JPanel 添加多个 JLabel
     */
    public static void jpAddJl(Object... values) {
        JPanel jp = (JPanel) values[0];
        for (int i = 1; i < values.length; i ++) {
            try {
                JLabel jl = (JLabel) values[i];
                jp.add(jl);
            } catch (ClassCastException e) {  // 处理类型转换异常，可以打印错误日志或抛出运行时异常
//                System.err.println("参数类型转换错误: " + e.getMessage());
                throw new RuntimeException("参数类型不匹配", e);
            }
        }
    }


    // 3. 提供一个公共的静态方法来设置上下文（由 Spring 启动时调用）
    // 2. 提供一个公共的静态方法来获取上下文
    // 1. 定义一个静态变量来保存应用上下文



    /**
     * @param values  JPanel 添加多个 JTextField
     */
    public static void jpAddJf(Object... values) {
        JPanel jp = (JPanel) values[0];
        for (int i = 1; i < values.length; i ++) {
            try {
                JTextField jf = (JTextField) values[i];
                jp.add(jf);
            } catch (ClassCastException e) {  // 处理类型转换异常，可以打印错误日志或抛出运行时异常
//                System.err.println("参数类型转换错误: " + e.getMessage());
                throw new RuntimeException("参数类型不匹配", e);
            }
        }
    }

    /**
     * @param values  JPanel 添加多个 JButton
     */
    public static void jpAddBt(Object... values) {
        JPanel jp = (JPanel) values[0];
        for (int i = 1; i < values.length; i ++) {
            try {
                JButton jb = (JButton) values[i];
                jp.add(jb);
            } catch (ClassCastException e) {  // 处理类型转换异常，可以打印错误日志或抛出运行时异常
//                System.err.println("参数类型转换错误: " + e.getMessage());
                throw new RuntimeException("参数类型不匹配", e);
            }
        }
    }

    /**
     * @param obj 包含 "" 和 null
     */
    public static Boolean isNull(Object obj){
        boolean aNull = ObjectUtils.isEmpty(obj);
        if(aNull){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @param str 包含 "" 和 null
     */
    public static Boolean isNull(String str){
        boolean aNull = cn.hutool.core.util.StrUtil.isEmpty(str);
        if(aNull){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取服务器ip地址
     * */
    public static String getServerIp() {
// 获取操作系统类型
        String sysType = System.getProperties().getProperty("os.name");
        String ip;
        if (sysType.toLowerCase().startsWith("win")) {// 如果是Windows系统，获取本地IP地址
            String localIP = null;
            try {
                localIP = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (localIP != null) {
                return localIP;
            }
        } else {
            ip = getIpByEthNum("eth0"); //兼容Linux
            if (ip != null) {
                return ip;
            }
        }
        return "";
    }
    /**
     * 根据网络接口获取IP地址
     * @param ethNum 网络接口名，Linux下是eth0
     * @return
     */
    private static String getIpByEthNum(String ethNum) {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (ethNum.equals(netInterface.getName())) {
                    Enumeration addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = (InetAddress) addresses.nextElement();
                        if (ip != null) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {
        Object obj = "";
        Boolean aNull = isNull(obj);
        System.out.println(aNull);
    }
}
