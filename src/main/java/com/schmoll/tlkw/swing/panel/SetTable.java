package com.schmoll.tlkw.swing.panel;



import com.schmoll.tlkw.db.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * 这段代码展示如何创建选择器，和使用选择器来以不同的方式选择表格中的单元格
 */
public class SetTable implements ListSelectionListener {

    public JTable table = null;
    ListSelectionModel selectionMode = null;
    TableCellEditor oldEditor;
    JScrollPane s;
    JTextField recipeText;
    public int userID;
    public User user = new User();
    public DefaultTableModel defaultTableModel;
    public TableModelListener tableModelListener;
    String[] n = { "配方列表"};

    public SetTable() {
        this(new String[]{"配方列表"});
    }

    public SetTable(JTextField jTextField, String[] n) {
        this.n = n;
        this.recipeText = jTextField;
        intTable();
    }
    public SetTable(String[] n) {
        this.n = n;
        intTable();
    }

    public SetTable(JTextField jTextField) {
        this.recipeText = jTextField;
        intTable();
    }

    public void intTable(){
        Object[][] p = {{}};
        defaultTableModel = new DefaultTableModel(p,n){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column==2){
                    return true;
                }else{
                    return false;
                }
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
        s = new JScrollPane();
        s.setViewportView(table);
        table.requestFocus();
        table.editCellAt(0,0);
        table.changeSelection(0,0,false, false);

        tableModelListener = new TableModelListener()
        {
            public void tableChanged(TableModelEvent e)
            {
                int col = e.getColumn();
                int row = e.getFirstRow();
                if (row < 0 || col < 0 || table.getRowCount() == 0 || row >= table.getRowCount()) {
                    return;
                }

                // 3. 关键修复：检查表格是否真的有数据
                // 如果表格的行数为 0，或者请求的行号 >= 总行数，直接返回
                oldEditor = table.getCellEditor();
                try {
                    Object value = table.getValueAt(row, col);
                    if (recipeText != null) { // 防止 recipeText 未初始化
                        recipeText.setText(String.valueOf(value));
                    }
                } catch (Exception ex) {
                    // 捕获潜在的其他异常，防止界面卡死
                    ex.printStackTrace();
                }
            }
        };

        table.getModel().addTableModelListener(tableModelListener);
    }
    
    public JScrollPane getMyTable(){
        return s;
    }

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