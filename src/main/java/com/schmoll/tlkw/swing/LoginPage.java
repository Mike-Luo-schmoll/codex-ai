package com.schmoll.tlkw.swing;


import com.formdev.flatlaf.FlatDarculaLaf;
import com.schmoll.tlkw.TlkwApplication;
import com.schmoll.tlkw.entity.PcDataEmp;
import com.schmoll.tlkw.entity.PcSignalStatus;
import com.schmoll.tlkw.service.PcDataEmpService;
import com.schmoll.tlkw.service.PcSignalStatusService;
import com.schmoll.tlkw.swing.panel.G3JP;
import com.schmoll.tlkw.utils.StaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

@Slf4j
@Component
@Lazy
public class LoginPage  extends JFrame {
    public static JTextField userText;
    public static JTextField passwordText;
     PcDataEmpService empService;
     PcSignalStatusService signalStatusService;
    public LoginPage() {
        initialize();
    }

    private void initialize() {
        setTitle("登录界面");
        setSize(350, 250);
        setLocation(220, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        FlatDarculaLaf.setup();
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        // 注意：这里不要 setVisible(true)，交给业务逻辑控制
    }

    public void showLoginFrame() {
        setVisible(true);
    }



    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userlabel = new JLabel("用户工号:");
        userlabel.setBounds(50, 80, 80, 25);
        panel.add(userlabel);
        userText = new JTextField();
        userText.setBounds(120, 80, 165, 25);
        panel.add(userText);

        JLabel passwordLable = new JLabel("姓名:");
        passwordLable.setBounds(50, 30, 80, 25);
        panel.add(passwordLable);
        passwordText = new JTextField();
        passwordText.setBounds(120,30,165,25);
        panel.add(passwordText);

        JLabel passwordLable1 = new JLabel("角色选择:");
        passwordLable1.setBounds(10, 80, 80, 25);
//        panel.add(passwordLable1);

        JComboBox<String> comboBox = new JComboBox<>();
        String[] select = {"操作员","工程师","管理员"};
        comboBox.setModel(new DefaultComboBoxModel<>(select)); //添加选项值
        comboBox.setBounds(100, 80, 165, 25);
//        panel.add(comboBox);

        JButton loginButton =new JButton("上机");
        loginButton.setBounds(40,140,80,25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (handleLogin(loginButton)) {
                    return;
                }
                String empLot = userText.getText();
                String empName = passwordText.getText();
                ApplicationContext context = StaticClass.getApplicationContext();
                 empService = context.getBean(PcDataEmpService.class);
                 empService.save(new PcDataEmp(null,empLot,empName,"INTIME","上机时间",new Date(),new Date()));
//                if (!DbClass.compareUser(userText.getText(), password, Objects.requireNonNull(comboBox.getSelectedItem()).toString())){
//                    passwordText.setText("");
//                    userText.setText("");
//                    JOptionPane.showMessageDialog(null, "账号或密码错误!!");
//                    return;
//                }else{
//                    String str = "";
//                    G3JP.userTxt.setText("");
//                    str=userText.getText();//admin 显示设置按钮

                setVisible(false); // 隐藏当前窗体//
                TlkwApplication.frame = context.getBean(MainForm.class);
                TlkwApplication.frame.setVisible(true);
                TlkwApplication.frame.repaint();//强制重绘
                G3JP.userTxt.setText(userText.getText());
                G3JP.userTxt.setEnabled(false);
                new StaticClass(context);

//                    MiddleJP.btn1.doClick();
//                    if(str.equals("admin")){
//                        MiddleJP.btn2.setVisible(true);
//                        MiddleJP.btn3.setVisible(true);
//                    }else {
//                        MiddleJP.btn2.setVisible(false);
//                        MiddleJP.btn3.setVisible(false);
//                    }
//                }
            }
        });

        JButton loginButton1 = new JButton("取消");
        loginButton1.setBounds(180,140,80,25); //设置登录的位置和大小
        panel.add(loginButton1);
        loginButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordText.setText("");
                userText.setText("");
                setExtendedState(WindowConstants.HIDE_ON_CLOSE);
            }
        });
    }

    private boolean handleLogin(final JButton loginButton) {
        final String empLot = userText.getText();
        final String empName = passwordText.getText();
        final ApplicationContext context = StaticClass.getApplicationContext();
        loginButton.setEnabled(false);

        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    empService = context.getBean(PcDataEmpService.class);
                    empService.save(new PcDataEmp(null, empLot, empName, "INTIME", "上机时间", new Date(), new Date()));
                    signalStatusService= context.getBean(PcSignalStatusService.class);
                    signalStatusService.save(new PcSignalStatus(null,"POWER","设备上机","1",new Date()));
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            setVisible(false);
                            TlkwApplication.frame = context.getBean(MainForm.class);
                            TlkwApplication.frame.setVisible(true);
                            TlkwApplication.frame.repaint();
                            G3JP.userTxt.setText(userText.getText());
                            G3JP.userTxt.setEnabled(false);
                            StaticClass.startSchmollClient(context);
                            loginButton.setEnabled(true);
                        }
                    });
                } catch (final Exception ex) {
                    log.error("Login failed", ex);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            loginButton.setEnabled(true);
                            JOptionPane.showMessageDialog(LoginPage.this, "上机失败：" + ex.getMessage());
                        }
                    });
                }
            }
        }, "Login-Worker");
        worker.setDaemon(true);
        worker.start();
        return true;
    }

    public static void setPage(){
//        if (UserControlSqlite.currentUser.getRole().equals("")){
//            StyleProperties.tab.remove(StyleProperties.adminPage);
//            StyleProperties.tab.remove(StyleProperties.userAdminPage);
//        }
//        if (UserControlSqlite.currentUser.getRole().equals("操作员")){
//            StyleProperties.tab.remove(StyleProperties.adminPage);
//            StyleProperties.tab.remove(StyleProperties.userAdminPage);
//        }else if(UserControlSqlite.currentUser.getRole().equals("工程师")){
//            StyleProperties.tab.remove(StyleProperties.adminPage);
//            StyleProperties.tab.remove(StyleProperties.userAdminPage);
//        }else{
//            StyleProperties.tab.add(StyleProperties.adminPage,"iMES设置");
//            StyleProperties.tab.add(StyleProperties.userAdminPage,"用户管理");
//        }
    }
}

