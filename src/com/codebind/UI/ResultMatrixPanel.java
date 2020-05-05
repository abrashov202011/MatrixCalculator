package com.codebind.UI;

import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Класс для вывода результатов в виде таблицы
 * @autor Абрашов
 * @version 1.0
 */
public class ResultMatrixPanel extends JPanel {
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param matrix матрица для вывода
     */
    public ResultMatrixPanel(Matrix matrix) {
        this.setLayout(new BorderLayout());
        ScrollPane scrollPane = new ScrollPane();
        JTable table = new MatrixTable(matrix, false).getTable();
        scrollPane.add(table);
        scrollPane.setMaximumSize(new Dimension(1000,500));
        scrollPane.setSize(new Dimension(100 * table.getRowCount() ,55 * table.getColumnCount()));
        this.add(scrollPane, BorderLayout.CENTER);
        JButton copyButton = new JButton("Копировать");
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Main.SavedMatrix = new Matrix(table);
            }
        });
        this.add(copyButton,BorderLayout.NORTH);
    }
}
