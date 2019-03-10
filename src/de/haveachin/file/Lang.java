package de.haveachin.file;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lang
{
    private static final Map<String, String> names = new HashMap<>();

    public static String getDisplayName(String registryName)
    {
        return names.get(registryName);
    }

    public static void loadNewFile(File langFile)
    {
        if (!isLangFile(langFile))
        {
            JOptionPane.showMessageDialog(null, "'" + langFile.getName() + "' is not a .lang file", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        loadNames(langFile);
    }

    public static void putName(String registryName, String displayName)
    {
        names.put(registryName, displayName);
    }

    private static void loadNames(File langFile)
    {
        String rawFile = "";

        try
        {
            rawFile = FileUtil.readFile(langFile);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error reading file", JOptionPane.INFORMATION_MESSAGE);
        }

        names.clear();

        String[] lines = rawFile.split(System.getProperty("line.separator"));

        for (int i = 0; i < lines.length; i++)
        {
            if (lines[i].startsWith("//") || !lines[i].matches("(.+\\..+=.+)"))
                continue;

            String registryName = lines[i].split("(\\.|=)")[1];
            String displayName = lines[i].split("=")[1];

            names.put(registryName, displayName);
        }
    }

    private static boolean isLangFile(File file)
    {
        return FileUtil.isFileExtension(file, "lang");
    }
}
