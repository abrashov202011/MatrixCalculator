package com.codebind.UI;

import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingleMatrixPanel extends JPanel {
    JPanel bottomPanel;
    JPanel topPanel;
    MatrixTable matrixTable;
    ScrollPane scrollPane;
    int rowsCount;
    int columnCount;
    /**
     * Конструктор - создание нового объекта
     */
    public SingleMatrixPanel(int rows, int columns) {
        this.setLayout(new BorderLayout());
        addMatrix(rows, columns);
        bottomPanel = new JPanel(new GridLayout());
        this.add(bottomPanel,BorderLayout.SOUTH);
        addDetermimantButton();
        addInverseButton();
        addTransposeButton();
        addMatrixSizeFilds(rows, columns);
        addClearButton();
    }
    private void addDetermimantButton() {
        JButton btn = new JButton("Определитель");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(rowsCount != columnCount) {
                    showWarning("Невозможно вычислить определитель у не квадратной матрицы");
                } else {
                    try {
                        Matrix matrix = new Matrix(matrixTable.getTable());
                        float determinant = matrix.getDeterminant();
                        JOptionPane.showMessageDialog(null, "Определитель равен " + determinant);
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void addInverseButton() {
        JButton btn = new JButton("Обращение");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(rowsCount != columnCount) {
                    showWarning("Невозможно обратить не квадратную матрицу");
                } else {
                    try {
                        Matrix matrix = new Matrix(matrixTable.getTable());
                        Matrix inverseMatrix = matrix.inverse();
                        showMatrixResult(matrix.inverse(), "Обращенная матрица");
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    private void addTransposeButton() {
        JButton btn = new JButton("Транспонирование");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    Matrix matrix = new Matrix(matrixTable.getTable());
                    Matrix inverseMatrix = matrix.transpose();
                    showMatrixResult(matrix.transpose(), "Транспонированна матрица");
                }
                catch (Error ex) {
                    showWarning(ex.getMessage());
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
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addMatrix(Integer.parseInt(rowCount.getText()),Integer.parseInt(columnCount.getText()));
            }
        });
        topPanel.add(btn);
    }
    private void addMatrix(int rows, int columns){
        if(scrollPane != null)
            this.remove(scrollPane);
        this.columnCount = columns;
        this.rowsCount = rows;
        this.matrixTable = new MatrixTable(rows, columns);
        this.scrollPane = new ScrollPane();
        this.add(this.scrollPane);
        this.scrollPane.add(matrixTable.getTable(), BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    private void addClearButton() {
        JButton btn = new JButton("Очистить");
        int rows = matrixTable.getTable().getRowCount();
        int columns = matrixTable.getTable().getColumnCount();
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addMatrix(rows,columns);
            }
        });
        bottomPanel.add(btn);
    }
    private void showWarning(String message) {
        JOptionPane.showMessageDialog(null,message);
    }
}
