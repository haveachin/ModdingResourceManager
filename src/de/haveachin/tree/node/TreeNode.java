package de.haveachin.tree.node;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreeNode
{
    protected String name;
    public final File directory;
    private final List<TreeNode> children;
    private final List<ChildListener> childListeners;

    protected TreeNode()
    {
        this("no.name");
    }

    public TreeNode(String name)
    {
        this(name, null);
    }

    public TreeNode(String name, File directory)
    {
        this.name = name;
        this.directory = directory;
        children = new ArrayList<>();
        childListeners = new ArrayList<>();
    }

    public void addListener(ChildListener listener)
    {
        childListeners.add(listener);
    }

    public void addChild(TreeNode node)
    {
        children.add(node);

        childListeners.forEach(listener ->
        {
            listener.ChildAdded(node);
            node.addListener(listener);
        });
    }

    public void autoPopulate()
    {
        if (directory == null || !directory.exists())
            return;

        for (File fileEntry : directory.listFiles())
        {
            TreeNode child = new TreeNode(fileEntry.getName(), fileEntry);
            addChild(child);

            if (fileEntry.isDirectory())
                child.autoPopulate();
        }
    }

    public void removeChild(TreeNode node)
    {
        children.remove(node);
    }

    public TreeNode getChild(int index)
    {
        return children.get(index);
    }

    public int getChildCount()
    {
        return children.size();
    }

    public int getIndexOfChild(TreeNode child)
    {
        return children.indexOf(child);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
