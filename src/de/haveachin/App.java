package de.haveachin;

import de.haveachin.tree.ResourceTreeModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class App
{
    private static JFrame frame;
    private JPanel panelMain;
    private JTree treeResources;

    public App()
    {
        TreeModel treeModel = null;

        while (treeModel == null)
        {
            try
            {
                //treeModel = loadProject();
                treeModel = new ResourceTreeModel(new File("D:/Projects/Java/github/content-plus/src/main/resources/mcmod.info"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Error reading file", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        treeResources.setModel(treeModel);
    }

    private TreeModel loadProject() throws Exception
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION)
            return null;

        return new ResourceTreeModel(fileChooser.getSelectedFile());
    }

    public static App init()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("FileUtil"));

        frame = new JFrame("Modding Resource Manager");
        App app = new App();

        frame.setContentPane(app.panelMain);
        frame.setJMenuBar(menuBar);
        frame.setSize(new Dimension(1024, 768));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        return app;
    }
}
