package com.delicacy.client.process;

import com.delicacy.client.data.BEmployee;
import com.delicacy.client.data.BEmployeeRole;
import com.delicacy.client.data.BaseFactory;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author guangxun
 */
public class EmployeeWithRF extends BEmployee  implements BaseFactory<EmployeeWithRF> {
	
	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		if (roles != null) {
			sb.append(__wrapList(1, "roles", roles));
		}
		if(functionCodes != null){
			sb.append(__wrapSet(1, "functions", functionCodes));
		}
		return sb.toString();
	}
	
	@Override
	public void setDataFromJSON(JSONObject values) {
		JSONValue val;
		super.setDataFromJSON(values);
		if ((val = values.get("roles")) != null) {
			setRoles(__getList(val.isArray(), BEmployeeRole.newInstance()));
		}
		if ((val = values.get("functions")) != null) {
			setFunctionCodes(__getSet(val.isArray()));
		}
	}
	
	public static EmployeeWithRF newInstance(){
		return new EmployeeWithRF();
	}
	
	@Override
	public EmployeeWithRF make(){
		return new EmployeeWithRF();
	}
	
	private Set<String> functionCodes = new HashSet<>();
	private List<BEmployeeRole> roles;

	/**
	 * @return the roles
	 */
	public List<BEmployeeRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<BEmployeeRole> roles) {
		this.roles = roles;
	}

	/**
	 * @return the functionCodes
	 */
	public Set<String> getFunctionCodes() {
		return functionCodes;
	}

	/**
	 * @param functionCodes the functionCodes to set
	 */
	public void setFunctionCodes(Set<String> functionCodes) {
		this.functionCodes = functionCodes;
	}
	
}
