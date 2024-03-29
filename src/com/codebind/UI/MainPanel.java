package com.codebind.UI;

import com.codebind.Classes.Matrix;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * Класс для вывода главной панели
 * @autor Абрашов
 * @version 1.0
 */
public class MainPanel extends JPanel {
    /** Поле для вывода матрицы*/
    JPanel matrixPanel;
    /**
     * Конструктор - создание нового объекта
     */
    public MainPanel() {
        this.setLayout(new BorderLayout());
        addMatrixPanel(new SingleMatrixPanel(3,3));
        addTopPanel();
    }
    /**
     * Функция для добавления верхней панели с выбором колисества матриц
     */
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
        JButton showLogButton = new JButton("Показать лог");
        showLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                final JDialog frame = new JDialog((JFrame) SwingUtilities.getWindowAncestor(Main.mainPanel), "Лог вычислений", true);
                frame.getContentPane().add(new LogPanel());
                frame.setMinimumSize(new Dimension(550,600));
                frame.pack();
                frame.setVisible(true);
            }
        });
        topPanel.add(oneMatrixButton);
        topPanel.add(twoMatrixButton);
        topPanel.add(showLogButton);
    }
    /**
     * Функция для вывода панели с матрицами
     */
    private void addMatrixPanel(JPanel panel) {
        if(matrixPanel != null)
            this.remove(matrixPanel);
        matrixPanel = panel;
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
