package de.haveachin.tree.node;

import de.haveachin.model.Block;

import java.io.File;

public class BlockNode extends TreeNode
{
    private File blockStateDir, modelDir, textureDir;

    public BlockNode(File directory)
    {
        super("Blocks", directory);

        blockStateDir = new File(directory + "/blockstates");
        modelDir = new File(directory + "/models/block");
        textureDir = new File(directory + "/textures/blocks");

        modelDir.mkdirs();
        textureDir.mkdirs();
    }

    @Override
    public void autoPopulate()
    {
        if (!blockStateDir.exists())
        {
            blockStateDir.mkdirs();
            return;
        }

        for (final File fileEntry : blockStateDir.listFiles()) {
            if (fileEntry.isDirectory())
                continue;

            String fileName = fileEntry.getName();

            File modelFile = new File(modelDir.getPath() + fileName);
            File textureFile = new File(textureDir.getPath() + fileName);

            Block block = new Block(fileEntry, modelFile, textureFile);
            addChild(block);
            block.autoPopulate();
        }
    }
}
