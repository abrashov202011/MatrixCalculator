package com.codebind.UI;

import com.codebind.Classes.FileOperations;
import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Класс для вывода окна с 1 матрицей
 * @autor Абрашов
 * @version 1.0
 */
public class SingleMatrixPanel extends JPanel {
    /** Поле для вывода нижней панели с кнопками*/
    JPanel bottomPanel;
    /** Поле для вывода верхней панели для ввода размера матрицы*/
    JPanel topPanel;
    /** Поле для вывода матрицы*/
    MatrixTable matrixTable;
    /** Поле для вывода контейнера для матрицы*/
    ScrollPane scrollPane;
    /** Поле для для хранения количества строк*/
    int rowsCount;
    /** Поле для для хранения количества столбцов*/
    int columnsCount;
    /** Поле для для вывода поял ввода количества столбцов*/
    JTextField rowsCountTextField;
    /** Поле для для вывода поял ввода количества строк*/
    JTextField columnsCountTextField;
    /**
     * Конструктор - создание нового объекта определенной размерности
     */
    public SingleMatrixPanel(int rows, int columns) {
        this.setLayout(new BorderLayout());
        addMatrixSizeFilds(rows, columns);
        addMatrix(rows, columns, null);
        bottomPanel = new JPanel(new GridLayout(2,3));
        this.add(bottomPanel,BorderLayout.SOUTH);
        addDetermimantButton();
        addInverseButton();
        addTransposeButton();
        addCopyButton();
        addPasteButton();
        addClearButton();

    }
    /**
     * Функция для добавления кнопки вычисления определителя матрицы
     */
    private void addDetermimantButton() {
        JButton btn = new JButton("Определитель");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(matrixTable.getTable().getCellEditor() != null)
                    matrixTable.getTable().getCellEditor().stopCellEditing();
                if(rowsCount != columnsCount) {
                    showWarning("Невозможно вычислить определитель у не квадратной матрицы");
                } else {
                    try {
                        Matrix matrix = new Matrix(matrixTable.getTable());
                        float determinant = matrix.getDeterminant();
                        FileOperations.writeOneMatrixOperationToFile(matrix.toString(), String.valueOf(determinant) + System.lineSeparator(), "DET");
                        JOptionPane.showMessageDialog(null, "Определитель равен " + determinant);
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    /**
     * Функция для добавления кнопки вычисления обратной матрицы
     */
    private void addInverseButton() {
        JButton btn = new JButton("Обращение");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(matrixTable.getTable().getCellEditor() != null)
                    matrixTable.getTable().getCellEditor().stopCellEditing();
                if(rowsCount != columnsCount) {
                    showWarning("Невозможно обратить не квадратную матрицу");
                } else {
                    try {
                        Matrix matrix = new Matrix(matrixTable.getTable());
                        Matrix inverseMatrix = matrix.inverse();
                        FileOperations.writeOneMatrixOperationToFile(matrix.toString(), inverseMatrix.toString(), "^-1");
                        showMatrixResult(inverseMatrix, "Обращенная матрица");
                    } catch (Error ex) {
                        showWarning(ex.getMessage());
                    }
                }
            }
        });
        bottomPanel.add(btn);
    }
    /**
     * Функция для добавления кнопки транспонирования матрицы
     */
    private void addTransposeButton() {
        JButton btn = new JButton("Транспонирование");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(matrixTable.getTable().getCellEditor() != null)
                    matrixTable.getTable().getCellEditor().stopCellEditing();
                try {
                    Matrix matrix = new Matrix(matrixTable.getTable());
                    Matrix inverseMatrix = matrix.transpose();
                    FileOperations.writeOneMatrixOperationToFile(matrix.toString(), inverseMatrix.toString(), "T");
                    showMatrixResult(inverseMatrix, "Транспонированна матрица");
                }
                catch (Error ex) {
                    showWarning(ex.getMessage());
                }
            }
        });
        bottomPanel.add(btn);
    }
    /**
     * Функция для добавления кнопки вычисления определителя матрицы
     */
    private void showMatrixResult(Matrix matrix, String title) {
        final JDialog frame = new JDialog((JFrame) SwingUtilities.getWindowAncestor(Main.mainPanel), title, true);
        frame.getContentPane().add(new ResultMatrixPanel(matrix));
        frame.setMinimumSize(new Dimension(200,0));
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Функция для вывода результата операции
     */
    private void addMatrixSizeFilds(int rows, int columns) {
        topPanel = new JPanel(new GridLayout());
        rowsCountTextField = new JTextField();
        rowsCountTextField.setText(String.valueOf(rows));
        JLabel rowLabel = new JLabel("Строк");
        columnsCountTextField = new JTextField(columns);
        columnsCountTextField.setText(String.valueOf(columns));
        JLabel columnLabel = new JLabel("Столбцов");
        topPanel.add(rowLabel);
        topPanel.add(rowsCountTextField);
        topPanel.add(columnLabel);
        topPanel.add(columnsCountTextField);
        this.add(topPanel, BorderLayout.NORTH);
        JButton btn = new JButton("Применить");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    Integer.parseInt(columnsCountTextField.getText());
                }
                catch (Exception ex) {
                    showWarning("Количество столбцов не является числом");
                }
                try {
                    Integer.parseInt(rowsCountTextField.getText());
                }
                catch (Exception ex) {
                    showWarning("Количество строк не является числом");
                }
                addMatrix(Integer.parseInt(rowsCountTextField.getText()),Integer.parseInt(columnsCountTextField.getText()), null);
            }
        });
        topPanel.add(btn);
    }
    /**
     * Функция для вывода верхней панели для ввода размера матрицы
     */
    private void addMatrix(int rows, int columns, MatrixTable newMatrixTable){
        if(rows <= 0) {
            showWarning("Количество строк матрицы не меожет быть меньше или равно 0");
        }
        else if(columns <= 0) {
            showWarning("Количество столбцов матрицы не меожет быть меньше или равно 0");
        }
        else {
            if(scrollPane != null)
                this.remove(scrollPane);
            this.columnsCount = columns;
            this.rowsCount = rows;
            if(newMatrixTable == null)
                this.matrixTable = new MatrixTable(rows, columns);
            else
                this.matrixTable = newMatrixTable;
            this.scrollPane = new ScrollPane();
            this.add(this.scrollPane);
            this.scrollPane.add(matrixTable.getTable(), BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }
    /**
     * Функция для добавления кнопки очистки матрицы
     */
    private void addClearButton() {
        JButton btn = new JButton("Очистить");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addMatrix(rowsCount, columnsCount, null);
            }
        });
        bottomPanel.add(btn);
    }
    /**
     * Функция для добавления кнопки копирования матрицы
     */
    private void addCopyButton() {
        JButton btn = new JButton("Копировать");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(matrixTable.getTable().getCellEditor() != null)
                    matrixTable.getTable().getCellEditor().stopCellEditing();
                try {
                    Main.SavedMatrix = new Matrix(matrixTable.getTable());
                } catch (Error ex) {
                    showWarning(ex.getMessage());
                }
            }
        });
        bottomPanel.add(btn);
    }
    /**
     * Функция для добавления кнопки вставки сохраненной матрицы
     */
    private void addPasteButton() {
        JButton btn = new JButton("Вставить");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int rows = Main.SavedMatrix.getMatrix().length;
                int columns = Main.SavedMatrix.getMatrix()[0].length;
                addMatrix(rows,columns,new MatrixTable(Main.SavedMatrix, true));
                rowsCountTextField.setText(String.valueOf(rows));
                columnsCountTextField.setText(String.valueOf(columns));
            }
        });
        bottomPanel.add(btn);
    }
    /**
     * Функция для вывода сообщения пользователю
     */
    private void showWarning(String message) {
        JOptionPane.showMessageDialog(null,message);
    }
}
