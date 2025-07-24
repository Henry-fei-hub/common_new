package com.delicacy.client;

import com.delicacy.client.data.DelicacyMenuData;
import com.delicacy.client.data.ExplorerTreeNode;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SortArrow;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class SideNavTree extends TreeGrid {

    private final String idSuffix = "";
    private ExplorerTreeNode[] MenuData;

    public SideNavTree() {
        setWidth100();
        setHeight100();

        setSelectionType(SelectionStyle.SINGLE);
        setCustomIconProperty("icon");
        setAnimateFolderTime(100);
        setAnimateFolders(true);
        setAnimateFolderSpeed(1000);
        setNodeIcon("silk/application_view_list.png");
        setShowSortArrow(SortArrow.CORNER);
        setShowAllRecords(true);
        setLoadDataOnDemand(false);
        setCanSort(false);
        setShowHeader(false);

        TreeGridField field = new TreeGridField();
        field.setCanFilter(true);
        field.setName("nodeTitle");
        field.setTitle("<b>Samples</b>");
        setFields(field);

    }

    public void setMenuData() {
		MenuData = DelicacyMenuData.getData();
        setMenuData(MenuData);
    }
	
	public void setMenuData(DelicacyMenuData menu){
		MenuData = menu.getData();
		setMenuData(MenuData);
	}
	
	public void setMenuData(ExplorerTreeNode[] data){
		MenuData = data;
		
		Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setNameProperty("nodeTitle");
        tree.setOpenProperty("isOpen");
        tree.setIdField("nodeID");
        tree.setParentIdField("parentNodeID");
        tree.setRootValue("root" + idSuffix);
		tree.setData(MenuData);

        setData(tree);
	}

    public ExplorerTreeNode[] getMenuData() {
        return MenuData;
    }
}
