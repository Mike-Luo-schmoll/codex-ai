package com.schmoll.tlkw.swing.panel;


import cn.hutool.core.util.StrUtil;
import com.schmoll.tlkw.TlkwApplication;
import com.schmoll.tlkw.entity.PcDataEmp;
import com.schmoll.tlkw.schmollMes.impl.SchmollClientImpl;
import com.schmoll.tlkw.service.PcDataEmpService;
import com.schmoll.tlkw.swing.LoginPage;
import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.swing.RecipeTable;
import com.schmoll.tlkw.utils.ClsPropertySet;
import com.schmoll.tlkw.utils.MyFileFilter;
import com.schmoll.tlkw.utils.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
public class G3JP {
    public static Boolean setFlag = true;

    public static Boolean gdqrTF = true;
    public static Boolean xfpfTF = true;
    public static Boolean jlTF = true;
    public static Boolean clTF = true;
    public static Boolean ckhjTF = true;
    public static Boolean jkhjTF = true;
    public static Boolean XYTF = false;

    public static JTextField work1;
    public static JTextField work2;
    public static JTextField work3;
    public static JTextField work4;
    public static JTextField work5;
    public static JTextField mociText;
    public static JTextField work6;
    public static JTextField wz;//物资编码
    public static JTextField gx;//工序代码
    public static JTextField cut;//切削
    public static JTextField prg;//配方
    public static JTextField userTxt; //登录用户名

    public static JLabel jl12;
    public static JLabel jl13;
    public static JLabel VXjf;
    public static JLabel VYjf;

    public static RecipeTable myTable;
    public static JTextArea mesMessageBody;

    public static JButton affirmBt; //确认
    public static JButton clearBt;
    public static JButton recipeBt;  //下发配方
    public static JButton completeBt;
    public static JButton modeBt;
    public static JButton logoutBt;
    public static JButton cutNameBt;
    public static JButton resetBt;
    public static JLabel inBtLabel;
    public static JButton inBt;//叫料
    public static JButton outBt;//出料
    public static JButton outShelfBt;//出空货架
    public static JButton inShelfBt;//叫空货架

//    public static JButton inBt1;//叫料1
//    public static JButton inBt2;//叫料2
//    public static JButton inBt3;//叫料3
//
//    public static JButton outBt1;//出料1
//    public static JButton outBt2;//出料2
//    public static JButton outBt3;//出料3
    public static JTextField jf01;
    public static JComboBox selectBox;


    public static String tempRecipe = "";

    private static final JFileChooser chooser = new JFileChooser("");

    public static void g3JPanel(JPanel jp) {
        ClsPropertySet cutPro = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "cutNameDataPath.pro");
        jp.setLayout(null);

        JLabel jl1 = MainForm.getJL("模式切换:", true, 30, 10, 80, 25, false);
        JLabel jl2 = MainForm.getJL("工单ID01:", true, 30, 40, 80, 25, false);
        JLabel jl4 = MainForm.getJL("工单ID03:", true, 30, 70, 80, 25, false);
        JLabel jl6 = MainForm.getJL("工单ID05:", true, 30, 100, 80, 25, false);
        JLabel moCi = MainForm.getJL("磨次:", true, 30, 130, 80, 25, false);
        JLabel jl3 = MainForm.getJL("工单ID02:", true, 300, 40, 80, 25, false);
        JLabel jl5 = MainForm.getJL("工单ID04:", true, 300, 70, 80, 25, false);
        JLabel jl7 = MainForm.getJL("工单ID06:", true, 300, 100, 80, 25, false);
        JLabel jl8 = MainForm.getJL("物资编码:", true, 30, 175, 80, 25, false);
        JLabel jl9 = MainForm.getJL("工序代码:", true, 300, 175, 80, 25, false);
        JLabel jl10 = MainForm.getJL("当前配方:", true, 30, 360, 80, 25, false);
        JLabel jl11 = MainForm.getJL("MES消息:", true, 600, 205, 70, 25, false);
        jl12 = MainForm.getJL("X:", true, 30, 390, 40, 25, false);
        jl13 = MainForm.getJL("Y:", true, 150, 390, 40, 25, false);

        work1 = MainForm.getJF("", 100, 40, 170, 25);
        work2 = MainForm.getJF("", 370, 40, 170, 25);
        work3 = MainForm.getJF("", 100, 70, 170, 25);
        work4 = MainForm.getJF("", 370, 70, 170, 25);
        work5 = MainForm.getJF("", 100, 100, 170, 25);
        mociText = MainForm.getJF("", 100, 130, 170, 25);
        work6 = MainForm.getJF("", 370, 100, 170, 25);
        wz = MainForm.getJF("", 100, 175, 170, 25);
        wz.setForeground(Color.DARK_GRAY);
        gx = MainForm.getJF("", 370, 175, 170, 25);
        gx.setForeground(Color.DARK_GRAY);
        cut = MainForm.getJF(cutPro.getPropertyStr("cutNamePath", ""), 100, 330, 440, 25);
        cut.setForeground(Color.DARK_GRAY);
//        cut.setVisible(false);
        prg = MainForm.getJF("", 100, 360, 440, 25);
        prg.setForeground(Color.DARK_GRAY);
        userTxt = MainForm.getJF("ul4", 380, 10, 60, 25);
        userTxt.setForeground(Color.DARK_GRAY);
        VXjf = MainForm.getJL("", true, 50, 390, 60, 25, false);
        VYjf = MainForm.getJL("", true, 170, 390, 60, 25, false);
        VXjf.setBorder(null); // 移除默认边框
        VXjf.setLayout(new BorderLayout()); // 使用BorderLayout
        VXjf.setBorder(new MatteBorder(2, 2, 2, 2, Color.gray)); // 添加边框
        VYjf.setBorder(null); // 移除默认边框
        VYjf.setLayout(new BorderLayout()); // 使用BorderLayout
        VYjf.setBorder(new MatteBorder(2, 2, 2, 2, Color.gray)); // 添加边框

        Boolean boolXY = Boolean.valueOf(IMESJP.XYzero);
        G3JP.VXjf.setVisible(boolXY);
        G3JP.VYjf.setVisible(boolXY);
        G3JP.jl12.setVisible(boolXY);
        G3JP.jl13.setVisible(boolXY);

        wz.setBackground(MainForm.gray);
        gx.setBackground(MainForm.gray);
        cut.setBackground(MainForm.gray);
        prg.setBackground(MainForm.gray);
        userTxt.setBackground(MainForm.gray);
        
//        wz.setEnabled(false);
//        gx.setEnabled(false);
//        wz.setEnabled(false);
//        cut.setEnabled(false);
//        prg.setEnabled(false);
//        userTxt.setEnabled(false);

        modeBt = MainForm.getBT("EAP模式", 120, 10, 60, 25);
        logoutBt = MainForm.getBT("下机", 470, 10, 60, 25);
        affirmBt = MainForm.getBT("确认", 380, 130, 60, 40);
        clearBt = MainForm.getBT("消除", 470, 130, 60, 40);
        cutNameBt = MainForm.getBT("切削资料", 20, 330, 70, 25);
        recipeBt = MainForm.getBT("下发配方", 290, 390, 80, 25);
        completeBt = MainForm.getBT("重置", 420, 390, 80, 25);
        completeBt.setToolTipText("当无法下发给机台配方时，或加载不完整时，点击重置，重新下配方。");
        inBt = MainForm.getBT("叫料", 630, 75, 80, 40);
        inBtLabel = MainForm.getJL("", true, 630, 100, 80, 40, false);
        outBt = MainForm.getBT("出料", 740, 75, 80, 40);
        outBt.setEnabled(false);
        outShelfBt = MainForm.getBT("退料", 630, 150, 80, 40);
        inShelfBt = MainForm.getBT("叫空货架", 740, 150, 80, 40);
        resetBt = MainForm.getBT("复位", 650, 390, 80, 25);
        resetBt.setVisible(false);

//        inBt.setVisible(false);
//        outBt.setVisible(false);
//        outShelfBt.setVisible(false);
//        inShelfBt.setVisible(false);

        //叫料1，2，3  出料1，2，3
//        inBt1 = MainForm.getBT("叫料1",610,10,80,40);
//        inBt2 = MainForm.getBT("叫料2",610,75,80,40);
//        inBt3 = MainForm.getBT("叫料3",610,140,80,40);
//
//        outBt1 = MainForm.getBT("出料1",750,10,80,40);
//        outBt2 = MainForm.getBT("出料2",750,75,80,40);
//        outBt3 = MainForm.getBT("出料3",750,140,80,40);

//        JLabel bk1 = MainForm.getJL( "",true, 590, 5, 260, 1,false);
//        JLabel bk2 = MainForm.getJL( "",true, 590, 55, 260, 1,false);
//        bk1.setBorder(null); // 移除默认边框
//        bk1.setLayout(new BorderLayout()); // 使用BorderLayout
//        bk1.setBorder(new MatteBorder(1, 1, 1, 1, java.awt.Color.gray)); // 添加边框
//        bk2.setBorder(null); // 移除默认边框
//        bk2.setLayout(new BorderLayout()); // 使用BorderLayout
//        bk2.setBorder(new MatteBorder(1, 1, 1, 1, java.awt.Color.gray)); // 添加边框
//
//        JLabel bk3 = MainForm.getJL( "",true, 590, 5, 1, 50,false);
//        JLabel bk4 = MainForm.getJL( "",true, 850, 5, 1, 50,false);
//        bk3.setBorder(null); // 移除默认边框
//        bk3.setLayout(new BorderLayout()); // 使用BorderLayout
//        bk3.setBorder(new MatteBorder(1, 1, 1, 1, java.awt.Color.gray)); // 添加边框
//        bk4.setBorder(null); // 移除默认边框
//        bk4.setLayout(new BorderLayout()); // 使用BorderLayout
//        bk4.setBorder(new MatteBorder(1, 1, 1, 1, java.awt.Color.gray)); // 添加边框

         jf01 = MainForm.getJF(MainForm.heartbeatContent, 630, 2, 80, 25);
        jf01.setBackground(Color.GREEN);
        jf01.setEnabled(false);


        selectBox = new JComboBox();
        selectBox.setVisible(true);
        selectBox.setBounds((int) Math.round( 630 * MainForm.w_s80), (int) Math.round( 30 * MainForm.h_s80), (int) Math.round( 140 * MainForm.w_s80), (int) Math.round( 30 * MainForm.h_s80));  //630, 30, 140, 30  (int) Math.round( x * w_s80), (int) Math.round( y * h_s80), (int) Math.round( w * w_s80), (int) Math.round( h * h_s80)

        myTable = new RecipeTable(prg);
        JScrollPane vv = myTable.getMyTable();
        vv.setBounds((int) Math.round( 25 * MainForm.w_s80), (int) Math.round(205 * MainForm.h_s80), (int) Math.round( 515 * MainForm.w_s80), (int) Math.round( 120 * MainForm.h_s80));  //25, 205, 515, 120

        mesMessageBody = new JTextArea("");
        mesMessageBody.setBounds((int) Math.round( 600 * MainForm.w_s80), (int) Math.round(230 * MainForm.h_s80), (int) Math.round(260 * MainForm.w_s80), (int) Math.round(150 * MainForm.h_s80));  //600, 230, 260, 150
        mesMessageBody.setBackground(new Color(254, 165, 0));
        mesMessageBody.setForeground(Color.BLACK);
        mesMessageBody.setLineWrap(true);
        mesMessageBody.setWrapStyleWord(true);

        StaticClass.jpAddJl(jp, jl1, jl2, jl3, jl4, jl5,moCi, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, VXjf, VYjf);//,bk1,bk2,bk3,bk4

        StaticClass.jpAddJf(jp, work1, work2, work3, work4, work5, mociText,work6, wz, gx, cut, prg, userTxt, jf01);  //jf01(SSEM)

        StaticClass.jpAddBt(jp, modeBt, logoutBt, affirmBt, clearBt, cutNameBt, recipeBt, completeBt, inBt, outBt,
                outShelfBt, resetBt);//,inBt1,inBt2,inBt3,outBt1,outBt2,outBt3

        jp.add(vv);
        jp.add(mesMessageBody);
        jp.add(selectBox);
        initListener();
    }
   public static Dialog dialog;
    private static void initListener() {

        modeBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (setFlag) {
                    modeBt.setText("本机模式");
                    setFlag = false;
                } else {
                    modeBt.setText("EAP模式");
                    setFlag = true;
                }
            }
        });

        logoutBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (handleLogout()) {
                    return;
                }
                int i = JOptionPane.showConfirmDialog(logoutBt, "确定下机吗？", "重启提示", 0);
                if (i == 0) {
                    ApplicationContext context = StaticClass.getApplicationContext();
                    PcDataEmpService   empService = context.getBean(PcDataEmpService.class);
// 2. 从容器里拿出 LoginPage 的实例（这个实例是有 Service 依赖注入的）
                    LoginPage loginPage = context.getBean(LoginPage.class);
                    empService.save(new PcDataEmp(null,LoginPage.userText.getText(),LoginPage.passwordText.getText(),"OUTTIME","下机时间",new Date(),new Date()));
                    LoginPage.passwordText.setText("");
                    LoginPage.userText.setText("");
                    loginPage.setVisible(true);
                    TlkwApplication.frame.setVisible(false); //注销
                    TlkwApplication.frame.stopAllScheduledTasks();
                    SchmollClientImpl bean = context.getBean(SchmollClientImpl.class);
                    bean.destroy();
                }
            }
        });

        affirmBt.addActionListener(e -> {  //确认
            // 创建一个模态对话框
            dialog = new JDialog((Frame) null, "提示", true);
            dialog.setLayout(new BorderLayout());
            dialog.setSize(300, 100);
            dialog.setLocationRelativeTo(null);

            // 添加提示信息
            JLabel label = new JLabel("正在等待服务器回应配方内容", JLabel.CENTER);
            dialog.add(label, BorderLayout.CENTER);

            // 添加滚动进度条
            JProgressBar progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);
            dialog.add(progressBar, BorderLayout.SOUTH);

            // 在新线程中显示对话框
            SwingUtilities.invokeLater(() -> dialog.setVisible(true));

            // 执行affirm方法
            new Thread(() -> {
                affirm();
                // 关闭对话框
                SwingUtilities.invokeLater(dialog::dispose);
            }).start();
        });

        clearBt.addActionListener(new ActionListener() {  //清除
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        cutNameBt.addActionListener(new ActionListener() {  //切削资料
            public void actionPerformed(ActionEvent e) {
                cutName();
            }
        });

        recipeBt.addActionListener(new ActionListener() {  //下发配方
            public void actionPerformed(ActionEvent e) {
                recipe();
            }
        });

        completeBt.addActionListener(new ActionListener() {  //重置
            public void actionPerformed(ActionEvent e) {
                complete();
            }
        });

        inBt.addActionListener(new ActionListener() {  //叫料
            public void actionPerformed(ActionEvent e) {
                inAGV();
            }
        });

        outBt.addActionListener(new ActionListener() {  //出料
            public void actionPerformed(ActionEvent e) {
                outAGV();
            }
        });

        outShelfBt.addActionListener(new ActionListener() {  //出空货架
            public void actionPerformed(ActionEvent e) {
                outShelfAGV();
            }
        });

        inShelfBt.addActionListener(new ActionListener() {  //叫空货架
            public void actionPerformed(ActionEvent e) {
                inShelfAGV();
            }
        });

        resetBt.addActionListener(new ActionListener() {  //复位
            public void actionPerformed(ActionEvent e) {
//                System.out.println(12);
            }
        });
    }

    private static boolean handleLogout() {
        int i = JOptionPane.showConfirmDialog(logoutBt, "确定下机吗？", "重启提示", 0);
        if (i != 0) {
            return true;
        }

        final ApplicationContext context = StaticClass.getApplicationContext();
        final String user = LoginPage.userText.getText();
        final String password = LoginPage.passwordText.getText();
        final LoginPage loginPage = context.getBean(LoginPage.class);

        LoginPage.passwordText.setText("");
        LoginPage.userText.setText("");
        loginPage.setVisible(true);
        if (TlkwApplication.frame != null) {
            TlkwApplication.frame.stopAllScheduledTasks();
            TlkwApplication.frame.setVisible(false);
            TlkwApplication.frame.dispose();
            TlkwApplication.frame = null;
        }

        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PcDataEmpService empService = context.getBean(PcDataEmpService.class);
                    empService.save(new PcDataEmp(null, user, password, "OUTTIME", "下机时间", new Date(), new Date()));
                } catch (Exception ex) {
                    log.error("Logout record save failed", ex);
                }

                try {
                    SchmollClientImpl bean = context.getBean(SchmollClientImpl.class);
                    bean.destroy();
                } catch (Exception ex) {
                    log.error("Schmoll client stop failed", ex);
                }
            }
        }, "Logout-Worker");
        worker.setDaemon(true);
        worker.start();
        return true;
    }

    private static void recipe() {  //下发配方
        selectBox.addItem(G3JP.work1.getText());
        ClsPropertySet set_cutNameDataPath = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "cutNameDataPath.pro");
        String cut = set_cutNameDataPath.getPropertyStr("cutNamePath", "");
//        String cutDrive = "";
//        String cutName = "";
//        if (!cut.isEmpty()) {
//            if (cut.contains("/")) {
//                int i = cut.lastIndexOf("/");
//                cutName = cut.substring(0, i + 1);
//                cutDrive = cut.substring(i + 1);
//            }
//        }

//        jobCallNull();

        String mes = JobCall_s80(prg.getText(), cut);
        log.info("JobCall====>{}",mes);
        ApplicationContext context = StaticClass.getApplicationContext();
        SchmollClientImpl schmollClientImpl = context.getBean(SchmollClientImpl.class);
       schmollClientImpl.send(mes);
    }

    private static void cutName() {
        chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
        chooser.addChoosableFileFilter(new MyFileFilter("null", "文件"));
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        cut.setText(file.getPath());
        ClsPropertySet set_cutNameDataPath = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "cutNameDataPath.pro");
        set_cutNameDataPath.setPropertyStr("cutNamePath", file.getPath());
        set_cutNameDataPath.saveProperties(); //保存到 cutNameDataPath.pro 文件
    }

    private static void complete() {
//        completeBt.setEnabled(false);
//         G3send.StitchingInterface(G3send.upload,G3send.jgwc);
//        StaticClass.schmollClientImpl.stop();
        try {
            Thread.sleep(500);
            ApplicationContext context = StaticClass.getApplicationContext();
            SchmollClientImpl bean = context.getBean(SchmollClientImpl.class);
            bean = new SchmollClientImpl();
//            StaticClass.schmollClientImpl.start();
            Thread.sleep(500);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static void jobCallNull() {  //清空

        String mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<schmoll_mes_message version=\"1\">\n" +
                " <cmd_type>Write</cmd_type>\n" +
                " <cmd_id>drill.JobCall</cmd_id>\n" +
                " <msg_result>0</msg_result>\n" +
                " <sender_id>MES Server</sender_id>\n" +
                " <receiver_id>" + "111" + "</receiver_id>\n" +
                " <data>\n" +
                " <param key=\"PrgName\" type=\"string\">" + "</param> \n" +
                " <param key=\"PrgDrive\" type=\"string\">" + "</param> \n" +   //  mnt/net1
                " <param key=\"CutName\" type=\"string\">" + "</param> \n" +
                " <param key=\"CutDrive\" type=\"string\">" + "</param> \n";
        mes = mes + " </data>\n" + "</schmoll_mes_message>";
        mes = "SMDATMSG,1,schmoll_mes_message,TlkwApplication/xml," + mes.length() + "\n" +
                mes + "\nSMDATEND";

//        StaticClass.schmollClientImpl.send(mes);
    }

    private static void jobCall(String PrgName, String cutName) {
        if (containsChineseCharacters(PrgName)) {
            JOptionPane.showMessageDialog(null, "配方路径不能包含中文,请重新获取");
            return;
        }
        String mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<schmoll_mes_message version=\"1\">\n" +
                " <cmd_type>Write</cmd_type>\n" +
                " <cmd_id>drill.JobCall</cmd_id>\n" +
                " <msg_result>0</msg_result>\n" +
                " <sender_id>MES Server</sender_id>\n" +
                " <receiver_id>" + "111" + "</receiver_id>\n" +
                " <data>\n" +
                " <param key=\"PrgName\" type=\"string\">" + PrgName + "</param> \n" +
                " <param key=\"PrgDrive\" type=\"string\">" + "N:" + "</param> \n" +   //  mnt/net1
                " <param key=\"CutName\" type=\"string\">" + cutName + "</param> \n" +
                " <param key=\"CutDrive\" type=\"string\">" + "N:" + "</param> \n";
//                    " <param key=\"VX\" type=\"string\">" + G3JP.VXjf.getText() + "</param> \n" +
//                    " <param key=\"VY\" type=\"string\">" + G3JP.VYjf.getText() + "</param> \n";
        mes = mes + " </data>\n" + "</schmoll_mes_message>";
        mes = "SMDATMSG,1,schmoll_mes_message,TlkwApplication/xml," + mes.length() + "\n" +
                mes + "\nSMDATEND";
//        StaticClass.schmollClientImpl.send(mes);
    }

    private static void jobCallXY(String PrgName, String PrgDrive, String cutName, String cutDrive) {
        if (containsChineseCharacters(PrgName)) {
            JOptionPane.showMessageDialog(null, "配方路径不能包含中文,请重新获取");
            return;
        }
//        System.out.println(G3send.VX+"\nx和y零点\n"+G3send.VY);
        if (StaticClass.isNull(G3JP.VXjf.getText()) || StaticClass.isNull(G3JP.VYjf.getText())) {
            JOptionPane.showMessageDialog(null, "坐标X,Y不能为空,请重新获取");
        } else {
            String mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<schmoll_mes_message version=\"1\">\n" +
                    " <cmd_type>Write</cmd_type>\n" +
                    " <cmd_id>drill.JobCall</cmd_id>\n" +
                    " <msg_result>0</msg_result>\n" +
                    " <sender_id>MES Server</sender_id>\n" +
                    " <receiver_id>" + "111" + "</receiver_id>\n" +
                    " <data>\n" +
                    " <param key=\"PrgName\" type=\"string\">" + PrgName + "</param> \n" +
                    " <param key=\"PrgDrive\" type=\"string\">" + "N:" + "</param> \n" +   //  mnt/net1
                    " <param key=\"CutName\" type=\"string\">" + cutName + "</param> \n" +
                    " <param key=\"CutDrive\" type=\"string\">" + "N:" + "</param> \n" +
                    " <param key=\"VX\" type=\"string\">" + G3JP.VXjf.getText() + "</param> \n" +
                    " <param key=\"VY\" type=\"string\">" + G3JP.VYjf.getText() + "</param> \n";
            mes = mes + " </data>\n" + "</schmoll_mes_message>";
            mes = "SMDATMSG,1,schmoll_mes_message,TlkwApplication/xml," + mes.length() + "\n" +
                    mes + "\nSMDATEND";
//            StaticClass.schmollClientImpl.send(mes);
        }
    }

    public static int jcid = 0;
    public static SimpleDateFormat tsFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static String JobCall_s80(String prgname,String cutname){
        double zob = 0.0;
        double vx = 0.0;
        double vy = 0.0;
        if (!StaticClass.recipeMap.isEmpty()){
            String macCode = StaticClass.webSetPro.getPropertyStr("eqpID", "MAC001");
            zob = Double.parseDouble(StaticClass.recipeMap.get(macCode+"_"+"1019").toString());
            vx = Double.parseDouble(StaticClass.recipeMap.get(macCode+"_"+"1017").toString());
            vy = Double.parseDouble(StaticClass.recipeMap.get(macCode+"_"+"1018").toString());
            jcid++;
            String ts = tsFormat1.format(new Date());
            String mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<schmoll_mes_message version=\"1\">\n" +
                    "<cmd_type>Write</cmd_type>\n" +
                    "<cmd_id>drill.JobCall</cmd_id>\n" +
                    "<cmd_variant>0</cmd_variant>\n" +
                    "<msg_id>"+jcid+"</msg_id>\n" +
                    "<msg_result>0</msg_result>\n" +
                    "<sender_id>mesAgent</sender_id>\n" +
                    "<receiver_id>S80</receiver_id>\n" +
                    "<msg_timestamp>"+ ts.substring(0, 26) + ":" + ts.substring(26) +"</msg_timestamp>\n" +
                    "<data_timestamp>" + ts.substring(0, 26) + ":" + ts.substring(26) + "</data_timestamp>\n" +
                    "<data>\n" +
                    "<param type=\"string\" key=\"PrgName\">" + prgname + "</param>\n" +
                    "<param type=\"string\" key=\"PrgDrive\">N:</param>\n" +
                    " <param key=\"CutName\" type=\"string\">" + cutname + "</param> \n" +
                    " <param key=\"CutDrive\" type=\"string\">" + "N:" + "</param> \n" +
                    " <param key=\"VX\" type=\"double\">" + vx + "</param> \n" +
                    " <param key=\"VY\" type=\"double\">" + vy + "</param> \n" +
                    " <param key=\"ZOB\" type=\"double\">" + zob + "</param> \n" +
                    "</data>\n" +
                    "</schmoll_mes_message>\n" ;
            mes = "SMDATMSG,1,schmoll_mes_message,TlkwApplication/xml,"+mes.length()+"\n" +
                    mes +                    "SMDATEND";
            return mes;
        }else {
            jcid++;
            String ts = tsFormat1.format(new Date());
            String mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<schmoll_mes_message version=\"1\">\n" +
                    "<cmd_type>Write</cmd_type>\n" +
                    "<cmd_id>drill.JobCall</cmd_id>\n" +
                    "<cmd_variant>0</cmd_variant>\n" +
                    "<msg_id>"+jcid+"</msg_id>\n" +
                    "<msg_result>0</msg_result>\n" +
                    "<sender_id>mesAgent</sender_id>\n" +
                    "<receiver_id>S80</receiver_id>\n" +
                    "<msg_timestamp>"+ ts.substring(0, 26) + ":" + ts.substring(26) +"</msg_timestamp>\n" +
                    "<data_timestamp>" + ts.substring(0, 26) + ":" + ts.substring(26) + "</data_timestamp>\n" +
                    "<data>\n" +
                    "<param type=\"string\" key=\"PrgName\">" + prgname + "</param>\n" +
                    "<param type=\"string\" key=\"PrgDrive\">N:</param>\n" +
                    " <param key=\"CutName\" type=\"string\">" + cutname + "</param> \n" +
                    " <param key=\"CutDrive\" type=\"string\">" + "N:" + "</param> \n" +
                    "</data>\n" +
                    "</schmoll_mes_message>\n" ;
            mes = "SMDATMSG,1,schmoll_mes_message,TlkwApplication/xml,"+mes.length()+"\n" +
                    mes +                    "SMDATEND";
            return mes;
        }
    }

    private static boolean containsChineseCharacters(String input) {
        String regex = ".*[\\u4e00-\\u9fa5]+.*"; // 正则表达式匹配包含中文字符的字符串
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private static void affirm() {  //确认
        if (G3JP.gdqrTF) {
//        affirmBt.setEnabled(false);  //避免反复请求
        }

        String wipEntity = "";
        if (!work1.getText().replaceAll(" ", "").equals("")) {
            if (wipEntity.equals("")) {
                wipEntity += work1.getText().replaceAll(" ", "");
            } else {
                wipEntity += "," + work1.getText().replaceAll(" ", "");
            }
        }
        if (!work2.getText().replaceAll(" ", "").equals("")) {
            if (wipEntity.equals("")) {
                wipEntity += work2.getText().replaceAll(" ", "");
            } else {
                wipEntity += "," + work2.getText().replaceAll(" ", "");
            }
        }
        if (!work3.getText().replaceAll(" ", "").equals("")) {
            if (wipEntity.equals("")) {
                wipEntity += work3.getText().replaceAll(" ", "");
            } else {
                wipEntity += "," + work3.getText().replaceAll(" ", "");
            }
        }
        if (!work4.getText().replaceAll(" ", "").equals("")) {
            if (wipEntity.equals("")) {
                wipEntity += work4.getText().replaceAll(" ", "");
            } else {
                wipEntity += "," + work4.getText().replaceAll(" ", "");
            }
        }
        if (!work5.getText().replaceAll(" ", "").equals("")) {
            if (wipEntity.equals("")) {
                wipEntity += work5.getText().replaceAll(" ", "");
            } else {
                wipEntity += "," + work5.getText().replaceAll(" ", "");
            }
        }
        if (!work6.getText().replaceAll(" ", "").equals("")) {
            if (wipEntity.equals("")) {
                wipEntity += work6.getText().replaceAll(" ", "");
            } else {
                wipEntity += "," + work6.getText().replaceAll(" ", "");
            }
        }
        if (wipEntity.equals("")) {
            JOptionPane.showMessageDialog(null, "工单ID不能为空");
            affirmBt.setEnabled(true);
            return;
        }
        wipEntity.replaceAll(" ", "");
        StaticClass.wipEntity=wipEntity;
        if (StrUtil.isEmpty(mociText.getText())){
            StaticClass.dataMap.put("macCode_301",mociText.getText());
        }
        StaticClass.dataMap.put("macCode_001",work1.getText());
        tempRecipe = wipEntity;
        work1.setEnabled(false);
        work2.setEnabled(false);
        work3.setEnabled(false);
        work4.setEnabled(false);
        work5.setEnabled(false);
        work6.setEnabled(false);
        mociText.setEnabled(false);
    }

    public static void clear() {
        G3JP.VXjf.setText("");
        G3JP.VYjf.setText("");
        work1.setText("");
        work2.setText("");
        work3.setText("");
        work4.setText("");
        work5.setText("");
        work6.setText("");
        wz.setText("");
        gx.setText("");
        affirmBt.setEnabled(true);
        recipeBt.setEnabled(true);
        completeBt.setEnabled(true);
        inBt.setEnabled(true);
        outBt.setEnabled(true);
        outShelfBt.setEnabled(true);
        inShelfBt.setEnabled(true);
        selectBox.removeAllItems();
        mociText.setEnabled(true);
        work1.setEnabled(true);
        work2.setEnabled(true);
        work3.setEnabled(true);
        work4.setEnabled(true);
        work5.setEnabled(true);
        work6.setEnabled(true);
        setMyTable(null);
        prg.setText("");
    }

    public static void clearBT() {
        affirmBt.setEnabled(true);
        recipeBt.setEnabled(true);
        inBt.setEnabled(true);
        outBt.setEnabled(true);
        outShelfBt.setEnabled(true);
        inShelfBt.setEnabled(true);
    }

    private static void inAGV() {  //叫料
        if (G3JP.jlTF) {
            inBt.setEnabled(false);  //避免反复请求
        }
    }

    private static void outAGV() {
        clear();
        if (G3JP.clTF) {
            outBt.setEnabled(false);
        }
    }

    private static void outShelfAGV() {
        if (G3JP.ckhjTF) {
            outShelfBt.setEnabled(false);
        }
    }

    private static void inShelfAGV() {
        if (G3JP.jkhjTF) {
            inShelfBt.setEnabled(false);
        }
    }

    public static void setMyTable(Vector tableValueList) {
        Vector titleList = new Vector<>();
        titleList.add("配方列表");
        myTable.defaultTableModel = new DefaultTableModel(tableValueList, titleList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        myTable.table.setModel(myTable.defaultTableModel);
        myTable.table.requestFocus();
        myTable.table.editCellAt(0, 0);
        myTable.table.changeSelection(0, 0, false, false);
        myTable.table.getModel().removeTableModelListener(myTable.tableModelListener);
//        System.out.println("myTable.tableModelListener");
        myTable.tableModelListener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int col = e.getColumn();
                int row = e.getFirstRow();
            }
        };
        myTable.table.getModel().addTableModelListener(myTable.tableModelListener);
    }

}
