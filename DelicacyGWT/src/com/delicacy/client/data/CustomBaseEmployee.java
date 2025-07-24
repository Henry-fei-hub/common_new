package com.delicacy.client.data;

import java.io.Serializable;


public class CustomBaseEmployee implements Serializable {
	private int employeeId;
	private String employeeNo;
	private String employeeName;
	private int departmentId;
	private  String level;

	public int compareTo(CustomBaseEmployee o) {
		return level == null ? -1 : level.compareTo(o.getLevel());
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}
