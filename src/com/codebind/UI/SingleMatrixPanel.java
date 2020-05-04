package com.codebind.UI;

import com.codebind.Classes.Matrix;

import javax.swing.*;
import java.awt.*;

public class SingleMatrixPanel extends JPanel {
    /**
     * Конструктор - создание нового объекта
     */
    public SingleMatrixPanel(int rows, int columns) {
        this.setLayout(new BorderLayout());
        MatrixTable matrixTable = new MatrixTable(rows, columns);
        this.add(matrixTable.getMatrix(), BorderLayout.CENTER);

        JButton btn = new JButton("Определитель");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    Matrix matrix = new Matrix(matrixTable.getMatrix());
                    float determinant = matrix.getDeterminant();
                    JOptionPane.showMessageDialog(null,"Определитель равен " + determinant);
                }
                catch (Error ex) {
                    ShowWarning(ex.getMessage());
                }

            }
        });
        this.add(btn,BorderLayout.SOUTH);
    }
    private void ShowWarning(String message) {
        JOptionPane.showMessageDialog(null,message);
    }
}
