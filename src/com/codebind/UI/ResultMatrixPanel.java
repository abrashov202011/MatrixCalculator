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
        this.add(new MatrixTable(matrix).getTable());
    }
}
