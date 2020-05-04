package com.codebind.UI;

import com.codebind.Classes.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    JPanel matrixPanel;
    /**
     * Конструктор - создание нового объекта
     */
    public MainPanel() {
        this.setLayout(new BorderLayout());
        addMatrixPanel(new SingleMatrixPanel(3,3));
        addTopPanel();
    }
    private void addTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout());
        this.add(topPanel, BorderLayout.NORTH);
        JButton oneMatrixButton = new JButton("1 матрица");
        oneMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addMatrixPanel(new SingleMatrixPanel(3,3));
            }
        });
        JButton twoMatrixButton = new JButton("2 матрицы");
        twoMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addMatrixPanel(new DualMatrixPanel(3,3, 3, 3));
            }
        });
        topPanel.add(oneMatrixButton);
        topPanel.add(twoMatrixButton);
    }
    private void addMatrixPanel(JPanel panel) {
        if(matrixPanel != null)
            this.remove(matrixPanel);
        matrixPanel = panel;
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
