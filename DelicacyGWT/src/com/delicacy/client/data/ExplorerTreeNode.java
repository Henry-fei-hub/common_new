package com.delicacy.client.data;

import com.google.gwt.core.client.prefetch.RunAsyncCode;
import com.delicacy.client.PanelFactory;
import com.smartgwt.client.widgets.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class ExplorerTreeNode extends TreeNode {

    public ExplorerTreeNode(String name, String nodeID, String parentNodeID, String icon, PanelFactory factory,
            boolean enabled, String idSuffix, Class clazz) {
        if (enabled) {
            setName(name);
        } else {
            setName("<span style='color:808080'>" + name + "</span>");
        }
        setNodeID(nodeID.replace("-", "_") + idSuffix);
        setThumbnail("thumbnails/" + nodeID.replace("-", "_") + ".gif");
        setParentNodeID(parentNodeID.replace("-", "_") + idSuffix);
        setIcon(icon);

        setFactory(factory);

        setClazz(clazz);

        if (nodeID.equals("order-management") || nodeID.equals("new-category")) {
            setIsOpen(true);
        }
    }

    public void setFactory(PanelFactory factory) {
        setAttribute("factory", factory);
    }

    public PanelFactory getFactory() {
        return (PanelFactory) getAttributeAsObject("factory");
    }

    public void setNodeID(String value) {
        setAttribute("nodeID", value);
    }

    public String getNodeID() {
        return getAttribute("nodeID");
    }

    public void setParentNodeID(String value) {
        setAttribute("parentNodeID", value);
    }
    @Override
    public void setName(String name) {
        setAttribute("nodeTitle", name);
    }

    @Override
    public String getName() {
        return getAttributeAsString("nodeTitle");
    }

    @Override
    public void setIcon(String icon) {
        setAttribute("icon", icon);
    }

    @Override
    public String getIcon() {
        return getAttributeAsString("icon");
    }
    public void setThumbnail(String thumbnail) {
        setAttribute("thumbnail", thumbnail);
    }

    public String getThumbnail() {
        return getAttributeAsString("thumbnail");
    }

    public void setIsOpen(boolean isOpen) {
        setAttribute("isOpen", isOpen);
    }

    public void setIconSrc(String iconSrc) {
        setAttribute("iconSrc", iconSrc);
    }

    public String getIconSrc() {
        return getAttributeAsString("iconSrc");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExplorerTreeNode that = (ExplorerTreeNode) o;

        return getName().equals(that.getName());
    }

    private List<RunAsyncCode> asyncCodes = new ArrayList<>();
    private Class clazz;

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * @return the asyncCodes
     */
    public List<RunAsyncCode> getAsyncCodes() {
        return asyncCodes;
    }

    /**
     * @param asyncCodes the asyncCodes to set
     */
    public void setAsyncCodes(List<RunAsyncCode> asyncCodes) {
        this.asyncCodes = asyncCodes;
    }

    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

}
