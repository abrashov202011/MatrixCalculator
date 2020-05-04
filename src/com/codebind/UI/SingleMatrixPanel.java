package com.codebind.UI;

import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingleMatrixPanel extends JPanel {
    JPanel bottomPanel;
    /**
     * Конструктор - создание нового объекта
     */
    public SingleMatrixPanel(int rows, int columns) {
        this.setLayout(new BorderLayout());
        MatrixTable matrixTable = new MatrixTable(rows, columns);
        this.add(matrixTable.getTable(), BorderLayout.CENTER);
        bottomPanel = new JPanel(new GridLayout());
        this.add(bottomPanel,BorderLayout.SOUTH);
        addDetermimantButton(matrixTable);
        addInverseButton(matrixTable);

    }
    private void addDetermimantButton(MatrixTable matrixTable) {
        JButton btn = new JButton("Определитель");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    Matrix matrix = new Matrix(matrixTable.getTable());
                    float determinant = matrix.getDeterminant();
                    JOptionPane.showMessageDialog(null,"Определитель равен " + determinant);
                }
                catch (Error ex) {
                    ShowWarning(ex.getMessage());
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void addInverseButton(MatrixTable matrixTable) {
        JButton btn = new JButton("Обращение");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    Matrix matrix = new Matrix(matrixTable.getTable());
                    Matrix inverseMatrix = matrix.inverse();
                    final JDialog frame = new JDialog((JFrame) SwingUtilities.getWindowAncestor(Main.mainPanel), "Обращенная матрица", true);
                    frame.getContentPane().add(new ResultMatrixPanel(inverseMatrix));
                    frame.setMinimumSize(new Dimension(200,0));
                    frame.pack();
                    frame.setVisible(true);
                }
                catch (Error ex) {
                    ShowWarning(ex.getMessage());
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void ShowWarning(String message) {
        JOptionPane.showMessageDialog(null,message);
    }
}
