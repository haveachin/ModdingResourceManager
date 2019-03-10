package de.haveachin.model;

import de.haveachin.file.FileUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class ModInfo
{
    public final File file;
    public final String modid, name;

    private final JSONArray json;

    public ModInfo(File file) throws NullPointerException, IOException
    {
        this.file = file;

        json = new JSONArray(FileUtil.readFile(file));

        if (json == null)
            throw new NullPointerException("Error reading '" + file.getPath() + "'");

        JSONObject infoObj = (JSONObject) json.get(0);
        modid = (String) infoObj.get("modid");
        name = (String) infoObj.get("name");
    }
}
