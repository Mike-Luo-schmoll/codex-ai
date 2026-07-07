package com.schmoll.tlkw.swing.panel;



import com.schmoll.tlkw.swing.RecipeTable;
import com.schmoll.tlkw.utils.ClsPropertySet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class OperationJP {
    public static JButton modeBody;
    public static JButton cutNameBody;  //切削资料 按钮
    public static JTextField cutNameTxt;   //切削资料文本框 按钮  cutNameDataPath.pro
    public static JTextField XTxt;
    public static JTextField YTxt;
    public static ClsPropertySet set_cutNameDataPath = new ClsPropertySet(System.getProperty("user.dir") + File.separator + "mesAgentConfig" + File.separator + "mesAgent.pro");

    public static JTextField sempBody;

    public static  Vector titleList;
    public Map dataMap = new HashMap();

    public static JTextField userIDBody;
    public static JTextField order1Body;
    public static JTextField order2Body;
    public static JTextField order3Body;
    public static JTextField order4Body;
    public static JTextField order5Body;
    public static JTextField order6Body;
    public static  JTextField materialCodeBody;
    public static JTextField processCodeBody;
    public static  JTextField recipeBody = new JTextField();
    public static  JTextArea mesMessageBody;
    public static  JTextField timeRetuen;

    public static  JComboBox cmb1;
    public static String[] select1 = {"1", "2","3", "4"};

    public static JButton affirmButton;
    public static JButton clearButton;
    public static JButton storeButton;
    public static JButton dischargeButton;
    public static JButton returnButton;
    public static JButton errDichargeButton;
    public static JButton completeButton;
    public static JButton resetButton;
    public static JButton loginButton;
    public static JButton recipeButton;
    public static  JComboBox selectBox;


    public static  String recipeText = "";
    public static  String recipeNum = "";
    public static  String oldRecipe = "";
    public static String tempRecipe;

    public static  Boolean setFlag = true;

    public static RecipeTable myTable;
    public static void operationJPanel(JPanel jp){

        titleList = new Vector<>();
        titleList.add("配方列表");
//        StyleProperties.setting = this;
        int x = 10;
        int y = 10;
        int width = 10;
        int heigth = 30;
        int textWidth = 170;
        int textheigth = 25;
        int labelWidth = 80;
        int labelHeigth = 25;

        jp.setLayout(null);
        jp.setBackground(new Color(230, 240, 232));
        jp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JLabel modelLabel = getJLabel("模式切换:");
        modelLabel.setBounds(x, y, labelWidth, labelHeigth);
        jp.add(modelLabel);

        modeBody = getJButton("在线");  //### on line  off line
        modeBody.setBounds(x + labelWidth + width, y, 40, 25);
        jp.add(modeBody);

        sempBody = getJTextField("",1);
        sempBody.setBounds(666, 10, 55, 20);
        sempBody.setBackground(Color.GREEN);
        jp.add(sempBody);

        JLabel userIDLabel = getJLabel("人员ID:");
        userIDLabel.setBounds(x, y+heigth, labelWidth, labelHeigth);
//        add(userIDLabel);

        userIDBody = getJTextField("", 1);
        userIDBody.setBounds(250, 10, 170, 25);
        jp.add(userIDBody);

        loginButton = getJButton("注销");  // ### logout
        loginButton.setBounds(450, 10, 60, 20);
        jp.add(loginButton);

        JLabel order1Label = getJLabel("工单ID1:");
        order1Label.setBounds(x,y+1*heigth,labelWidth,labelHeigth);
        jp.add(order1Label);

        order1Body = getJTextField("", 2);
        order1Body.setBounds(x + labelWidth + width, y + heigth, textWidth, textheigth);
        jp.add(order1Body);

        JLabel order2Label = getJLabel("工单ID2:");
        order2Label.setBounds(x+labelWidth+textWidth+2*width,y+1*heigth,labelWidth,labelHeigth);
        jp.add(order2Label);

        order2Body = getJTextField("", 2);
        order2Body.setBounds(x + 2 * labelWidth + textWidth + 3 * width, y + heigth, textWidth, textheigth);
        jp.add(order2Body);

        storeButton = getAGVJButton("叫料");
        storeButton.setBounds(x+2*labelWidth+2*textWidth+3*width+90,y+2*heigth,80, 40);
        jp.add(storeButton);

        cmb1 = new JComboBox();
        cmb1.setModel(new DefaultComboBoxModel(select1));
        cmb1.setBounds(x+2*labelWidth+2*textWidth+3*width+50,y+3*heigth,100, 40);
//        add(cmb1);

        selectBox = new JComboBox();
        selectBox.setVisible(true);
        selectBox.setBounds(x + 2 * labelWidth + 2 * textWidth + 4 * width + 90 + 80, y + 2 * heigth - 30, 140, 30);
        jp.add(selectBox);

        dischargeButton = getAGVJButton("出料");
        dischargeButton.setBounds(x + 2 * labelWidth + 2 * textWidth + 4 * width + 90 + 100, y + 2 * heigth, 80, 40);
        jp.add(dischargeButton);

        timeRetuen = getJTextField("21321", 2);
        timeRetuen.setBounds(x + 2 * labelWidth + 2 * textWidth + 3 * width + 90, y + 2 * heigth + 45, 80, 20);
        timeRetuen.setBackground(new Color(230, 240, 232));
        timeRetuen.setBorder(new EmptyBorder(0, 10, 0, 0));
        timeRetuen.setEnabled(false);
//        add(timeRetuen);

        returnButton = getAGVJButton("出空货架");
        returnButton.setBounds(x + 2 * labelWidth + 2 * textWidth + 3 * width + 90, y + 2 * heigth + 60 + 20, 80, 40);
        jp.add(returnButton);

        errDichargeButton = getAGVJButton("叫空货架");
        errDichargeButton.setBounds(x + 2 * labelWidth + 2 * textWidth + 4 * width + 90 + 100, y + 2 * heigth + 60 + 20, 80, 40);
        jp.add(errDichargeButton);

        JLabel order3Label = getJLabel("工单ID3:");
        order3Label.setBounds(x, y + 2 * heigth, labelWidth, labelHeigth);
        jp.add(order3Label);

        order3Body = getJTextField("", 2);
        order3Body.setBounds(x + labelWidth + width, y + 2 * heigth, textWidth, textheigth);
        jp.add(order3Body);

        JLabel order4Label = getJLabel("工单ID4:");
        order4Label.setBounds(x + labelWidth + textWidth + 2 * width, y + 2 * heigth, labelWidth, labelHeigth);
        jp.add(order4Label);

        order4Body = getJTextField("", 2);
        order4Body.setBounds(x + 2 * labelWidth + textWidth + 3 * width, y + 2 * heigth, textWidth, textheigth);
        jp.add(order4Body);

        JLabel order5Label = getJLabel("工单ID5:");
        order5Label.setBounds(x, y + 3 * heigth, labelWidth, labelHeigth);
        jp.add(order5Label);

        order5Body = getJTextField("", 2);
        order5Body.setBounds(x + labelWidth + width, y + 3 * heigth, textWidth, textheigth);
        jp.add(order5Body);

        JLabel order6Label = getJLabel("工单ID6:");
        order6Label.setBounds(x + labelWidth + textWidth + 2 * width, y + 3 * heigth, labelWidth, labelHeigth);
        jp.add(order6Label);

        order6Body = getJTextField("", 2);
        order6Body.setBounds(x + 2 * labelWidth + textWidth + 3 * width, y + 3 * heigth, textWidth, textheigth);
        jp.add(order6Body);

        affirmButton = getJButton("确认");
        affirmButton.setBounds(x+labelWidth+width+(textWidth-60)/2,y+4*heigth,60,40);
        jp.add(affirmButton);

        clearButton = getJButton("清除");
        clearButton.setBounds(x+2*labelWidth+textWidth+2*width+(textWidth-60)/2,y+4*heigth,60,40);
        jp.add(clearButton);
        
        JLabel materialCodeLabel = getJLabel("物资编码:");  //
        materialCodeLabel.setBounds(x, y + 6 * heigth - 15, labelWidth, labelHeigth);
        jp.add(materialCodeLabel);

        materialCodeBody = getJTextField("", 1);
        materialCodeBody.setBounds(x + labelWidth + width, y + 6 * heigth - 15, textWidth, textheigth);
        jp.add(materialCodeBody);

        JLabel processCodeLabel = getJLabel("工序代码:");
        processCodeLabel.setBounds(x + labelWidth + textWidth + 2 * width, y + 6 * heigth - 15, labelWidth, labelHeigth);
        jp.add(processCodeLabel);

        processCodeBody = getJTextField("", 1);
        processCodeBody.setBounds(x + 2 * labelWidth + textWidth + 3 * width, y + 6 * heigth - 15, textWidth, textheigth);
        jp.add(processCodeBody);

//        JPanel recipePanel = new RecipeList();
//        recipePanel.setBounds(x+15,y+8*heigth+10,515,120);
//        add(recipePanel);

        String[] n = {"配方列表"};
//        myTable = new RecipeTable(n);
        myTable = new RecipeTable(recipeBody);
        JScrollPane vv = myTable.getMyTable();
        vv.setBounds(x + 15, y + 7 * heigth - 15, 515, 120);
        jp.add(vv);
        setMyTable(null);

        JLabel mesMessageTitle = getJLabel("MES消息:");
        mesMessageTitle.setBounds(x + 2 * labelWidth + 2 * textWidth + 3 * width + 40, y + 7 * heigth - 15, 70, 25);
        mesMessageTitle.setBackground(new Color(230, 240, 232));
        mesMessageTitle.setBorder(new EmptyBorder(0, 5, 0, 5));
        jp.add(mesMessageTitle);

        mesMessageBody = new JTextArea("");
        mesMessageBody.setBounds(x + 2 * labelWidth + 2 * textWidth + 3 * width + 50, y + 7 * heigth + 10, 260, 150);
        mesMessageBody.setBackground(new Color(254, 165, 0));
        mesMessageBody.setLineWrap(true);
        mesMessageBody.setWrapStyleWord(true);
        jp.add(mesMessageBody);

        cutNameBody = getJButton("切削资料：");
        cutNameBody.setBounds(x + 10, y + 6 * heigth + 140, 70, 25);
        jp.add(cutNameBody);

        cutNameTxt = getJTextField("", 1);
        cutNameTxt.setBounds(x + labelWidth + width, y + 6 * heigth + 140, 440, 25);
        jp.add(cutNameTxt);

        JLabel recipeLable = getJLabel("当前配方:");
        recipeLable.setBounds(x, y + 7 * heigth + 140, 80, 25);
        jp.add(recipeLable);

        recipeBody = getJTextField("", 1);
        recipeBody.setBounds(x + labelWidth + width, y + 7 * heigth + 140, 440, 25);
        jp.add(recipeBody);

//        JLabel XBody = getJLabel("位移X:");
//        XBody.setBounds(x, y + 7 * heigth + 170, 60, 25);
//        add(XBody);
//
//        XTxt = getJTextField("", 2);
//        XTxt.setBounds(80, y + 7 * heigth + 170, 80, 25);
//        add(XTxt);

//        JLabel YBody = getJLabel("位移Y:");
//        YBody.setBounds(160, y + 7 * heigth + 170, 60, 25);
//        add(YBody);

//        YTxt = getJTextField("", 2);
//        YTxt.setBounds(230, y + 7 * heigth + 170, 80, 25);
//        add(YTxt);

        recipeButton = getJButton("下发配方");
        recipeButton.setBounds(150, y + 7 * heigth + 170, 80, 25);
        jp.add(recipeButton);

        completeButton = getJButton("加工完成");
        completeButton.setBounds(400, y + 7 * heigth + 170, 80, 25);
        jp.add(completeButton);

        resetButton = getJButton("复位");
        resetButton.setBounds(630, y + 7 * heigth + 170, 80, 25);
        jp.add(resetButton);

        initListener();
    }

    public static JLabel getJLabel(String str) {
        JLabel jLabel = new JLabel(str, JLabel.RIGHT);
        jLabel.setOpaque(true);
        jLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        jLabel.setBackground(new Color(233, 240, 232));
        return jLabel;
    }

    public static JTextField getJTextField(String str, int flag) {
        JTextField jTextField = new JTextField(str);
        if (flag == 1) {
            jTextField.setEnabled(false);
            jTextField.setBackground(new Color(211, 211, 211));
        } else {
            jTextField.setEnabled(true);
        }
        jTextField.setBorder(BorderFactory.createLineBorder(new Color(173, 173, 173), 1));
        jTextField.setDisabledTextColor(new Color(102, 102, 153));
//        jTextField.setBorder(new EmptyBorder(0,5,0,5));
        jTextField.setForeground(new Color(102, 102, 153));
        jTextField.setFont(new Font("宋体", Font.PLAIN, 12));
        return jTextField;
    }

    public static JButton getJButton(String str) {
        JButton jButton = new JButton(str);
        jButton.setBackground(new Color(233, 240, 232));
        jButton.setBorder(new EmptyBorder(5, 30, 5, 5));
        jButton.setBorder(BorderFactory.createRaisedBevelBorder());
        return jButton;
    }

    public static JButton getAGVJButton(String str) {
        JButton jButton = new JButton(str);
        jButton.setPreferredSize(new Dimension(60, 30));
        jButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jButton.setBackground(new Color(233, 240, 232));
        jButton.setBorder(new EmptyBorder(5, 30, 5, 5));
        jButton.setBorder(BorderFactory.createRaisedBevelBorder());
        return jButton;
    }

    private static void initListener() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order1Body.setText("");
                order2Body.setText("");
                order3Body.setText("");
                order4Body.setText("");
                order5Body.setText("");
                order6Body.setText("");

                materialCodeBody.setText("");
                processCodeBody.setText("");
                setMyTable(null);
                recipeBody.setText("");
                mesMessageBody.setText("");
            }
        });

        modeBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (setFlag) {
//                    modeBody.setText("离线");
                    setFlag = false;
                } else {
//                    modeBody.setText("在线");
                    setFlag = true;
                }
            }
        });

        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        dischargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        errDichargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        affirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wipEntity = "";
                if (!order1Body.getText().replaceAll(" ", "").equals("")) {
                    if (wipEntity.equals("")) {
                        wipEntity += order1Body.getText().replaceAll(" ", "");
                    } else {
                        wipEntity += "," + order1Body.getText().replaceAll(" ", "");
                    }
                }
                if (!order2Body.getText().replaceAll(" ", "").equals("")) {
                    if (wipEntity.equals("")) {
                        wipEntity += order2Body.getText().replaceAll(" ", "");
                    } else {
                        wipEntity += "," + order2Body.getText().replaceAll(" ", "");
                    }
                }
                if (!order3Body.getText().replaceAll(" ", "").equals("")) {
                    if (wipEntity.equals("")) {
                        wipEntity += order3Body.getText().replaceAll(" ", "");
                    } else {
                        wipEntity += "," + order3Body.getText().replaceAll(" ", "");
                    }
                }
                if (!order4Body.getText().replaceAll(" ", "").equals("")) {
                    if (wipEntity.equals("")) {
                        wipEntity += order4Body.getText().replaceAll(" ", "");
                    } else {
                        wipEntity += "," + order4Body.getText().replaceAll(" ", "");
                    }
                }
                if (!order5Body.getText().replaceAll(" ", "").equals("")) {
                    if (wipEntity.equals("")) {
                        wipEntity += order5Body.getText().replaceAll(" ", "");
                    } else {
                        wipEntity += "," + order5Body.getText().replaceAll(" ", "");
                    }
                }
                if (!order6Body.getText().replaceAll(" ", "").equals("")) {
                    if (wipEntity.equals("")) {
                        wipEntity += order6Body.getText().replaceAll(" ", "");
                    } else {
                        wipEntity += "," + order6Body.getText().replaceAll(" ", "");
                    }
                }
                if (wipEntity.equals("")) {
                    JOptionPane.showMessageDialog(null, "工单ID不能为空");
                    return;
                }
                wipEntity.replaceAll(" ", "");
                tempRecipe = wipEntity;

                String message = "<Ingredients\n" +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n" +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
//                        "MacCode=\"" + StyleProperties.adminPage.configPropertySet.getPropertyStr("eqpID", "MAC001") + "\"\n" +
                        "WipEntity=\"" + tempRecipe + "\">\n" +
                        "</Ingredients>";
//                String result = stichInterface("Ingredients", message);  //TODO 配方下发

//                String result = " <s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">
//                <s:Body>
//                <macIntfResponse xmlns=\"http://scc.com.cn\">
//                <macIntfResult xmlns:a=\"http://schemas.datacontract.org/2004/07/FormWebServer\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">
//                <a:_x003C_errorCode_x003E_k__BackingField>0</a:_x003C_errorCode_x003E_k__BackingField>
//                <a:_x003C_errorMsg_x003E_k__BackingField>OK</a:_x003C_errorMsg_x003E_k__BackingField>
//                <a:_x003C_resultData_x003E_k__BackingField>\uFEFF&lt;?xml version=\"1.0\" encoding=\"utf-8\"?&gt;&#xD;\n" +
//                        "&lt;Ingredients xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" MacCode=\"AF2306007\" WipEntity=\"1681293001\"&gt;&#xD;\n" +
//                        "  &lt;item TagCode=\"AF2306007_1004\" TagValue=\"e301115418a002-070_CD-7syw06q.md34\" /&gt;&#xD;\n" +
//                        "&lt;/Ingredients&gt;</a:_x003C_resultData_x003E_k__BackingField>
//                </macIntfResult>
//                </macIntfResponse>
//                </s:Body>
//                </s:Envelope>";
//                dispatchRecipe(result);
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String message = C;
//                for (String recipeOne : CustomerClientImpl.upMessageList) {
//                    stichInterface("LotDataUpload", recipeOne);  //TODO 加工参数 ProductionData
//                }
//                CustomerClientImpl.upMessageList.clear();

            }
        });

/**
 *切削资料按钮： 点击触发选择切削文件，将选择的切削路径展示在 切削资料文本框
 */
        cutNameBody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
//
//                chooser.addChoosableFileFilter(new MyFileFilter("null", "文件"));
//
//                chooser.showOpenDialog(null);
//
//                File file = chooser.getSelectedFile();
//
//                cutNameTxt.setText(file.getPath());
//
//                set_cutNameDataPath.setPropertyStr("cutNamePath", file.getPath());
//
//                set_cutNameDataPath.saveProperties(); //保存到 cutNameDataPath.pro 文件

            }
        });

//        recipeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                loadRecipe();
//            }
//        });
    }

    public void setNUll() {
        order1Body.setText("");
        order2Body.setText("");
        order3Body.setText("");
        order4Body.setText("");
        order5Body.setText("");
        order6Body.setText("");

        materialCodeBody.setText("");
        processCodeBody.setText("");
        recipeBody.setText("");
        setMyTable(null);
        mesMessageBody.setText("");
    }

    public static void setMyTable(Vector tableValueList) {
        myTable.defaultTableModel = new DefaultTableModel(tableValueList, titleList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        myTable.table.setModel(myTable.defaultTableModel);
        myTable.table.requestFocus();
        myTable.table.editCellAt(0, 0);
        myTable.table.changeSelection(0, 0, false, false);
        myTable.table.getModel().removeTableModelListener(myTable.tableModelListener);
        myTable.tableModelListener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int col = e.getColumn();
                int row = e.getFirstRow();
            }
        };
        myTable.table.getModel().addTableModelListener(myTable.tableModelListener);
    }

//    public String stichInterface(String invoke, String message) {
//        String text = message.replaceAll("<", "&lt;");
//        text = text.replaceAll(">", "&gt;");
//        message = StyleProperties.getMessageToScc(invoke, text);
//        return CustomerClientImpl.StitchingInterface(invoke, message);
//    }

//    public static void dispatchRecipe(String message) {
//        String result = message;
//        recipeText = "";
//        message = message.split("<a:_x003C_resultData_x003E_k__BackingField>")[1].split("</a:_x003C_resultData_x003E_k__BackingField>")[0];
//        message = message.replaceAll("&lt;", "<");
//        message = message.replaceAll("&gt;", ">");
//        message = JsonAndXmlConvertor.xmlToJson(message);
//        JSONObject resultMap = JSON.parseObject(message);
//        Map secMap = (Map) resultMap.get("Ingredients");
//        Vector<Vector<Object>> recipeList = new Vector();
//        dataMap.clear();
//        try {
//            java.util.List MiList = (List) secMap.get("item");
//
//            for (Object iteamMap : MiList) {
//                System.out.println("--------------");
//                System.out.println(((Map) iteamMap).get("TagCode").toString().split("_")[1]);
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1004"))) {
//                    Vector a = new Vector();
//                    String filePath = (((Map) iteamMap).get("TagValue").toString().replaceAll("\\\\", "/"));
//                    a.add(getRecipePath(filePath));
//                    recipeList.add(a);
//                    continue;
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1000"))) {
//                    dataMap.put("001", ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1001"))) {
//                    dataMap.put("004", ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1002"))) {
//                    dataMap.put("005", ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1003"))) {
//                    dataMap.put("003", ((Map) iteamMap).get("TagValue"));
//                }
////                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1004"))){
////                    dataMap.put(((Map) iteamMap).get("TagCode").toString().split("_")[1],((Map) iteamMap).get("TagValue"));
////                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1005"))) {
//                    dataMap.put(((Map) iteamMap).get("TagCode").toString().split("_")[1], ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1006"))) {
//                    dataMap.put(((Map) iteamMap).get("TagCode").toString().split("_")[1], ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1010"))) {
//                    dataMap.put(((Map) iteamMap).get("TagCode").toString().split("_")[1], ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1011"))) {
//                    dataMap.put(((Map) iteamMap).get("TagCode").toString().split("_")[1], ((Map) iteamMap).get("TagValue"));
//                }
//                if ((((Map) iteamMap).get("TagCode").toString().split("_")[1].equals("1012"))) {
//                    dataMap.put(((Map) iteamMap).get("TagCode").toString().split("_")[1], ((Map) iteamMap).get("TagValue"));
//                }
//            }
//        } catch (Exception e) {
//            Map MiList = (Map) secMap.get("item");
//            Vector a = new Vector();
//            if ((MiList.get("TagCode").toString().split("_")[1].equals("1004"))) {
//                a.add(getRecipePath(MiList.get("TagValue").toString()));
//            }
//            recipeList.add(a);
//        }
//        if (recipeList.size() > 0) {
//            selectBox.removeAllItems();
//            recipeNum = tempRecipe;
//            Arrays.stream(recipeNum.split(",")).forEach(v -> selectBox.addItem(v));
//        }
//        setMyTable(recipeList);
//    }

    public String getRecipePath(String path) {
        if (path.startsWith("//")) {
            return "/" + (path.replaceAll("^//(.)*?/(.)*?/", ""));
        } else {
            return path;
        }
    }

//    public void sendNullJob() {
//        String filePath = StyleProperties.mainFrame.configPropertySet.getPropertyStr("NULLRECIPEFILE", "/dose/dncdat/mesAgentNullRecipeFile.txt");
//        File file = new File(filePath);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        String mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<schmoll_mes_message version=\"1\">\n" +
//                " <cmd_type>Write</cmd_type>\n" +
//                " <cmd_id>drill.JobCall</cmd_id>\n" +
//                " <msg_result>0</msg_result>\n" +
//                " <sender_id>MES Server</sender_id>\n" +
//                " <receiver_id>" + StyleProperties.machineID + "</receiver_id>\n" +
//                " <data>\n" +
//                " <param key=\"PrgName\" type=\"string\">" + "/mesAgentNullRecipeFile.txt" + "</param> \n" +
//                " <param key=\"PrgDrive\" type=\"string\">" + "/dose/dncdat" + "</param> \n" +
//                " <param key=\"CutName\" type=\"string\">" + "" + "</param> \n" +
//                " <param key=\"CutDrive\" type=\"string\">" + "/dose/dncdat" + "</param> \n";
//        mes = mes + " </data>\n" + "</schmoll_mes_message>";
//        mes = "SMDATMSG,1,schmoll_mes_message,application/xml," + mes.length() + "\n" +
//                mes +
//                "\nSMDATEND";
//        StyleProperties.mainFrame.schmollClientImpl.send(mes);
//    }

//    public void loadRecipe() {
//        int mes_num = 0;
//        String ts = tsFormat.format(new Date());
//        if (recipeBody.getText().equals("")) {
//            JOptionPane.showMessageDialog(null, "请先获取配方");
//        } else {
//            String recipeStr = "";
//            if (!recipeBody.getText().startsWith("/")) {
//                recipeStr = "/mnt/" + StyleProperties.mainFrame.configPropertySet.getPropertyStr("Disk", "net1") + "/" + recipeBody.getText();
//            } else {
//                recipeStr = "/mnt/" + StyleProperties.mainFrame.configPropertySet.getPropertyStr("Disk", "net1") + recipeBody.getText();
//            }
//            File file = new File(recipeStr);
//            if (!file.exists()) {
//                setNUll();
//                JOptionPane.showMessageDialog(null, "配方文件不存在，请重新扫码获取");
//                return;
//            }
//
//            if (!XTxt.getText().matches("^(-)?(((([1-9]\\d)|[0-9])(\\.\\d{1,3})?$|^0(\\.\\d{1,3})?)|((([1-9]\\d\\d))(\\.\\d{1,3})?$))$")
//                    | !YTxt.getText().matches("^(-)?(((([1-9]\\d)|[0-9])(\\.\\d{1,3})?$|^0(\\.\\d{1,3})?)|((([1-9]\\d\\d))(\\.\\d{1,3})?$))$")
//            ) {
//                JOptionPane.showMessageDialog(null, "涨缩系数设置不规范");
//                return;
//            }
//
//            String mes;
//            mes_num += 1;
//            sendNullJob();
//            try {
//                Thread.sleep(StyleProperties.mainFrame.configPropertySet.getPropertyInt("RECIPETIME", 10000));
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//
//            String cut = set_cutNameDataPath.getPropertyStr("cutNamePath", "");
//            String cutDrive = "";
//            String cutName = "";
//            if (!cut.isEmpty()) {
//                if(cut.contains("/")){
//                    int i = cut.lastIndexOf("/");
//                    cutDrive = cut.substring(0, i + 1);
//                    cutName = cut.substring(i + 1);
//                }
//            }
////            String VX = StyleProperties.setting.dataMap.get("1010").toString();
////            String VY = StyleProperties.setting.dataMap.get("1011").toString();
//            String VXY = StyleProperties.setting.dataMap.get("1012").toString();    //位移X，编号：(1010),位移Y，编号：(1011)  ---> 更改为(-255,-5.95)，编号：(1012)。
//            VXY = VXY.substring(1, VXY.length() - 1);
//            String VX = "";
//            String VY = "";
//            if(VXY.contains(",")){
//                VX = VXY.split(",")[0];
//                VY = VXY.split(",")[1];
//            }
//
//            mes = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                    "<schmoll_mes_message version=\"1\">\n" +
//                    " <cmd_type>Write</cmd_type>\n" +
//                    " <cmd_id>drill.JobCall</cmd_id>\n" +
//                    " <cmd_variant>" + mes_num + "</cmd_variant>\n" +
//                    " <msg_id>" + mes_num + "</msg_id>\n" +
//                    " <msg_result>0</msg_result>\n" +
//                    " <sender_id>MES Server</sender_id>\n" +
//                    " <receiver_id>" + StyleProperties.machineID + "</receiver_id>\n" +
//                    " <msg_timestamp>" + ts.substring(0, 26) + ":" + ts.substring(26) + "</msg_timestamp>\n" +
//                    " <data_timestamp>" + ts.substring(0, 26) + ":" + ts.substring(26) + "</data_timestamp>\n" +
//                    " <data>\n" +
//                    " <param key=\"PrgName\" type=\"string\">" + recipeBody.getText() + "</param> \n" +
//                    " <param key=\"PrgDrive\" type=\"string\">/mnt/" + StyleProperties.mainFrame.configPropertySet.getPropertyStr("Disk", "net1") + "</param> \n" +
//                    " <param key=\"CutName\" type=\"string\">" + cutName + "</param> \n" +
//                    " <param key=\"CutDrive\" type=\"string\">" + cutDrive + "</param> \n" +
//                    " <param key=\"VX\" type=\"string\">" + VX + "</param> \n" +
//                    " <param key=\"VY\" type=\"string\">" + VY + "</param> \n";
//            mes = mes + " </data>\n" + "</schmoll_mes_message>";
//            mes = "SMDATMSG,1,schmoll_mes_message,application/xml," + mes.length() + "\n" +
//                    mes +
//                    "\nSMDATEND";
////                    }
//            try {
////                FileMethod.machine("send_s50\n" + mes);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            recipeText = recipeBody.getText();
//            StyleProperties.mainFrame.schmollClientImpl.send(mes);
//            CustomerClientImpl.relRecipe = CustomerClientImpl.recipe;
//        }
//    }
}
