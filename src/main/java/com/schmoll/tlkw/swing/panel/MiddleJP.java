package com.schmoll.tlkw.swing.panel;



import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.utils.StaticClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiddleJP {
   public static JButton btn1;
   public static JButton btn2;
   public static JButton btn3;
    public static void middleJPanel(JPanel jp){
        jp.setLayout(null);

        Font customFont = new Font("宋体",Font.PLAIN,12);
        btn1 =  MainForm.getBT("MES",10,18,100,25);
//        btn1.setFont(customFont);
//        btn1.setBackground(MainForm.gray);
        btn1.setBorderPainted(false);

        btn2 = MainForm.getBT("iMES设置",120,18,100,25);
//        btn2.setFont(customFont);
//        btn2.setBackground(MainForm.white);
        btn2.setBorderPainted(false);
//        btn2.setVisible(false);

        btn3 =  MainForm.getBT("用户管理",230,18,100,25);
//        btn3.setFont(customFont);
//        btn3.setBackground(MainForm.white);
        btn3.setBorderPainted(false);
//        btn3.setVisible(false);

        JButton btn4 =  MainForm.getBT("操作",340,18,100,25);
//        btn4.setFont(customFont);
//        btn4.setBackground(MainForm.white);
        btn4.setBorderPainted(false);
        btn4.setVisible(false);

        JButton btn5 =  MainForm.getBT("operation",450,18,100,25);
//        btn5.setFont(customFont);
//        btn5.setBackground(MainForm.white);
        btn5.setBorderPainted(false);
        btn5.setVisible(false);

        StaticClass.jpAddBt(jp,btn1,btn2,btn3,btn4,btn5);
        
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlBt(btn1,MainForm.gray,btn2,MainForm.white,btn3,MainForm.white);
                controlJP(MainForm.g3JP,true,MainForm.iMESJP,false,MainForm.userJP,false);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlBt(btn1,MainForm.white,btn2,MainForm.gray,btn3,MainForm.white);
                controlJP(MainForm.g3JP,false,MainForm.iMESJP,true,MainForm.userJP,false);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlBt(btn1,MainForm.white,btn2,MainForm.white,btn3,MainForm.gray);
                controlJP(MainForm.g3JP,false,MainForm.iMESJP,false,MainForm.userJP,true);
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlBt(btn1,MainForm.white,btn2,MainForm.white,btn3,MainForm.white,btn4,MainForm.gray,btn5,MainForm.white);
                controlJP(MainForm.leftJP,false,MainForm.rightJP,false,MainForm.iMESJP,false,MainForm.userJP,false,MainForm.operationJP,true,MainForm.g3JP,false);
            }
        });

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlBt(btn1,MainForm.white,btn2,MainForm.white,btn3,MainForm.white,btn4,MainForm.white,btn5,MainForm.gray);
                controlJP(MainForm.leftJP,false,MainForm.rightJP,false,MainForm.iMESJP,false,MainForm.userJP,false,MainForm.operationJP,false,MainForm.g3JP,true);
            }
        });

    }

//    public static void controlBt(JButton b1,Color c1,JButton b2,Color c2,JButton b3,Color c3,JButton b4,Color c4){
//        b1.setBackground(c1);
//        b2.setBackground(c2);
//        b3.setBackground(c3);
//        b4.setBackground(c4);
//    }
//
//    public static void controlJP(JPanel j1,Boolean b1, JPanel j2,Boolean b2,JPanel j3,Boolean b3,JPanel j4,Boolean b4,JPanel j5,Boolean b5){
//        j1.setVisible(b1);
//        j2.setVisible(b2);
//        j3.setVisible(b3);
//        j4.setVisible(b4);
//        j5.setVisible(b5);
//    }

    public static void controlBt(Object... values) {
        // 确保values数组长度是偶数，避免数组越界
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("参数数量必须是偶数");
        }
        for (int i = 0; i < values.length; i += 2) {
            try {
                JButton button = (JButton) values[i];
                //Color color = (Color) values[i + 1];
                //button.setBackground(color);
                //設置按鈕邊框
                button.setBorderPainted(true);

            } catch (ClassCastException e) {  // 处理类型转换异常，可以打印错误日志或抛出运行时异常
//                System.err.println("参数类型转换错误: " + e.getMessage());
                throw new RuntimeException("参数类型不匹配，应为JButton和Color交替出现", e);
            }
        }
    }

    public static void controlJP(Object... values) {
        // 确保values数组长度是偶数，避免数组越界
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("参数数量必须是偶数");
        }
        for (int i = 0; i < values.length; i += 2) {
            try {
                JPanel value = (JPanel) values[i];
                Boolean b = (Boolean) values[i+1];
                value.setVisible(b);
            } catch (ClassCastException e) {  // 处理类型转换异常，可以打印错误日志或抛出运行时异常
//                System.err.println("参数类型转换错误: " + e.getMessage());
                throw new RuntimeException("参数类型不匹配，应为JPanel和Boolean交替出现", e);
            }
        }
    }

    public static void main(String[] args) {
        JButton white1 = new JButton();
//        JButton white2 = new JButton();
//        JButton gray3 = new JButton();
////        controlJB1(white1,white2,gray3);
//
//        JButton white = new JButton();
//        JButton gray = new JButton();
//        controlJB2(white,gray,gray,white,white,gray,white);
        controlBt(white1,Color.red);
        Color background = white1.getBackground();
        System.out.println(background);
    }
}
