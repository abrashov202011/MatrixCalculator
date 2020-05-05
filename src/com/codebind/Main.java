package com.codebind;

import com.codebind.Classes.Matrix;
import com.codebind.UI.MainPanel;

import javax.swing.*;
import java.awt.*;
/**
 * Основной класс приложения
 * @autor Абрашов
 * @version 1.0
 */
public class Main {
    /** Поле для вывода главной палени с интерфесом приложения*/
    public static MainPanel mainPanel;
    /** Поле для хранения сохраненной матрицы*/
    public static Matrix SavedMatrix;
    /**
     * Функция создающая интерфейс приложения
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 800));
        mainPanel = new MainPanel();
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
