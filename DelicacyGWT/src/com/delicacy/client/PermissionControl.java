package com.delicacy.client;

import java.util.Objects;

import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.widgets.IButton;

/**
 *
 * @author CL
 */
public class PermissionControl {
    
    /**
     * 创建一个带有权限控制的按钮
     * @param title
     * @param permission
     * @return 
     */
    public static IButton createPermissionButton(String title, String permission) {
        IButton newButton = new IButton(title);
        if(!Objects.equals(ClientUtil.getUserNo(), "admin") && !ClientUtil.checkIsHavePermission(permission)){
            newButton.hide();
        }
        return newButton;
    }
    
    public static Boolean getPermission(String permission) {
        return ClientUtil.checkIsHavePermission(permission);
    }
}
