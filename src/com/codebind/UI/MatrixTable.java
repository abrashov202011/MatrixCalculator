package com.codebind.UI;

import com.codebind.Classes.Matrix;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;

public class MatrixTable {
    JTable table;
    public MatrixTable(int rows, int columns) {
        String[] columnNames = new String[columns];
        for(int i = 0; i < columns; i++){
            columnNames[i] = String.valueOf(i +1);
        }
        Object[][] data = new Object[rows][columns];
        table = new JTable(data, columnNames);
        var columnModel = table.getColumnModel();
        for(int i = 0; i < columns; i++) {
            columnModel.getColumn(i).setMaxWidth(40);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            columnModel.getColumn(i).setCellRenderer( centerRenderer );
        }
        table.setRowHeight(40);

    }

    public MatrixTable(Matrix matrix) {
        int rows = matrix.getMatrix().length;
        int columns = matrix.getMatrix()[0].length;

        String[] columnNames = new String[columns];
        for(int i = 0; i < columns; i++){
            columnNames[i] = String.valueOf(i +1);
        }
        Object[][] data = new Object[rows][columns];
        DecimalFormat df = new DecimalFormat("#.##");
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data.length; j++) {
                data[i][j] = df.format(matrix.getMatrix()[i][j]);
            }
        }
        table = new JTable(data, columnNames);
        var columnModel = table.getColumnModel();
        for(int i = 0; i < columns; i++) {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            columnModel.getColumn(i).setCellRenderer( centerRenderer );
        }
        table.setRowHeight(40);

    }

    public JTable getTable() {
        return table;
    }

}
