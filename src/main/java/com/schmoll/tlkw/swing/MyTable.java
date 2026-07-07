package com.schmoll.tlkw.swing;

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
public class MyTable implements ListSelectionListener {

    public JTable table = null;
    ListSelectionModel selectionMode = null;
    TableCellEditor oldEditor;
    JScrollPane s;
    JTextField recipeText;
    public DefaultTableModel defaultTableModel;
    public TableModelListener tableModelListener;
    String[] n = { "配方列表"};

    public MyTable() {
        intTable();
    }

    public MyTable(JTextField jTextField, String[] n) {
        this.recipeText = jTextField;
        this.n = n;
        intTable();
    }
    public MyTable(String[] n) {
        this.n = n;
        intTable();
    }

    public MyTable(JTextField jTextField) {
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
        StringBuilder tempString = new StringBuilder();
        int[] rows = table.getSelectedRows();
        int[] columns = table.getSelectedColumns();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                table.getColumnModel().getColumn(columns[j]).setCellEditor(oldEditor);
                tempString.append("");
                tempString.append(table.getValueAt(rows[i], columns[j]));
            }
        }
        if (this.recipeText != null){
            MainForm.prgTxt.setText(tempString.toString());
        }
    }
}