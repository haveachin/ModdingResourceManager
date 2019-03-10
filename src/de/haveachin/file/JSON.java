package de.haveachin.file;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class JSON
{
    public static JSONObject objectFromFile(File file)
    {
        if (!JSON.isJSONFile(file))
            return null;

        JSONObject obj = null;

        try
        {
            obj = new JSONObject(FileUtil.readFile(file));
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }

        return obj;
    }

    public static JSONArray arrayFromFile(File file)
    {
        if (!JSON.isJSONFile(file))
            return null;

        JSONArray array = null;

        try
        {
            array = new JSONArray(FileUtil.readFile(file));
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }

        return array;
    }

    public static boolean isJSONFile(File file)
    {
        return FileUtil.isFileExtension(file, "json");
    }
}
