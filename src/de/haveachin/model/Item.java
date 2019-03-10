package de.haveachin.model;

import de.haveachin.file.Lang;
import de.haveachin.tree.node.TreeNode;

import java.io.File;

public class Item extends TreeNode
{
    public final File model, texture;

    public Item(File model, File texture)
    {
        super();
        this.model = model;
        this.texture = texture;
        this.name = getName();
    }

    @Override
    public void autoPopulate()
    {
        addChild(new TreeNode("Model", model));
        addChild(new TreeNode("Texture", texture));
    }

    private String getName()
    {
        return Lang.getDisplayName(model.getName().split("\\.")[0]);
    }
}
