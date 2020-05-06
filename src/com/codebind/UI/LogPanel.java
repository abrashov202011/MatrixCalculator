package com.codebind.UI;

import com.codebind.Classes.FileOperations;

import javax.swing.*;
import java.awt.*;
/**
 * Класс вывода лога вычислений
 * @autor Абрашов
 * @version 1.0
 */
public class LogPanel extends JPanel {
    /** Поле для вывода лога вычислений*/
    JTextArea textArea;
    /**
     * Конструктор - создание нового объекта
     */
    public LogPanel() {
        textArea = new JTextArea(35, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        String text = FileOperations.readResultFromFile();
        textArea.setText(text);
        this.setPreferredSize(new Dimension(565, 570));
        this.add(scrollPane, BorderLayout.CENTER);

    }
}
