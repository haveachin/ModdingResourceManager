package de.haveachin;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //JOptionPane.showMessageDialog(null, "This is an early access program. Please report any bugs you encounter to *insert URL here*", "Warning", JOptionPane.INFORMATION_MESSAGE);
        invokeLater(() -> App.init());
    }
}
