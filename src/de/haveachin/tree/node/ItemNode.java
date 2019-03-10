package de.haveachin.tree.node;

import de.haveachin.model.Item;

import java.io.File;

public class ItemNode extends TreeNode
{
    private File modelDir, textureDir;

    public ItemNode(File directory)
    {
        super("Items", directory);

        modelDir = new File(directory + "/models/item");
        textureDir = new File(directory + "/textures/items");

        textureDir.mkdirs();
    }

    @Override
    public void autoPopulate()
    {
        if (!modelDir.exists())
        {
            modelDir.mkdirs();
            return;
        }

        for (final File fileEntry : modelDir.listFiles()) {
            if (fileEntry.isDirectory())
                continue;

            String fileName = fileEntry.getName();

            File textureFile = new File(textureDir.getPath() + fileName);

            Item item = new Item(fileEntry, textureFile);
            addChild(item);
            item.autoPopulate();
        }
    }
}
