package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class DSSubjectType extends DBDataSource
{


	public static DSSubjectType instance = null;

	public static DSSubjectType getInstance() {
		if(instance == null) {
			instance = new DSSubjectType("DSSubjectType");
		}
		return instance;
	}

	private final DataSourceIntegerField subjectTypeIdField;
	private final DataSourceIntegerField parentIdField;
	private final DataSourceTextField subjectNameField;
	private final DataSourceBooleanField isEnabledField;
	private final DataSourceBooleanField hasSelectedField;

	public DSSubjectType(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("SubjectType");


		subjectTypeIdField = new DataSourceIntegerField("subjectTypeId", "主键编码");
		subjectTypeIdField.setLength(11);
		subjectTypeIdField.setPrimaryKey(true);
		subjectTypeIdField.setRequired(true);
		subjectTypeIdField.setHidden(true);

		hasSelectedField = new DataSourceBooleanField("hasSelected", " ");
		hasSelectedField.setRequired(false);
		hasSelectedField.setHidden(false);

		parentIdField = new DataSourceIntegerField("parentId", "父级编码");
		parentIdField.setLength(11);
		parentIdField.setRequired(false);
		parentIdField.setForeignKey(dataSourceID+".subjectTypeId");
		parentIdField.setRootValue("0");
		parentIdField.setHidden(true);


		subjectNameField = new DataSourceTextField("subjectName", "科目名称");
		subjectNameField.setLength(64);
		subjectNameField.setRequired(false);
		subjectNameField.setHidden(false);


		isEnabledField = new DataSourceBooleanField("isEnabled", "是否启用");
		isEnabledField.setRequired(false);
		isEnabledField.setHidden(false);


		setFields(subjectTypeIdField, hasSelectedField, parentIdField, subjectNameField, isEnabledField);
	}


}

