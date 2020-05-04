package com.codebind.UI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

public class MatrixTable {
    JTable matrix;
    public MatrixTable(int rows, int columns) {
        String[] columnNames = new String[columns];
        for(int i = 0; i < columns; i++){
            columnNames[i] = String.valueOf(i +1);
        }
        Object[][] data = new Object[rows][columns];
        matrix = new JTable(data, columnNames);
        var columnModel = matrix.getColumnModel();
        var a = matrix.isCellEditable(0,0);
        for(int i = 0; i < columns; i++) {
            columnModel.getColumn(i).setMaxWidth(40);
        }
        matrix.setRowHeight(40);

    }
    public JTable getMatrix() {
        return matrix;
    }

}
