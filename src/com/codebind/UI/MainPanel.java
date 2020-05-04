package com.codebind.UI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    JPanel matrixPanel;
    /**
     * Конструктор - создание нового объекта
     */
    public MainPanel() {
        this.setLayout(new BorderLayout());
        matrixPanel = new SingleMatrixPanel(4,4);
        this.add(matrixPanel, BorderLayout.CENTER);
    }
}
