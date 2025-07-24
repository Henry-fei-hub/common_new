package com.delicacy.client.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class GroupColumn {
    
    
    private String groupName;
    private String groupCaption;
    private List<String> columnNames = new ArrayList<>();

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the groupCaption
     */
    public String getGroupCaption() {
        return groupCaption;
    }

    /**
     * @param groupCaption the groupCaption to set
     */
    public void setGroupCaption(String groupCaption) {
        this.groupCaption = groupCaption;
    }

    /**
     * @return the columnNames
     */
    public List<String> getColumnNames() {
        return columnNames;
    }

    /**
     * @param columnNames the columnNames to set
     */
    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }
    
}
