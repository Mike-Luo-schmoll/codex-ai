package com.schmoll.tlkw.db;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class UserTable implements ListSelectionListener {

    public JTable table = null;
    ListSelectionModel selectionMode = null;
    TableCellEditor oldEditor;
    JScrollPane s;
    JTextField recipeText;
    public int userID = 0;
    public User user = new User();
    public DefaultTableModel defaultTableModel;
    public TableModelListener tableModelListener;
    String[] n = { "配方列表"};

    public UserTable() {
        intTable();
    }

    public UserTable(JTextField jTextField, String[] n) {
        this.recipeText = jTextField;
        this.n = n;
        intTable();
    }
    public UserTable(String[] n) {
        this.n = n;
        intTable();
    }

    public UserTable(JTextField jTextField) {
        intTable();
        Font customFont = new Font("宋体",Font.PLAIN,12);
        jTextField.setFont(customFont);
        this.recipeText = jTextField;
    }

    public void intTable(){
        Object[][] p = {{}};
        defaultTableModel = new DefaultTableModel(p,n){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table = new JTable(defaultTableModel);
        table.setRowHeight(35);
        table.setPreferredScrollableViewportSize(new Dimension(150, 80));
        table.setCellSelectionEnabled(true);

        selectionMode = table.getSelectionModel();
        selectionMode.addListSelectionListener(this);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        selectionMode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Font customFont = new Font("宋体",Font.PLAIN,12);
        table.setFont(customFont);
        s = new JScrollPane();
        s.setViewportView(table);
        table.requestFocus();
        table.editCellAt(0,0);
        table.changeSelection(0,0,false, false);
        table.setFont(customFont);
        tableModelListener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int col = e.getColumn();
                int row = e.getFirstRow();
                oldEditor = table.getCellEditor();
                table.getCellEditor();
                Font customFont = new Font("宋体",Font.PLAIN,12);
                recipeText.setFont(customFont);
                recipeText.setText(String.valueOf(table.getValueAt(row,col)));
            }
        };

        table.getModel().addTableModelListener(tableModelListener);
    }

    public JScrollPane getMyTable(){
        return s;
    }
    /**
     * 当用户选取表格数据时会出发ListSelectionEvent,使用ListSelectionListener界面来处理这一事件
     */
    public void valueChanged(ListSelectionEvent e) {
        /**
         * 当用户选取表格数据时会出发ListSelectionEvent,使用ListSelectionListener界面来处理这一事件
         */
        try{
            userID = Integer.valueOf(table.getValueAt(table.getSelectedRow(),0).toString());
            user.setName(table.getValueAt(table.getSelectedRow(),1).toString());
            user.setRole(table.getValueAt(table.getSelectedRow(),3).toString());
        }catch (Exception e1){
            try {
                userID = Integer.valueOf(table.getValueAt(0,0).toString());
                user.setName(table.getValueAt(0,1).toString());
                user.setRole(table.getValueAt(0,3).toString());
            }catch (Exception m){
            }
        }

    }
}
