package com.schmoll.tlkw.swing.panel;


import com.schmoll.tlkw.TlkwApplication;
import com.schmoll.tlkw.swing.LoginPage;
import com.schmoll.tlkw.swing.MainForm;
import com.schmoll.tlkw.utils.ClsPropertySet;
import com.schmoll.tlkw.utils.StaticClass;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Vector;

public class IMESJP {
    public static JButton saveBt;
    public static JButton setBt;

    public static JCheckBox  startHeart;

    public static String XYzero = StaticClass.webSetPro.getPropertyStr("XYzero","false");
    static SetTable myTable;
    public static void iMESJPanel(JPanel jp){
        int x = 10;
        int y = 10;
        int width = 10;
        int heigth = 30;
        int labelWidth = 100;
        int labelHeigth = 25;
        int textWidth = 220;
        int textheigth = 25;
        jp.setLayout(null);

        JButton btn1 = new JButton("");
        btn1.setBounds(1, 2, 212, 35);
        btn1.setBorderPainted(false);
        btn1.setEnabled(false);
//        jp.add(btn1);
        ClsPropertySet webSetPro = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "webSet.pro");
        JLabel jl1 = MainForm.getJL("设备编号:",true, x, y, labelWidth, labelHeigth,false);
        MainForm.imesDrillId= MainForm.getJF( webSetPro.getPropertyStr("eqpID","MAC001"), x+labelWidth+width, y, textWidth, textheigth);
        JLabel jl2 = MainForm.getJL("iMES URL:",true, x, y+heigth, labelWidth, labelHeigth,false);
        MainForm.imesDrillUrl= MainForm.getJF( webSetPro.getPropertyStr("url","http://192.168.10.1:9000/wsservice/macWS?Wsdl"), x+labelWidth+width,y+heigth,textWidth,textheigth);
        JLabel jl3 = MainForm.getJL( "通讯超时:",true, x, y+2*heigth, labelWidth, labelHeigth,false);
        MainForm.imesDrillTimeOut= MainForm.getJF( webSetPro.getPropertyStr("imesDrillTimeOut","30"), x+labelWidth+width,y+2*heigth,100,textheigth);
        JTextField overUnit = MainForm.getJF("(S)",x+labelWidth+width+100,y+2*heigth,30,textheigth);
        overUnit.setBackground(new Color(60, 63, 65));
        overUnit.setEnabled(false);
        overUnit.setForeground(Color.BLACK);
        overUnit.setBorder(new EmptyBorder(0,5,0,5));
        JLabel jl4 = MainForm.getJL("AGV按钮时效:",true,x, y+3*heigth, labelWidth, labelHeigth,false);
       MainForm.agvOutTime= MainForm.getJF(webSetPro.getPropertyStr("agvOutTime","5"),x+labelWidth+width,y+3*heigth,100,textheigth);
        JTextField agvUnit = MainForm.getJF("(S)",x+labelWidth+width+100,y+3*heigth,30,textheigth);
        agvUnit.setBackground(new Color(60, 63, 65));
        agvUnit.setEnabled(false);
        agvUnit.setForeground(Color.BLACK);
        agvUnit.setBorder(new EmptyBorder(0,5,0,5));
        JLabel jl5 = MainForm.getJL( "叫料载位编号:",true, x, y+4*heigth, labelWidth, labelHeigth,false);
        JLabel jl6 = MainForm.getJL( "出料载位编号:",true, x, y+5*heigth, labelWidth, labelHeigth,false);
        MainForm.inAGV = MainForm.getJF( webSetPro.getPropertyStr("storeCode",""), x+labelWidth+width,y+4*heigth,textWidth,textheigth);
        MainForm.outAGV = MainForm.getJF( webSetPro.getPropertyStr("dischargeCode",""), x+labelWidth+width,y+5*heigth,textWidth,textheigth);

        saveBt = MainForm.getBT("保存",80,y+12*heigth+15,70,25);

        JLabel jl7 = MainForm.getJL("心跳设置:",true,x, y+6*heigth, 70, labelHeigth,false);
        startHeart = new JCheckBox("启用心跳");
        startHeart.setSelected(webSetPro.getPropertyBool("heartbeatStart",true));
        MainForm.heartbeatStart=webSetPro.getPropertyBool("heartbeatStart",true);
        startHeart.setBackground(new Color(60, 63, 65));
        startHeart.setBounds((int) Math.round( x * MainForm.w_s80), (int) Math.round( y+7*heigth * MainForm.h_s80), (int) Math.round( labelWidth * MainForm.w_s80), (int) Math.round( labelHeigth * MainForm.h_s80)
        );
        jp.add(startHeart);
//        JLabel jl8 = MainForm.getJL("启用心跳",true, x, y+7*heigth, labelWidth, labelHeigth,false);
//        MainForm.setUpload= MainForm.getJF( webSetPro.getPropertyStr("setUpload","DataUpload"), 600, 50, 180, 25);
        JLabel jl9 = MainForm.getJL("心跳内容:",true, x,y+8*heigth,labelWidth, labelHeigth,false);
        MainForm.heartbeatContent=webSetPro.getPropertyStr("heartbeatContent","SSEMP");
        MainForm.heartbeatContentBody=MainForm.getJF(MainForm.heartbeatContent,x+labelWidth+width,y+8*heigth,textWidth,textheigth);
//        MainForm.setRecipe= MainForm.getJF( webSetPro.getPropertyStr("setRecipe","1004"), 600, 100, 180, 25);
        JLabel jl10 = MainForm.getJL("心跳服务:",true, x,y+9*heigth,labelWidth, labelHeigth,false);
        MainForm.heartbeatIPBody=MainForm.getJF(webSetPro.getPropertyStr("heartbeatIP","http://127.0.0.1"),x+labelWidth+width,y+9*heigth,textWidth,textheigth);

//        MainForm.TC= MainForm.getJF( webSetPro.getPropertyStr("TC","tagCode"), 600, 150, 180, 25);
        JLabel jl11 = MainForm.getJL("心跳端口:",true, x,y+10*heigth,labelWidth, labelHeigth,false);
        MainForm.heartbeatPortBody=MainForm.getJF(webSetPro.getPropertyStr("heartbeatPort","8900"),x+labelWidth+width,y+10*heigth,textWidth,textheigth);

//        MainForm.TV= MainForm.getJF( webSetPro.getPropertyStr("TV","tagValue"), 600, 200, 180, 25);
        JLabel jl12 = MainForm.getJL("心跳频率(S):",true, x,y+11*heigth,labelWidth, labelHeigth,false);
//        MainForm.Tzero= MainForm.getJF( webSetPro.getPropertyStr("Tzero","1005"), 600, 250, 180, 25);
        MainForm.heartbeatFrequencyBody=MainForm.getJF(webSetPro.getPropertyStr("heartbeatFrequency","30"),x+labelWidth+width,y+11*heigth,textWidth,textheigth);
        setBt = MainForm.getBT("取消",230,y+12*heigth+15,70,25);
        JLabel tableLabel = MainForm.getJL("配方标签说明",true,380,y,480,25,false);
        tableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tableLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        tableLabel.setBackground(new Color(186,192,185));

        String[] n = { "序号","标签","说明"};
        myTable = new SetTable(n);
        myTable.table.getColumnModel().getColumn(0).setPreferredWidth(40);
        // 设置第二列宽度为120
        myTable.table.getColumnModel().getColumn(1).setPreferredWidth(120);
        myTable.table.getColumnModel().getColumn(2).setPreferredWidth(220);
        JScrollPane vv = myTable.getMyTable();
        vv.setBounds((int) Math.round(380 * MainForm.w_s80), (int) Math.round( 35 * MainForm.h_s80), (int) Math.round( 480 * MainForm.w_s80), (int) Math.round( 330 * MainForm.h_s80));
        jp.add(vv);

        StaticClass.jpAddJf(jp,MainForm.imesDrillId,MainForm.imesDrillUrl,MainForm.imesDrillTimeOut,overUnit,MainForm.agvOutTime,agvUnit,MainForm.inAGV,MainForm.outAGV,
                MainForm.heartbeatContentBody,MainForm.heartbeatIPBody,MainForm.heartbeatPortBody,MainForm.heartbeatFrequencyBody);

        StaticClass.jpAddJl(jp,jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl9,jl10,jl11,jl12,tableLabel);

        StaticClass.jpAddBt(jp,saveBt,setBt);

        initListener();
    }
    public static void updateTableData(List<Vector<Object>> dataList){
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) myTable.table.getModel();
        model.setRowCount(0); // 清空所有行

        // 2. 遍历数据并添加到表格
        if (dataList != null) {
            int index = 1; // 序号从1开始
            for (Vector<Object> row : dataList) {
                // row 结构假设为 [TagCode, TagValue]
                // 表格列结构: 0=序号, 1=标签(TagCode), 2=说明(TagValue)
                Vector<Object> tableRow = new Vector<>();
                tableRow.add(index++);           // 序号
                tableRow.add(row.get(0));        // 标签 (TagCode)
                tableRow.add(row.get(1));        // 说明 (TagValue)

                model.addRow(tableRow);
            }
        }

        // 3. 刷新 UI
        myTable.table.updateUI();
    }


    private static void initListener(){
        saveBt.addActionListener(e -> saveIMES());

        setBt.addActionListener(e -> setLabel());
        }

    public static Boolean T_or_F(Boolean bo,JButton bt){
        if (bo) {
            bt.setBackground(MainForm.red);
            bo = false;
        } else {
            bt.setBackground(MainForm.green);
            bo = true;
        }
        return bo;
    }

    public static void saveIMES(){            //iMES保存
        boolean selected = startHeart.isSelected();
        MainForm.MESJL.setText(MainForm.imesDrillUrl.getText());
        StaticClass.webSetPro.setPropertyStr("eqpID",MainForm.imesDrillId.getText());
        StaticClass.webSetPro.setPropertyStr("imesDrillTimeOut",MainForm.imesDrillTimeOut.getText());
        StaticClass.webSetPro.setPropertyStr("url",MainForm.imesDrillUrl.getText());
        StaticClass.webSetPro.setPropertyStr("storeCode",MainForm.inAGV.getText());
        StaticClass.webSetPro.setPropertyStr("dischargeCode",MainForm.outAGV.getText());
        StaticClass.webSetPro.setPropertyStr("agvOutTime",MainForm.agvOutTime.getText());
        StaticClass.webSetPro.setPropertyStr("heartbeatContent",MainForm.heartbeatContentBody.getText());
        StaticClass.webSetPro.setPropertyStr("heartbeatIP",MainForm.heartbeatIPBody.getText());
        StaticClass.webSetPro.setPropertyStr("heartbeatPort",MainForm.heartbeatPortBody.getText());
        StaticClass.webSetPro.setPropertyStr("heartbeatFrequency",MainForm.heartbeatFrequencyBody.getText());
        StaticClass.webSetPro.setPropertyBool("heartbeatStart",selected);
        StaticClass.webSetPro.saveProperties();
        SwingUtilities.invokeLater(() -> {
            JLabel messageLabel = new JLabel("修改配置成功!");
            messageLabel.setFont(new Font("宋体", Font.BOLD, 16)); // 设置字体，字体样式和大小
            JOptionPane.showMessageDialog(null,messageLabel);
        });
        G3JP.clear();
        TlkwApplication.frame.setVisible(false);
        ApplicationContext context = StaticClass.getApplicationContext();
// 2. 从容器里拿出 LoginPage 的实例（这个实例是有 Service 依赖注入的）
        LoginPage loginPage = context.getBean(LoginPage.class);
        loginPage.setVisible(true);

    }

    public static void setLabel(){
        MiddleJP.btn1.doClick();
//        G3send.saveStatus(); //保存后发送暂停状态
    }
}
