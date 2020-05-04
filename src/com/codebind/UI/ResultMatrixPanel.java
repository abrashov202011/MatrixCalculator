package com.codebind.UI;

import com.codebind.Classes.Matrix;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultMatrixPanel extends JPanel {
    /**
     * Конструктор - создание нового объекта
     */
    public ResultMatrixPanel(Matrix matrix) {
        this.setLayout(new GridLayout());
        ScrollPane scrollPane = new ScrollPane();
        JTable table = new MatrixTable(matrix).getTable();
        scrollPane.add(new MatrixTable(matrix).getTable());
        scrollPane.setMaximumSize(new Dimension(1000,500));
        scrollPane.setSize(new Dimension(100 * table.getRowCount() ,50 * table.getColumnCount()));
        this.add(scrollPane);
    }
}
