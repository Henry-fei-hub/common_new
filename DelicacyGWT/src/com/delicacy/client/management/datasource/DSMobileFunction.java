package com.delicacy.client.management.datasource;

import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.FieldType;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.data.DBDataSource;

public class DSMobileFunction extends DBDataSource
{


	public static DSMobileFunction instance = null;

	public static DSMobileFunction getInstance() {
		if(instance == null) {
			instance = new DSMobileFunction("DSMobileFunction");
		}
		return instance;
	}

	private final DataSourceIntegerField functionIdField;
	private final DataSourceTextField functionCodeField;
	private final DataSourceIntegerField parentIdField;
	private final DataSourceTextField functionNameField;
	private final DataSourceIntegerField applicationIdField;
	private final DataSourceIntegerField functionTypeField;
	private final DataSourceIntegerField privilegeTypeField;
	private final DataSourceBooleanField enabledField;
	private final DataSourceTextField functionStaticNameField;
	private final DataSourceTextField iconNameField;
	private final DataSourceTextField styleNameField;
	private final DataSourceTextField classNameField;
	private final DataSourceTextField executePageNameField;
	private final DataSourceTextField appPageNameField;
	private final DataSourceFloatField marginTopField;
	private final DataSourceFloatField marginBottomField;
	private final DataSourceTextField iconColorField;

	public DSMobileFunction(String dataSourceID) {

		super();
		setID(dataSourceID);
		setActionMode(ACTION_MODE_TABLE);
		setActionName("MobileFunction");


		functionIdField = new DataSourceIntegerField("functionId", "功能编码");
		functionIdField.setLength(11);
		functionIdField.setPrimaryKey(true);
		functionIdField.setRequired(true);
		functionIdField.setHidden(true);


		functionCodeField = new DataSourceTextField("functionCode", "功能编号");
		functionCodeField.setLength(64);
		functionCodeField.setRequired(false);
		functionCodeField.setHidden(false);


		parentIdField = new DataSourceIntegerField("parentId", "上级功能");
		parentIdField.setLength(11);
		parentIdField.setRequired(false);
		parentIdField.setForeignKey(dataSourceID+".functionId");
		parentIdField.setRootValue("0");
		parentIdField.setHidden(false);


		functionNameField = new DataSourceTextField("functionName", "功能名称");
		functionNameField.setLength(64);
		functionNameField.setRequired(false);
		functionNameField.setHidden(false);


		applicationIdField = new DataSourceIntegerField("applicationId", "应用系统代码");
		applicationIdField.setLength(11);
		applicationIdField.setRequired(false);
		applicationIdField.setHidden(false);
		applicationIdField.setValueMap(KeyValueManager.getValueMap("domain_values_application_type"));


		functionTypeField = new DataSourceIntegerField("functionType", "功能类型");
		functionTypeField.setLength(11);
		functionTypeField.setRequired(false);
		functionTypeField.setValueMap(KeyValueManager.getValueMap("domain_values_function_type"));


		privilegeTypeField = new DataSourceIntegerField("privilegeType", "数据权限类型");
		privilegeTypeField.setLength(11);
		privilegeTypeField.setRequired(false);
		privilegeTypeField.setHidden(false);


		enabledField = new DataSourceBooleanField("enabled", "是否有效");
		enabledField.setRequired(false);
		enabledField.setHidden(true);


		functionStaticNameField = new DataSourceTextField("functionStaticName", "功能静态名称");
		functionStaticNameField.setLength(255);
		functionStaticNameField.setRequired(false);
		functionStaticNameField.setHidden(false);


		iconNameField = new DataSourceTextField("iconName", "功能图标");
		iconNameField.setLength(256);
		iconNameField.setRequired(false);
		iconNameField.setHidden(false);


		styleNameField = new DataSourceTextField("styleName", "功能的样式");
		styleNameField.setLength(256);
		styleNameField.setRequired(false);
		styleNameField.setHidden(false);


		classNameField = new DataSourceTextField("className", "功能class样式");
		classNameField.setLength(256);
		classNameField.setRequired(false);
		classNameField.setHidden(false);


		executePageNameField = new DataSourceTextField("executePageName", "该功能菜单对应的页面");
		executePageNameField.setLength(256);
		executePageNameField.setRequired(false);
		executePageNameField.setHidden(false);
		
		
		appPageNameField = new DataSourceTextField("appPageName", "该功能菜单对应的APP页面");
		appPageNameField.setLength(256);
		appPageNameField.setRequired(false);
		appPageNameField.setHidden(false);
		
		
		marginTopField = new DataSourceFloatField("marginTop", "头部间距");
		marginTopField.setLength(18);
		marginTopField.setDecimalPad(2);
		marginTopField.setFormat("#,###,###,###,###,##0.00");
		marginTopField.setHidden(false);
		
		marginBottomField = new DataSourceFloatField("marginBottom", "底部间距");
		marginBottomField.setLength(18);
		marginBottomField.setDecimalPad(2);
		marginBottomField.setFormat("#,###,###,###,###,##0.00");
		marginBottomField.setHidden(false);
		
		iconColorField = new DataSourceTextField("iconColor", "图标颜色");
		iconColorField.setLength(256);
		iconColorField.setRequired(false);
		iconColorField.setHidden(false);


		setFields(functionIdField, functionCodeField, parentIdField, functionNameField, applicationIdField, functionTypeField, privilegeTypeField, enabledField, functionStaticNameField, iconNameField, styleNameField, classNameField, executePageNameField, appPageNameField, marginTopField,marginBottomField,iconColorField);
	}


}

