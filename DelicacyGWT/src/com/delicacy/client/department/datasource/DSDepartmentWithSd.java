package com.delicacy.client.department.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSDepartmentWithSd extends DBDataSource
{


	public static DSDepartmentWithSd instance = null;

	public static DSDepartmentWithSd getInstance() {
		if(instance == null) {
			instance = new DSDepartmentWithSd("DSDepartmentWithSd");
		}
		return instance;
	}

	private final DataSourceIntegerField departmentIdField;
	private final DataSourceTextField departmentNameField;
	private final DataSourceTextField abbreviationField;
	private final DataSourceIntegerField managerIdField;
	private final DataSourceTextField managerNameField;
	private final DataSourceIntegerField parentIdField;
	private final DataSourceBooleanField enabledField;
	private final DataSourceIntegerField originalIdField;
	private final DataSourceIntegerField plateIdField;
	private final DataSourceBooleanField isHeadcountField;
	private final DataSourceIntegerField departmentTypeField;
	private final DataSourceIntegerField weixinDepartmentIdField;
	private final DataSourceIntegerField emailDepartmentIdField;
	private final DataSourceIntegerField ecmcDepartmentIdField;
	private final DataSourceIntegerField deleteFlagField;
	private final DataSourceBooleanField isEnableField;
	private final DataSourceField detailSystemProcessDepartment;
	private final DataSourceField detailDepartmentRoleWithR;

	public DSDepartmentWithSd(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_QUERY);
		setActionName("DepartmentWithSd");


		departmentIdField = new DataSourceIntegerField("departmentId", "部门编码");
		departmentIdField.setLength(11);
		departmentIdField.setPrimaryKey(true);
		departmentIdField.setRequired(true);
		departmentIdField.setHidden(true);
		departmentIdField.setValueMap(KeyValueManager.getValueMap("departments"));


		departmentNameField = new DataSourceTextField("departmentName", "部门名称");
		departmentNameField.setLength(64);
		departmentNameField.setRequired(true);
		departmentNameField.setHidden(false);


		abbreviationField = new DataSourceTextField("abbreviation", "部门名称缩写");
		abbreviationField.setLength(64);
		abbreviationField.setRequired(false);
		abbreviationField.setHidden(false);


		managerIdField = new DataSourceIntegerField("managerId", "部门负责编码");
		managerIdField.setLength(11);
		managerIdField.setRequired(false);
		managerIdField.setHidden(false);
		managerIdField.setValueMap(KeyValueManager.getValueMap("employee_lists"));


		managerNameField = new DataSourceTextField("managerName", "部门负责人姓名");
		managerNameField.setLength(64);
		managerNameField.setRequired(false);
		managerNameField.setHidden(false);


		parentIdField = new DataSourceIntegerField("parentId", "上级部门");
		parentIdField.setLength(11);
		parentIdField.setRequired(false);
		parentIdField.setHidden(false);
		parentIdField.setValueMap(KeyValueManager.getValueMap("contracts_1"));


		enabledField = new DataSourceBooleanField("enabled", "是否有效");
		enabledField.setRequired(false);
		enabledField.setHidden(false);


		originalIdField = new DataSourceIntegerField("originalId", "original_id");
		originalIdField.setLength(11);
		originalIdField.setRequired(false);
		originalIdField.setHidden(false);


		plateIdField = new DataSourceIntegerField("plateId", "板块");
		plateIdField.setLength(11);
		plateIdField.setRequired(false);
		plateIdField.setHidden(false);
		plateIdField.setValueMap(KeyValueManager.getValueMap("system_dictionary_1"));


		isHeadcountField = new DataSourceBooleanField("isHeadcount", "总部");
		isHeadcountField.setRequired(false);
		isHeadcountField.setHidden(false);


		departmentTypeField = new DataSourceIntegerField("departmentType", " 2业务部门");
		departmentTypeField.setLength(11);
		departmentTypeField.setRequired(false);
		departmentTypeField.setHidden(false);
		departmentTypeField.setValueMap(KeyValueManager.getValueMap("system_dictionary_93"));


		weixinDepartmentIdField = new DataSourceIntegerField("weixinDepartmentId", "微信部门编码");
		weixinDepartmentIdField.setLength(64);
		weixinDepartmentIdField.setRequired(false);
		weixinDepartmentIdField.setHidden(false);


		emailDepartmentIdField = new DataSourceIntegerField("emailDepartmentId", "企业邮箱部门编码");
		emailDepartmentIdField.setLength(64);
		emailDepartmentIdField.setRequired(false);
		emailDepartmentIdField.setHidden(false);


		ecmcDepartmentIdField = new DataSourceIntegerField("ecmcDepartmentId", "ECMC部门编码");
		ecmcDepartmentIdField.setLength(11);
		ecmcDepartmentIdField.setRequired(false);
		ecmcDepartmentIdField.setHidden(false);


		deleteFlagField = new DataSourceIntegerField("deleteFlag", "0 未删除  1 已删除");
		deleteFlagField.setLength(11);
		deleteFlagField.setRequired(false);
		deleteFlagField.setHidden(false);


		isEnableField = new DataSourceBooleanField("isEnable", "is_enable");
		isEnableField.setRequired(false);
		isEnableField.setHidden(false);

		detailSystemProcessDepartment = new DataSourceField("detailSystemProcessDepartment", FieldType.ANY);
		detailSystemProcessDepartment.setChildrenProperty(true);
		detailSystemProcessDepartment.setChildTagName("SystemProcessDepartment");
		detailSystemProcessDepartment.setRequired(false);
		detailSystemProcessDepartment.setHidden(true);
		detailDepartmentRoleWithR = new DataSourceField("detailDepartmentRoleWithR", FieldType.ANY);
		detailDepartmentRoleWithR.setChildrenProperty(true);
		detailDepartmentRoleWithR.setChildTagName("DepartmentRoleWithR");
		detailDepartmentRoleWithR.setRequired(false);
		detailDepartmentRoleWithR.setHidden(true);

		setFields(departmentIdField, departmentNameField, abbreviationField, managerIdField, managerNameField, parentIdField, enabledField, originalIdField, plateIdField, isHeadcountField, departmentTypeField, weixinDepartmentIdField, emailDepartmentIdField, ecmcDepartmentIdField, deleteFlagField, isEnableField, detailSystemProcessDepartment, detailDepartmentRoleWithR);
	}


}

