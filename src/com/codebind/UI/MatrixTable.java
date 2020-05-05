package com.codebind.UI;

import com.codebind.Classes.Matrix;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;
/**
 * Класс для преобразования матрицы в виде 2 мерного массива в JTable
 * @autor Абрашов
 * @version 1.0
 */
public class MatrixTable {
    /** Поле для хранения матрицы в виде JTable*/
    JTable table;
    /**
     * Конструктор - создание нового объекта определенной размерности
     * @param rows количество строк матрицы
     * @param columns количество столбцов матрицы
     */
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
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param matrix матрица для преобразования в @link MatrixTable#table}
     * @param setRowWidth true если нужно задавать фиксированную ширину столбцом для компактного вывода таблицы
     */
    public MatrixTable(Matrix matrix, boolean setRowWidth) {
        int rows = matrix.getMatrix().length;
        int columns = matrix.getMatrix()[0].length;

        String[] columnNames = new String[columns];
        for(int i = 0; i < columns; i++){
            columnNames[i] = String.valueOf(i +1);
        }
        Object[][] data = new Object[rows][columns];
        DecimalFormat df = new DecimalFormat("#.##");
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[0].length; j++) {
                data[i][j] = df.format(matrix.getMatrix()[i][j]);
            }
        }
        table = new JTable(data, columnNames);
        var columnModel = table.getColumnModel();
        for(int i = 0; i < columns; i++) {
            if(setRowWidth)
                columnModel.getColumn(i).setMaxWidth(40);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            columnModel.getColumn(i).setCellRenderer( centerRenderer );
        }
        table.setRowHeight(40);

    }
    /**
     * Функция возвращает матрицу в виде JTable {@link MatrixTable#table}
     * @return возвращает матрицу в виде JTable {@link MatrixTable#table}
     */
    public JTable getTable() {
        return table;
    }

}
