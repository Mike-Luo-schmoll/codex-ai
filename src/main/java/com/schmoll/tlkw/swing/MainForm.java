package com.schmoll.tlkw.swing;


import com.schmoll.tlkw.entity.PcSignalStatus;
import com.schmoll.tlkw.service.PcSignalStatusService;
import com.schmoll.tlkw.swing.panel.*;
import com.schmoll.tlkw.utils.ClsPropertySet;
import com.schmoll.tlkw.utils.MidnightScheduler;
import com.schmoll.tlkw.utils.StaticClass;
import com.schmoll.tlkw.utils.TaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Component
@Lazy
public class MainForm extends JFrame {
    public static String version = "V1.0";//V3.3 imes设置标签大小写,配方标签. 3.5按钮自定义 3.6自动弹起，清除配发，3条消息  3.7切削路径转换 / \  4.3传感器数据
    // v4.2 20251120 JH:
    // Change G3Send.java.
    // 1. Add _016 MachineState in DataUpload Report.
    // 2. Limit _001 wipEntity to 8 digits.
    // 3. Remove _1005 upload part.

    public static String properties = System.getProperty("user.dir") + File.separator + "config" + File.separator + "application.properties";  //配置服务端
    public static ClsPropertySet clsPropertySet = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "mesAgent.pro");

    public static String DrillId = "ul4";
    public static String DrillType = "Schmoll Drill";
    public static String DrillIP = "127.0.0.1";
    public static String DrillPort = "8191";
    public static ClsPropertySet webSetPro = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "webSet.pro");
    public static String ClientIP = webSetPro.getPropertyStr("url", "");

    public static Color white = new Color(233, 240, 232);
    public static Color gray = new Color(186, 192, 185);
    public static Color purple = new Color(102, 102, 153);
    public static Color red = new Color(255, 0, 0);
    public static Color green = new Color(0, 255, 0);
    public static Color black = new Color(0, 0, 0);

    public static LightStatus CNCLight;
    public static LightStatus MESLight;

    public static JPanel upJP;
    public static JPanel middleJP;
    public static JPanel leftJP;
    public static JPanel rightJP;
    public static JPanel iMESJP;
    public static JPanel userJP;
    public static JPanel operationJP;
    public static JPanel g3JP;

    public static JLabel MESJL;
    public static JLabel CNCJL;
    public static JLabel serverPortJL;

    public static JLabel DrillIdJL;
    public static JLabel DrillTypeJL;
    public static JLabel LotJL;

    public static JTextField lotJF;  //工单号
    public static JTextField cardJF;  //卡号

    public static JTextField serverPort;  //设置服务端口
    public static JTextField imesDrillId;
    public static JTextField imesDrillType;
    public static JTextField imesDrillIP;
    public static JTextField imesDrillPort;
    public static JTextField imesClientIP;
    public static JTextField inAGV;
    public static JTextField outAGV;

    public static JTextField setUpload;  //设置标签
    public static JTextField setRecipe;
    public static JTextField TC;
    public static JTextField TV;
    public static JTextField Tzero;

    public static MyTable myTable;//配方列表
    public static JLabel prgTxt;
    public static JLabel cutTxt;

    public static JTextArea mesMtext;  //多行mes信息
    public static Boolean mesTxtShow = true;
    public static String[][] tableValueList;
    public static int sendTime = webSetPro.getPropertyInt("productionDataSendTime", 5);
    public static JScrollPane JSPMtext;  //滚动条
    public static JScrollPane JSPprg;
    public static JScrollPane JSPcut;
    public static JTextField imesDrillUrl;
    public static String imesDrillUrlStr = webSetPro.getPropertyStr("url", "http://127.0.0.1");
    public static JTextField heartbeatContentBody;
    public static JTextField heartbeatIPBody;
    public static JTextField heartbeatPortBody;
    public static JTextField heartbeatFrequencyBody;
    public static Boolean heartbeatStart;
    public static JTextField agvOutTime;
    public static JTextField imesDrillTimeOut;
    public static String heartbeatContent;
    public static double w_s80 = Double.parseDouble(StaticClass.mesAgentPro.getPropertyStr("DrillModel_w", "1.91"));  //s80与s50系数 w
    public static double h_s80 = Double.parseDouble(StaticClass.mesAgentPro.getPropertyStr("DrillModel_h", "1.55"));  //s80与s50系数 h
    private ScheduledFuture<?> deviceParamTaskFuture;
    private ScheduledFuture<?> checkEapSignalStatus;

    @Autowired
    PcSignalStatusService signalStatusService;

    public MainForm() {
//        this.DrillId =  clsPropertySet.getPropertyStr("DrillId", "ul4");
//        this.DrillType =  clsPropertySet.getPropertyStr("DrillType", "Schmoll Drill");
        DrillIP = clsPropertySet.getPropertyStr("ClientIP", "127.0.0.1");
        DrillPort = clsPropertySet.getPropertyStr("ClientPort", "8191");
//        this.ClientIP =  clsPropertySet.getPropertyStr("ClientIP", "127.0.0.1");

        setLayout(null);
        setUndecorated(true);
        setPreferredSize(new Dimension(1700, 900));
        setLocation(2, 50);
        setBackground(white);

        Container c = getContentPane();

        String strIPName = "";
        ClsPropertySet webSetPro = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "webSet.pro");
        try {
            InetAddress addr = InetAddress.getLocalHost();
            strIPName = version + "  " + webSetPro.getPropertyStr("eqpID", "MAC001") + "  " + addr.getHostAddress() + "  " +
                    StaticClass.getServerIp() + "  " + addr.getHostName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        upJP = getJP(upJP, strIPName, 0, 0, 891, 77);
        UpJP.upJPanel(upJP);
        c.add(upJP);

        middleJP = getJP(middleJP, "FUNCTION", 0, 77, 891, 51);
        MiddleJP.middleJPanel(middleJP);
        c.add(middleJP);

        leftJP = getJP(leftJP, "", 0, 128, 215, 447);
        LeftJP.leftJPanel(leftJP);
        leftJP.setVisible(false);
        c.add(leftJP);

        rightJP = getJP(rightJP, "", 215, 128, 676, 447);
        RightJP.rightJPanel(rightJP);
        rightJP.setVisible(false);
        c.add(rightJP);

        iMESJP = getJP(iMESJP, "", 0, 128, 891, 447);
        IMESJP.iMESJPanel(iMESJP);
        iMESJP.setVisible(false);
        c.add(iMESJP);

        // 1498 896 200 36
        new MidnightScheduler().start();

        operationJP = getJP(operationJP, "", 0, 128, 891, 447);
        OperationJP.operationJPanel(operationJP);
        operationJP.setVisible(false);
        c.add(operationJP);

        g3JP = getJP(g3JP, "", 0, 128, 891, 447);
        G3JP.g3JPanel(g3JP);
//        g3JP.setVisible(false);
        c.add(g3JP);

        userJP = getJP(userJP, "", 0, 128, 891, 447);
        UserJP.userJPanel(userJP);
        userJP.setVisible(false);
        c.add(userJP);

        setTitle(version);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//设置关闭方式
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //关闭窗体，默认机台掉线
                PcSignalStatus pcSignalStatus = new PcSignalStatus();
                pcSignalStatus.setSignalcode("POWER");
                pcSignalStatus.setSignaldes("设备停机");
                pcSignalStatus.setSignalvalue("0");
                pcSignalStatus.setCreatetime(new Date());
                signalStatusService.save(pcSignalStatus);
                System.exit(0);
            }
        });


//        new StaticClass();
        //数据上传
        startAllScheduledTasks();
        pack();
    }

    private ScheduledExecutorService scheduler;

    // 启动所有定时任务（使用当前 configPropertySet 中的值）
    public void startAllScheduledTasks() {
        // 确保先停止旧任务
        stopAllScheduledTasks();
        
        // 初始化线程池（只初始化一次）
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newScheduledThreadPool(2); // 2个任务，并发执行
        }


        ApplicationContext context = StaticClass.getApplicationContext();
        TaskManager bean = context.getBean(TaskManager.class);
        checkEapSignalStatus = scheduler.scheduleAtFixedRate(
                bean::checkEapSignal,
                0,
                5, // 来自 configPropertySet
                TimeUnit.SECONDS
        );

        // 设备参数任务
//        deviceParamTaskFuture = scheduler.scheduleAtFixedRate(
//                G3send::sendProduction,
//                0,
//                sendTime, // 来自 configPropertySet
//                TimeUnit.MINUTES
//        );
    }

    // 停止所有定时任务
    public void stopAllScheduledTasks() {

        if (deviceParamTaskFuture != null && !deviceParamTaskFuture.isCancelled()) {
            deviceParamTaskFuture.cancel(false);
            deviceParamTaskFuture = null;
        }
        if (checkEapSignalStatus != null && !checkEapSignalStatus.isCancelled()) {
            checkEapSignalStatus.cancel(false);
            checkEapSignalStatus = null;
        }

    }


    private JPanel getJP(JPanel jp, String title, int x, int y, int w, int h) {
        jp = new JPanel();
//        jp.setFont(new Font("宋体",Font.PLAIN,20));
//        jp.setBorder(new TitledBorder(new EtchedBorder(), title));
//        jp.setBackground(white);
        jp.setBounds((int) Math.round(x * w_s80), (int) Math.round(y * h_s80), (int) Math.round(w * w_s80), (int) Math.round(h * h_s80));
//        jp.setFont(new Font("宋体",Font.PLAIN,20));
        return jp;
    }

    /**
     * @param str       setText 设置：文本。
     * @param bounds    setBounds 是否设置：位置，大小。
     * @param underline setBorder 是否设置：下边框。
     */
    public static JLabel getJL(String str, Boolean bounds, int x, int y, int w, int h, Boolean underline) {
        JLabel jl = new JLabel(str);
        if (bounds) {
            jl.setBounds((int) Math.round(x * w_s80), (int) Math.round(y * h_s80), (int) Math.round(w * w_s80), (int) Math.round(h * h_s80));
        }
        if (underline) {
            jl.setBorder(null); // 移除默认边框
//            jl.setLayout(new BorderLayout()); // 使用BorderLayout
//            jl.setBorder(new MatteBorder(0, 0, 2, 0, java.awt.Color.gray)); // 添加下划线边框
        }
        return jl;
    }

    public static JTextField getJF(String str, int x, int y, int w, int h) {
        JTextField jf = new JTextField(str);
        jf.setBounds((int) Math.round(x * w_s80), (int) Math.round(y * h_s80), (int) Math.round(w * w_s80), (int) Math.round(h * h_s80));
//        jf.setBorder(null); // 移除默认边框
//        jf.setLayout(new BorderLayout()); // 使用BorderLayout
//        jf.setBorder(new MatteBorder(0, 0, 0, 0, java.awt.Color.gray)); // 添加下划线边框
        return jf;
    }


    public static JScrollPane getJSP(JScrollPane JSP, int x, int y, int w, int h) {
        JSP = new JScrollPane();//滚动条
        JSP.setBounds((int) Math.round(x * w_s80), (int) Math.round(y * h_s80), (int) Math.round(w * w_s80), (int) Math.round(h * h_s80));
        return JSP;
    }

    public static JButton getBT(String str, int x, int y, int w, int h) {
        JButton jButton = new JButton(str);
//        jButton.setBackground(new Color(233, 240, 232));
//        jButton.setBorder(new EmptyBorder(5, 30, 5, 5));
//        jButton.setBorder(BorderFactory.createRaisedBevelBorder());
        jButton.setBounds((int) Math.round(x * w_s80), (int) Math.round(y * h_s80), (int) Math.round(w * w_s80), (int) Math.round(h * h_s80));

//        jButton.setBackground(MainForm.white);
        return jButton;
    }

    public static JLabel getRim(String str, Boolean bounds, int x, int y, int w, int h, Boolean underline) {
        JLabel jl = new JLabel(str);
        if (bounds) {
            jl.setBounds(x, y, w, h);
        }
        if (underline) {
            jl.setBorder(null); // 移除默认边框
            jl.setLayout(new BorderLayout()); // 使用BorderLayout
            jl.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray)); // 添加下划线边框
        }
        jl.setBorder(null); // 移除默认边框
        jl.setLayout(new BorderLayout()); // 使用BorderLayout
        jl.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray)); // 添加边框
        return jl;
    }

    public static boolean containsChineseCharacters(String input) {
        String regex = ".*[\\u4e00-\\u9fa5]+.*"; // 正则表达式匹配包含中文字符的字符串
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static void rewriteConfig(String newValue) {     //重写整个文件
        try (FileWriter writer = new FileWriter(properties)) {
            // 修改配置文件的内容，这里假设你想更新server.port
            writer.write("server.port=" + newValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String serverPort() {
        Properties prop = new Properties();
        try {
            InputStream input = Files.newInputStream(Paths.get(properties));
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty("server.port");
    }

    public static void updateConfig(String newValue) {      //修改某个属性
        try {
            Properties prop = new Properties();
            InputStream input = Files.newInputStream(Paths.get(properties));
            prop.load(input);
            prop.setProperty("server.port", newValue);
            OutputStream output = Files.newOutputStream(Paths.get(properties));
            prop.store(output, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void savePort() {
        rewriteConfig(serverPort.getText());
        serverPortJL.setText(serverPort());
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
