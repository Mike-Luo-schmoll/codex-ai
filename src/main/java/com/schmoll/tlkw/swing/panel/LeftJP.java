package com.schmoll.tlkw.swing.panel;


import com.schmoll.tlkw.swing.MainForm;

import javax.swing.*;

public class LeftJP {

    public static void leftJPanel(JPanel jp){
        jp.setLayout(null);

        JLabel jl0 = MainForm.getJL("机台信息",true, 70, 2, 70, 35,false);

        JButton btn1 = new JButton("");
        btn1.setBounds(1, 0, 212, 35);
        btn1.setBackground(MainForm.gray);
        btn1.setBorderPainted(false);
        btn1.setEnabled(false);


        JLabel jl1 = MainForm.getJL("机台号:",true, 10, 45, 70, 25,false);

        MainForm.DrillIdJL= MainForm.getJL( MainForm.DrillId,true, 70, 45, 130, 25,true);

        JLabel jl2 = MainForm.getJL( "机台类型:",true, 10, 90, 70, 25,false);

        MainForm.DrillTypeJL= MainForm.getJL( MainForm.DrillType,true, 70, 90, 130, 25,true);

        JLabel jl3 = MainForm.getJL( "LotID:",true, 10, 135, 70, 25,false);

        MainForm.LotJL= MainForm.getJL("",true, 70, 135, 130, 25,true);

        jp.add(jl0);
        jp.add(btn1);
        jp.add(jl1);
        jp.add(MainForm.DrillIdJL);
        jp.add(jl2);
        jp.add(MainForm.DrillTypeJL);
        jp.add(jl3);
        jp.add(MainForm.LotJL);
    }
}
