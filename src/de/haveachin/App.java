package de.haveachin;

import javax.swing.*;
import java.awt.*;

public class App
{
    private JPanel panelMain;
    private JTree treeResources;
    private JToolBar toolBar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Modding Resource Manager");
        frame.setContentPane(new App().panelMain);
        frame.setSize(new Dimension(1024, 768));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
