package de.haveachin.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil
{
    public static boolean isFileExtension(java.io.File file, String extension)
    {
        if (!file.isFile())
            return false;

        String fileName = file.getName();
        int extensionIndex = fileName.lastIndexOf(".");

        if (extensionIndex < 0)
            return false;

        return fileName.substring(extensionIndex + 1).equals(extension);
    }

    public static String readFile(java.io.File file) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(file.toURI()));
        return new String(encoded, Charset.defaultCharset());
    }
}
