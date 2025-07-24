package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class DSSubjectLinkProcessType extends DBDataSource
{


	public static DSSubjectLinkProcessType instance = null;

	public static DSSubjectLinkProcessType getInstance() {
		if(instance == null) {
			instance = new DSSubjectLinkProcessType("DSSubjectLinkProcessType");
		}
		return instance;
	}

	private final DataSourceIntegerField processTypeIdField;
	private final DataSourceTextField processTypeNameField;
	private final DataSourceIntegerField parentProcessTypeIdField;
	private final DataSourceBooleanField hasSelectedField;

	public DSSubjectLinkProcessType(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_CUSTOM);
		setActionName("SubjectLinkSystemProcessType");


		processTypeIdField = new DataSourceIntegerField("processTypeId", "主键ID");
		processTypeIdField.setLength(11);
		processTypeIdField.setPrimaryKey(true);
		processTypeIdField.setRequired(true);
		processTypeIdField.setHidden(false);
		
		hasSelectedField = new DataSourceBooleanField("hasSelected", " ");
		hasSelectedField.setRequired(false);
		hasSelectedField.setHidden(false);

		processTypeNameField = new DataSourceTextField("processTypeName", "类别名称");
		processTypeNameField.setLength(64);
		processTypeNameField.setRequired(true);
		processTypeNameField.setHidden(false);

		parentProcessTypeIdField = new DataSourceIntegerField("parentProcessTypeId", "父级ID");
		parentProcessTypeIdField.setLength(11);
		parentProcessTypeIdField.setRequired(false);
		parentProcessTypeIdField.setHidden(true);



		setFields(processTypeIdField,hasSelectedField,processTypeNameField,parentProcessTypeIdField);
	}


}

