package delicacy.system.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import delicacy.employee.bean.BaseLoginEmployeeInfo;
import delicacy.system.bean.BaseEmployeeRole;

/**
 *
 * @author guangxun
 */
public class EmployeeWithRoleFunction extends BaseLoginEmployeeInfo {

	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toJSONString());
		int count = sb.length();
		if (roles != null) {
			sb.append(__wrapList(count++, "roles", roles));
		}
		if (functions != null) {
			sb.append(__wrapSet(count++, "functions", functions));
		}
		return sb.toString();
	}

	@Override
	public void setDataFromMap(Map<String, Object> values) {
		super.setDataFromMap(values);
		Map<String, Object> val = null;
		List<String> val1 = null;
		if ((val = (Map<String, Object>) values.get("roles")) != null) {
			setRoles(__getList(val, BaseEmployeeRole.newInstance()));
		}
		if ((val1 = (List<String>) values.get("functions")) != null) {
			setFunctions(__getSet(val1));
		}
	}

	private Set<String> functions = new HashSet<>();
	private List<BaseEmployeeRole> roles;

	/**
	 * @return the functions
	 */
	public Set<String> getFunctions() {
		return functions;
	}

	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(Set<String> functions) {
		this.functions = functions;
	}

	/**
	 * @return the roles
	 */
	public List<BaseEmployeeRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<BaseEmployeeRole> roles) {
		this.roles = roles;
	}

}
