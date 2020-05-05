package com.codebind.UI;

import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DualMatrixPanel extends JPanel {
    JPanel bottomPanel;
    SingleMatrixPanel singleMatrixPanel1;
    SingleMatrixPanel singleMatrixPanel2;
    JPanel mainMatrixContainer;
    /**
     * Конструктор - создание нового объекта
     */
    public DualMatrixPanel(int rows1, int columns1, int rows2, int columns2) {
        this.setLayout(new BorderLayout());
        addMatrix(rows1, columns1, rows2,columns2);
        bottomPanel = new JPanel(new GridLayout());
        this.add(bottomPanel,BorderLayout.SOUTH);
        addAdditionButton();
        addMultiplyButton();
        addSubtractionButton();
    }
    private void addAdditionButton() {
        JButton btn = new JButton("Сложение");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(singleMatrixPanel1.matrixTable.getTable().getCellEditor() != null)
                    singleMatrixPanel1.matrixTable.getTable().getCellEditor().stopCellEditing();
                if(singleMatrixPanel2.matrixTable.getTable().getCellEditor() != null)
                    singleMatrixPanel2.matrixTable.getTable().getCellEditor().stopCellEditing();
                int rowsCount1 = singleMatrixPanel1.matrixTable.getTable().getRowCount();
                int columnCount1 = singleMatrixPanel1.matrixTable.getTable().getColumnCount();
                int rowsCount2 = singleMatrixPanel2.matrixTable.getTable().getRowCount();
                int columnCount2 = singleMatrixPanel2.matrixTable.getTable().getColumnCount();
                if (rowsCount1 != rowsCount2 || columnCount1 != columnCount2) {
                    showWarning("Невозможно сложить матрицы разной рамерности");
                } else {
                    try {
                        Matrix matrix1 = new Matrix(singleMatrixPanel1.matrixTable.getTable());
                        Matrix matrix2 = new Matrix(singleMatrixPanel2.matrixTable.getTable());
                        Matrix matrixResult = Matrix.addition(matrix1, matrix2);
                        showMatrixResult(matrixResult, "Результат сложения матриц");
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void addSubtractionButton() {
        JButton btn = new JButton("Вычитание");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(singleMatrixPanel1.matrixTable.getTable().getCellEditor() != null)
                    singleMatrixPanel1.matrixTable.getTable().getCellEditor().stopCellEditing();
                if(singleMatrixPanel2.matrixTable.getTable().getCellEditor() != null)
                    singleMatrixPanel2.matrixTable.getTable().getCellEditor().stopCellEditing();
                int rowsCount1 = singleMatrixPanel1.matrixTable.getTable().getRowCount();
                int columnCount1 = singleMatrixPanel1.matrixTable.getTable().getColumnCount();
                int rowsCount2 = singleMatrixPanel2.matrixTable.getTable().getRowCount();
                int columnCount2 = singleMatrixPanel2.matrixTable.getTable().getColumnCount();
                if (rowsCount1 != rowsCount2 || columnCount1 != columnCount2) {
                    showWarning("Невозможно вычислить разность матриц разной рамерности");
                } else {
                    try {
                        Matrix matrix1 = new Matrix(singleMatrixPanel1.matrixTable.getTable());
                        Matrix matrix2 = new Matrix(singleMatrixPanel2.matrixTable.getTable());
                        Matrix matrixResult = Matrix.subtraction(matrix1, matrix2);
                        showMatrixResult(matrixResult, "Результат вычитания матриц");
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void addMultiplyButton() {
        JButton btn = new JButton("Умножение");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(singleMatrixPanel1.matrixTable.getTable().getCellEditor() != null)
                    singleMatrixPanel1.matrixTable.getTable().getCellEditor().stopCellEditing();
                if(singleMatrixPanel2.matrixTable.getTable().getCellEditor() != null)
                    singleMatrixPanel2.matrixTable.getTable().getCellEditor().stopCellEditing();
                int columnCount1 = singleMatrixPanel1.matrixTable.getTable().getColumnCount();
                int rowsCount2 = singleMatrixPanel2.matrixTable.getTable().getRowCount();
                if (columnCount1 != rowsCount2 ) {
                    showWarning("Число столбцов матрицы 1 должно быть равно числу строк матрицы 2");
                } else {
                    try {
                        Matrix matrix1 = new Matrix(singleMatrixPanel1.matrixTable.getTable());
                        Matrix matrix2 = new Matrix(singleMatrixPanel2.matrixTable.getTable());
                        Matrix matrixResult = Matrix.multiply(matrix1, matrix2);
                        showMatrixResult(matrixResult, "Результат умножения матриц");
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void showMatrixResult(Matrix matrix, String title) {
        final JDialog frame = new JDialog((JFrame) SwingUtilities.getWindowAncestor(Main.mainPanel), title, true);
        frame.getContentPane().add(new ResultMatrixPanel(matrix));
        frame.setMinimumSize(new Dimension(200,0));
        frame.pack();
        frame.setVisible(true);
    }
    private void addMatrix(int rows1, int columns1, int rows2, int columns2){
        if(mainMatrixContainer != null)
            this.remove(mainMatrixContainer);
        mainMatrixContainer = new JPanel(new GridLayout());
        this.add(mainMatrixContainer,BorderLayout.CENTER);
        singleMatrixPanel1 = new SingleMatrixPanel(rows1,columns1);
        singleMatrixPanel2 = new SingleMatrixPanel(rows2,columns2);
        mainMatrixContainer.add(singleMatrixPanel1);
        mainMatrixContainer.add(singleMatrixPanel2);
        this.revalidate();
        this.repaint();
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(null,message);
    }
}
