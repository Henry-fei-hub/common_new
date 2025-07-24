package com.delicacy.client.ui;

import com.delicacy.client.data.ExplorerTreeNode;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Peter
 */
public interface MenuAppender {

	public void append(Map<String, ExplorerTreeNode> map);
	
	public Set<String> getPrivileges();

}
