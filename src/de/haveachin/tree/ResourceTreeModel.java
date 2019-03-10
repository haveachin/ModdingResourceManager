package de.haveachin.tree;

import de.haveachin.model.ModInfo;
import de.haveachin.file.Lang;
import de.haveachin.tree.node.BlockNode;
import de.haveachin.tree.node.ChildListener;
import de.haveachin.tree.node.ItemNode;
import de.haveachin.tree.node.TreeNode;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ResourceTreeModel implements TreeModel, ChildListener
{
    private ModInfo modInfo;
    private List<TreeNode> nodes;
    private TreeNode rootNode;

    public ResourceTreeModel(File modInfoFile) throws Exception
    {
        Lang.loadNewFile(new File("D:\\Projects\\Java\\github\\content-plus\\src\\main\\resources\\assets\\contentplus\\lang\\en_us.lang"));

        modInfo = new ModInfo(modInfoFile);
        nodes = new ArrayList<>();

        rootNode = new TreeNode(modInfo.name, modInfoFile);
        rootNode.addListener(this);

        nodes.add(rootNode);

        File rootFolder = new File(modInfoFile.getParent() + "/assets/" + modInfo.modid);

        BlockNode blockNode = new BlockNode(rootFolder);
        rootNode.addChild(blockNode);
        blockNode.autoPopulate();

        ItemNode itemNode = new ItemNode(rootFolder);
        rootNode.addChild(itemNode);
        itemNode.autoPopulate();

        TreeNode resources = new TreeNode("Resources", rootFolder);
        rootNode.addChild(resources);
        resources.autoPopulate();
    }

    @Override
    public String getRoot()
    {
        return rootNode.toString();
    }

    @Override
    public TreeNode getChild(Object parent, int index)
    {
        return nodes.stream().filter(node -> node.toString().equals(parent.toString())).findFirst().orElse(null).getChild(index);
    }

    @Override
    public int getChildCount(Object parent)
    {
        TreeNode parentNode = nodes.stream().filter(node -> node.toString().equals(parent.toString())).findFirst().orElse(null);

        if (parentNode == null)
            return 0;

        return parentNode.getChildCount();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child)
    {
        return nodes.stream().filter(node -> node.toString().equals(parent.toString())).findFirst().orElse(null)
                .getIndexOfChild(nodes.stream().filter(node -> node.toString().equals(child.toString())).findFirst().orElse(null));
    }

    @Override
    public void ChildAdded(TreeNode node)
    {
        nodes.add(node);
    }

    @Override
    public boolean isLeaf(Object item)
    {
        TreeNode node = nodes.stream().filter(n -> n.toString().equals(item.toString())).findFirst().orElse(null);

        if (node == null)
            return true;

        return node.getChildCount() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) { }

    @Override
    public void addTreeModelListener(TreeModelListener l) { }

    @Override
    public void removeTreeModelListener(TreeModelListener l) { }
}
