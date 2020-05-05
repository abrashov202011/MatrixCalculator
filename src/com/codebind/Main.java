package com.codebind;

import com.codebind.Classes.Matrix;
import com.codebind.UI.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static MainPanel mainPanel;
    public static Matrix SavedMatrix;
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
