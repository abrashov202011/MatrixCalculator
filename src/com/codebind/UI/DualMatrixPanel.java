package com.codebind.UI;

import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DualMatrixPanel extends JPanel {
    JPanel bottomPanel;
    JPanel topPanel;
    MatrixTable matrixTable1;
    MatrixTable matrixTable2;
    ScrollPane scrollPane1;
    ScrollPane scrollPane2;
    int rowsCount1;
    int columnCount1;
    int rowsCount2;
    int columnCount2;
    JPanel matrixContainer;
    /**
     * Конструктор - создание нового объекта
     */
    public DualMatrixPanel(int rows1, int columns1, int rows2, int columns2) {
        this.setLayout(new BorderLayout());
        addMatrix(rows1, columns1, rows2,columns2);
        bottomPanel = new JPanel(new GridLayout());
        this.add(bottomPanel,BorderLayout.SOUTH);
        addAdditionButton();
        //addInverseButton();
        //addTransposeButton();
        //addMatrixSizeFilds(rows, columns);
        addClearButton();
    }
    private void addAdditionButton() {
        JButton btn = new JButton("Сложение");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rowsCount1 != rowsCount2 || columnCount1 != columnCount2) {
                    showWarning("Невозможно сложить матрицы разной рамерности");
                } else {
                    try {
                        Matrix matrix1 = new Matrix(matrixTable1.getTable());
                        Matrix matrix2 = new Matrix(matrixTable2.getTable());
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
    private void showMatrixResult(Matrix matrix, String title) {
        final JDialog frame = new JDialog((JFrame) SwingUtilities.getWindowAncestor(Main.mainPanel), title, true);
        frame.getContentPane().add(new ResultMatrixPanel(matrix));
        frame.setMinimumSize(new Dimension(200,0));
        frame.pack();
        frame.setVisible(true);
    }
    private void addMatrixSizeFilds(int rows, int columns) {
        topPanel = new JPanel(new GridLayout());
        JTextField rowCount = new JTextField();
        rowCount.setText(String.valueOf(rows));
        JLabel rowLabel = new JLabel("Количество строк");
        JTextField columnCount = new JTextField(columns);
        columnCount.setText(String.valueOf(columns));
        JLabel columnLabel = new JLabel("Количество столбцов");
        topPanel.add(rowLabel);
        topPanel.add(rowCount);
        topPanel.add(columnLabel);
        topPanel.add(columnCount);
        this.add(topPanel, BorderLayout.NORTH);
        JButton btn = new JButton("Применить");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //addMatrix(Integer.parseInt(rowCount.getText()),Integer.parseInt(columnCount.getText()));
            }
        });
        topPanel.add(btn);
    }
    private void addMatrix(int rows1, int columns1, int rows2, int columns2){
        if(scrollPane1 != null)
            this.remove(scrollPane1);
        if(scrollPane2 != null)
            this.remove(scrollPane2);
        this.columnCount1 = columns1;
        this.rowsCount1 = rows1;
        this.columnCount2 = columns2;
        this.rowsCount2 = rows2;
        this.matrixTable1 = new MatrixTable(rows1, columns1);
        this.matrixTable2 = new MatrixTable(rows2, columns2);
        this.scrollPane1 = new ScrollPane();
        this.scrollPane2 = new ScrollPane();
        matrixContainer = new JPanel(new GridLayout());
        this.add(matrixContainer,BorderLayout.CENTER);
        matrixContainer.add(this.scrollPane1);
        matrixContainer.add(this.scrollPane2);
        this.scrollPane1.add(matrixTable1.getTable(), BorderLayout.CENTER);
        this.scrollPane2.add(matrixTable2.getTable(), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    private void addClearButton() {
        JButton btn = new JButton("Очистить");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMatrix(rowsCount1,columnCount1,rowsCount2,columnCount2);
            }
        });
        bottomPanel.add(btn);
    }
    private void showWarning(String message) {
        JOptionPane.showMessageDialog(null,message);
    }
}
