/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package delicacy.bean.utils;

/**
 *
 * @author wull
 */
public class DeptRole {
    private String employeeId;
    private String deptId;
    private String roleId;
    private Boolean isDefault;

    public DeptRole() {
    }

    public DeptRole(String employeeId, String deptId, String roleId, Boolean isDefault) {
        this.employeeId = employeeId;
        this.deptId = deptId;
        this.roleId = roleId;
        this.isDefault = isDefault;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }


}
