package de.haveachin.model;

import de.haveachin.file.Lang;
import de.haveachin.tree.node.TreeNode;

import java.io.File;

public class Block extends TreeNode
{
    public final File state, model, texture;

    public Block(File state, File model, File texture)
    {
        super();
        this.state = state;
        this.model = model;
        this.texture = texture;
        this.name = getName();
    }

    @Override
    public void autoPopulate()
    {
        addChild(new TreeNode("BlockState", state));
        addChild(new TreeNode("Model", model));
        addChild(new TreeNode("Texture", texture));
    }

    private String getName()
    {
        return Lang.getDisplayName(state.getName().split("\\.")[0]);
    }
}
