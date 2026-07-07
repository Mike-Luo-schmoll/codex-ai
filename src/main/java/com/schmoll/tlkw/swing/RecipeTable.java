package com.schmoll.tlkw.swing;


import com.schmoll.tlkw.swing.panel.G3JP;

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
public class RecipeTable implements ListSelectionListener {

    public JTable table = null;
    ListSelectionModel selectionMode = null;
    TableCellEditor oldEditor;
    JScrollPane s;
    JTextField recipeText;
    public int userID;
    public DefaultTableModel defaultTableModel;
    public TableModelListener tableModelListener;
    String[] n = { "配方列表"};

    public RecipeTable() {
        intTable();
    }

    public RecipeTable(JTextField jTextField, String[] n) {
        this.recipeText = jTextField;
        this.n = n;
        intTable();
    }
    public RecipeTable(String[] n) {
        this.n = n;
        intTable();
    }

    public RecipeTable(JTextField jTextField) {
        intTable();
        this.recipeText = jTextField;
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
                oldEditor = table.getCellEditor();
                table.getCellEditor();
                recipeText.setText(String.valueOf(table.getValueAt(row,col)));
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
//            StyleProperties.setting.recipeBody.setText(table.getValueAt(table.getSelectedRow(),0).toString());
            G3JP.prg.setText(table.getValueAt(table.getSelectedRow(),0).toString());
        }catch (Exception e1){
            try {
                G3JP.prg.setText(table.getValueAt(0,0).toString());
//                StyleProperties.setting.recipeBody.setText(table.getValueAt(0,0).toString());
            }catch (Exception m){
            }
        }
    }
}