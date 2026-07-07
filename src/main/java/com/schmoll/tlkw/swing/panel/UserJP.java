package com.schmoll.tlkw.swing.panel;



import com.schmoll.tlkw.db.UserTable;
import com.schmoll.tlkw.swing.MainForm;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UserJP {
    //用户管理
    public static UserTable userTable;

    public static JTextField usernameBody;
    public static JPasswordField passwordBody;
    public static JComboBox<String> roleBody;
    public static JTextField selectBody;
    public static JButton addButton;
    public static JButton deleteButton;
    public static JButton selectButton;
    public static JButton modifyButton;
    public static JButton saveButton;

    public static Vector<Object> titleList;
    public static String[] tableTitle = { "序号","用户名","密码","角色"};

    public static void userJPanel(JPanel jp){
        jp.setLayout(null);

        titleList = new Vector<>();
        titleList.add("序号");
        titleList.add("用户名");
        titleList.add("密码");
        titleList.add("角色");

        JLabel tableLable = MainForm.getJL("用户管理",true,0,0,890,30,false);
        tableLable.setBounds(0,0,870,30);
        tableLable.setHorizontalAlignment(SwingConstants.CENTER);
        tableLable.setBackground(new Color(186,192,185));
        jp.add(tableLable);

        userTable = new UserTable(tableTitle);
        userTable.table.getColumnModel().getColumn(0).setPreferredWidth(40);
        // 设置第二列宽度为120
        userTable.table.getColumnModel().getColumn(1).setPreferredWidth(120);
        userTable.table.getColumnModel().getColumn(2).setPreferredWidth(220);
        JScrollPane vv = userTable.getMyTable();
        vv.setBounds(1,30,867,280);
        jp.add(vv);
//        setMyTable(DbClass.selectAll());

        JLabel usernameLable = MainForm.getJL("用户名:",true,10,320,80,25,false);
        jp.add(usernameLable);

        usernameBody = MainForm.getJF("",90,320,100,25);
        jp.add(usernameBody);

        JLabel passwordLable = MainForm.getJL("密码:",true,220,320,80,25,false);
        jp.add(passwordLable);

        passwordBody = new JPasswordField();
//        passwordBody.setBounds(300,320,100,25);
        passwordBody.setBounds((int) Math.round(280 * MainForm.w_s80), (int) Math.round(320 * MainForm.h_s80), (int) Math.round(100 * MainForm.w_s80), (int) Math.round(25 * MainForm.h_s80));
        jp.add(passwordBody);

        JLabel roleLable=MainForm.getJL("角色:",true,410,320,80,25,false);
        jp.add(roleLable);

        roleBody = new JComboBox<String>();
        roleBody.addItem("操作员");
        roleBody.addItem("工程师");       // 创建下拉选项
        roleBody.addItem("管理员");
        roleBody.setBounds(490,320,80,25);
        roleBody.setBounds((int) Math.round(490 * MainForm.w_s80), (int) Math.round(320 * MainForm.h_s80), (int) Math.round(80 * MainForm.w_s80), (int) Math.round(25 * MainForm.h_s80));
        jp.add(roleBody);

        JLabel selectLable = MainForm.getJL("查找字段:",true,620,320,80,25,false);
        jp.add(selectLable);

        selectBody = MainForm.getJF("",700,320,100,25);
        jp.add(selectBody);

        addButton = new JButton("添加");
        addButton.setBounds(40,370,80,25);
//        addButton.setBackground(MainForm.white);
        jp.add(addButton);

        deleteButton = new JButton("删除");
        deleteButton.setBounds(210,370,80,25);
//        deleteButton.setBackground(MainForm.white);
        jp.add(deleteButton);

        selectButton = new JButton("查找");
        selectButton.setBounds(380,370,80,25);
//        selectButton.setBackground(MainForm.white);
        jp.add(selectButton);

        modifyButton = new JButton("修改");
        modifyButton.setBounds(550,370,80,25);
//        modifyButton.setBackground(MainForm.white);
        jp.add(modifyButton);

        saveButton = new JButton("保存");
        saveButton.setBounds(720,370,80,25);
//        saveButton.setBackground(MainForm.white);
        jp.add(saveButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println("addButton");
                String password = "";

                refresh();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println("deleteButton");
                if (userTable.user.getName().equals("admin")){
                    JOptionPane.showMessageDialog(null, "admin用户不可删除!!!");
                    return;
                }
                refresh();
            }
        });

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println("selectButton");
                refresh();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println("modifyButton");
                usernameBody.setText(userTable.user.getName());
                roleBody.setSelectedItem(userTable.user.getRole());
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                System.out.println("saveButton");
                if (userTable.user.getName().equals("admin")){
                    JOptionPane.showMessageDialog(null, "admin用户不可修改!!!");
                    return;
                }
                String password = "";

                refresh();
            }
        });
    }

    public static void setMyTable(Vector tableValueList){
        userTable.defaultTableModel = new DefaultTableModel(tableValueList, titleList) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable.table.setModel(userTable.defaultTableModel);
        userTable.table.requestFocus();
        userTable.table.editCellAt(0, 0);
        userTable.table.changeSelection(0, 0, false, false);
        userTable.table.getModel().removeTableModelListener(userTable.tableModelListener);
        userTable.tableModelListener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                System.out.println(userTable.table.getValueAt(row, col));
            }
        };
        userTable.table.getModel().addTableModelListener(userTable.tableModelListener);
    }

    public static void refresh(){
        if (selectBody.getText().replaceAll(" ","").equals("")|selectBody.getText()==null){
//            setMyTable(DbClass.selectAll());
        }else{
//            setMyTable(DbClass.selectByFilt(selectBody.getText().replaceAll(" ","")));
        }
        selectBody.setText("");
        clearJtext();
    }

    public static void clearJtext(){
        usernameBody.setText("");
        passwordBody.setText("");
        roleBody.setSelectedIndex(0);
    }
}
