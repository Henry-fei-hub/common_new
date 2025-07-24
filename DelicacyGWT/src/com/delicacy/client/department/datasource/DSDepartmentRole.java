package com.delicacy.client.department.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSDepartmentRole extends DBDataSource
{


	public static DSDepartmentRole instance = null;

	public static DSDepartmentRole getInstance() {
		if(instance == null) {
			instance = new DSDepartmentRole("DSDepartmentRole");
		}
		return instance;
	}

	private final DataSourceIntegerField departmentRoleIdField;
	private final DataSourceIntegerField departmentIdField;
	private final DataSourceIntegerField roleIdField;
	private final DataSourceField detailRoleFunction;

	public DSDepartmentRole(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("DepartmentRole");


		departmentRoleIdField = new DataSourceIntegerField("departmentRoleId", "部门角色代码");
		departmentRoleIdField.setLength(11);
		departmentRoleIdField.setPrimaryKey(true);
		departmentRoleIdField.setRequired(true);
		departmentRoleIdField.setHidden(false);


		departmentIdField = new DataSourceIntegerField("departmentId", "部门");
		departmentIdField.setLength(11);
		departmentIdField.setRequired(false);
		departmentIdField.setHidden(false);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		roleIdField = new DataSourceIntegerField("roleId", "角色");
		roleIdField.setLength(11);
		roleIdField.setRequired(false);
		roleIdField.setHidden(false);

		detailRoleFunction = new DataSourceField("detailRoleFunction", FieldType.ANY);
		detailRoleFunction.setChildrenProperty(true);
		detailRoleFunction.setChildTagName("RoleFunction");
		detailRoleFunction.setRequired(false);
		detailRoleFunction.setHidden(true);

		setFields(departmentRoleIdField, departmentIdField, roleIdField, detailRoleFunction);
	}


}

