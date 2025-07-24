package com.delicacy.client.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.DelicacyMenuData;
import com.delicacy.client.data.ExplorerTreeNode;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.tree.Tree;

public class SideMunuTree extends Menu {
	private static Logger __logger = Logger.getLogger("");
    private final String idSuffix = "";
    private ExplorerTreeNode[] MenuData;
    private Tree tree;

    public SideMunuTree() {
    }

    public void setMenuData() {
		MenuData = DelicacyMenuData.getData();
        setMenuData(MenuData);
    }
	
	public void setMenuData(ExplorerTreeNode[] data){
		Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setNameProperty("nodeTitle");
        tree.setOpenProperty("isOpen");
        tree.setIdField("nodeID");
        tree.setParentIdField("parentNodeID");
        tree.setRootValue("root" + idSuffix);
		tree.setData(MenuData);
        setData(tree);
        setTree(tree);
	}
	
	/**
	 * 根据parentNode获取该节点下的子级数据
	 * @param parentNode
	 * @param nodeList
	 * @return
	 */
	public List<ExplorerTreeNode> getTreeData(String parentNode,List<ExplorerTreeNode> nodeList){
		if(!BaseHelpUtils.isNullOrEmpty(MenuData) && MenuData.length > 0){
			for(ExplorerTreeNode e : MenuData){
				String parentNodeID = e.getAttribute("parentNodeID");
				if(parentNodeID.equals(parentNode)){
					nodeList.add(e);
					String nodeID = e.getNodeID();
					getTreeData(nodeID, nodeList);
				}
			}
		}
		return nodeList;
	}
	
	/**
	 * 获取菜单数据集
	 * @param parentNode
	 * @return
	 */
	public Menu getMenu(String parentNode){
		List<ExplorerTreeNode> list = new ArrayList<>();
		List<ExplorerTreeNode> nodeList = getTreeData(parentNode, list);
		ExplorerTreeNode[] data = nodeList.toArray(new ExplorerTreeNode[nodeList.size()]);
		Menu menu = new Menu();
		menu.setShowIcons(true);
		menu.setCanSelectParentItems(true);  
		menu.setWidth(160);
		Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setNameProperty("nodeTitle");
        tree.setOpenProperty("isOpen");
        tree.setIdField("nodeID");
        tree.setParentIdField("parentNodeID");
        tree.setRootValue("root" + idSuffix);
		tree.setData(data);
		menu.setData(tree); 
		setTree(tree);
		return menu;
	}

    public ExplorerTreeNode[] getMenuData() {
        return MenuData;
    }

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}
    
    
}
