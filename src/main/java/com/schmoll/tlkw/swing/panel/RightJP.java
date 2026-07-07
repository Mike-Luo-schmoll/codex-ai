package com.schmoll.tlkw.swing.panel;



import com.schmoll.tlkw.schmollMes.impl.SchmollClientImpl;
import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.swing.MyTable;
import com.schmoll.tlkw.utils.StaticClass;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RightJP {

    public static List<MyTable> tableList = new ArrayList();

    public static void rightJPanel(JPanel jp){
        jp.setLayout(null);
        Font customFont = new Font("宋体",Font.PLAIN,12);
        JLabel jl0 = MainForm.getJL("配方信息",true, 300, 2, 70, 35,false);
        jp.add(jl0);

        JButton btn1 = new JButton("");
        btn1.setBounds(1, 0, 672, 35);
        btn1.setBackground(MainForm.gray);
        btn1.setBorderPainted(false);
        btn1.setEnabled(false);
        jp.add(btn1);

        JLabel jl1 = MainForm.getJL("工 单 号:",true, 10, 50, 70, 25,false);
//        jp.add(jl1);

        MainForm.lotJF= MainForm.getJF( "1234567890", 70, 50, 180, 25);
//        jp.add(lotTxt);

        JLabel jl2 = MainForm.getJL("卡 号:",true, 290, 50, 70, 25,false);
//        jp.add(jl2);

        MainForm.cardJF= MainForm.getJF( "9876543210", 330, 50, 180, 25);
//        jp.add(cardTxt);

        JButton acquireBt = MainForm.getBT("取得",560, 50, 60, 25);
//        jp.add(acquireBt);

        acquireBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acquirePrg();
            }
        });

        JTextField recipeBody = new JTextField("配方列表");
        MainForm.myTable = new MyTable(recipeBody);
        tableList.add(MainForm.myTable);
        JScrollPane vv = MainForm.myTable.getMyTable();
//        vv.setBounds(1, 90, 672, 165);
        vv.setFont(customFont);
        vv.setBounds(1, 50, 672, 195);
        jp.add(vv);

        JLabel jl3 = MainForm.getJL("配方资料:",true, 10, 265, 65, 25,false);
        jp.add(jl3);

        MainForm.prgTxt = MainForm.getJL("",false,80, 295, 330, 25,true);
        MainForm.JSPprg = MainForm.getJSP(MainForm.JSPprg,80, 265, 350, 50);
        MainForm.JSPprg.setViewportView(MainForm.prgTxt);
        jp.add(MainForm.JSPprg);

        JLabel jl4 = MainForm.getJL("切削资料:",true, 10, 335, 65, 25,false);
        jp.add(jl4);

        MainForm.cutTxt = MainForm.getJL("",false,80, 335, 330, 25,true);
        MainForm.JSPcut = MainForm.getJSP(MainForm.JSPcut,80, 335, 350, 50);
        MainForm.JSPcut.setViewportView(MainForm.cutTxt);
        jp.add(MainForm.JSPcut);

        JButton loadBt =MainForm.getBT("载入",345, 400, 60, 25);
        loadBt.setBounds(345, 400, 60, 25);
        loadBt.setFont(customFont);
        loadBt.setBackground(MainForm.white);
        jp.add(loadBt);

        loadBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPrg();  //加载程式
            }
        });

        MainForm.mesMtext = new JTextArea("");
//        mesMtext.setBounds(480, 310, 180, 120);
        MainForm.mesMtext.setBackground(new Color(254,165,50));
        MainForm.mesMtext.setLineWrap(true);//自动换行
        MainForm.mesMtext.setWrapStyleWord(true);
        MainForm.mesMtext.setEditable(false);
        MainForm.JSPMtext = MainForm.getJSP(MainForm.JSPMtext,475, 290, 185, 140);
        MainForm.JSPMtext.setViewportView(MainForm.mesMtext);
        jp.add(MainForm.JSPMtext);

        JLabel icon = new JLabel(new ImageIcon(System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "icon.png"));
        icon.setBounds(480, 380, 120, 50);
//        jp.add(icon);

    }

    public static void acquirePrg(){          //取得配方
        String[][] tableValueList = new String[2][1];
        tableValueList[0][0] = String.valueOf(MainForm.lotJF.getText());
        tableValueList[1][0] = String.valueOf(MainForm.cardJF.getText());
        recipeList(tableValueList);
    }
    public static void recipeList(String[][] tableValueList){
        String[] n = {"配方列表"};
        MainForm.myTable.defaultTableModel = new DefaultTableModel(tableValueList, n){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        MainForm.myTable.table.setModel(MainForm.myTable.defaultTableModel);
        MainForm.myTable.table.requestFocus();
        MainForm.myTable.table.editCellAt(0,0);
        MainForm.myTable.table.changeSelection(0,0,false, false);
        MainForm.myTable.table.getModel().removeTableModelListener(MainForm.myTable.tableModelListener);
        MainForm.myTable.tableModelListener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                MainForm.prgTxt.setText(String.valueOf(MainForm.myTable.table.getValueAt(row,col)));
            }
        };
        MainForm.myTable.table.getModel().addTableModelListener(MainForm.myTable.tableModelListener);
    }

    public static void loadPrg(){             //载入配方
//        MainForm.prgTxt.setText("/fpc/01product/04301-99999/GRM03C13217C2/DRL/grm03c13217c2x3.0y9.0/grm03c13217c2x3.0y9.0.dwl");
//        MainForm.cutTxt.setText("/fpc/01product/04301-99999/GRM03C13217C2/DRL/grm03c13217c2x3.0y9.0/grm03c13217c2x3.0y9.0.drl");
        jobCall();  //加载程式
    }

    public static void jobCall() {
        if (MainForm.containsChineseCharacters(MainForm.prgTxt.getText())) {
            JOptionPane.showMessageDialog(null, "配方路径不能包含中文，请重新获取");
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
                " <param key=\"PrgName\" type=\"string\">" +  MainForm.prgTxt.getText() + "</param> \n" +
                " <param key=\"PrgDrive\" type=\"string\">" + "N:" + "</param> \n" +
                " <param key=\"CutName\" type=\"string\">" + MainForm.cutTxt.getText() + "</param> \n" +
                " <param key=\"CutDrive\" type=\"string\">" + "N:" + "</param> \n" ;
        mes = mes + " </data>\n" + "</schmoll_mes_message>";
        mes = "SMDATMSG,1,schmoll_mes_message,application/xml," + mes.length() + "\n" +
                mes + "\nSMDATEND";
        ApplicationContext context = StaticClass.getApplicationContext();
        SchmollClientImpl bean = context.getBean(SchmollClientImpl.class);
        bean.send(mes);
    }
}
