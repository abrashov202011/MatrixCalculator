package com.codebind.UI;

import com.codebind.Main;
import com.codebind.MatrixOperations;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class SingleMatrixPanel extends JPanel {
    /**
     * Конструктор - создание нового объекта
     */
    public SingleMatrixPanel(int rows, int columns) {
        this.setLayout(new BorderLayout());
        MatrixTable matrixTable = new MatrixTable(rows, columns);
        this.add(matrixTable.getMatrix(), BorderLayout.CENTER);
    }
}
