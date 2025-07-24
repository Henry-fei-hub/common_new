package com.delicacy.client.app.form;

import java.util.LinkedHashMap;
import java.util.Map;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.app.datasource.DSSystemProcessActivity;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.management.datasource.DSSelectGridEmployeeValue;
import com.delicacy.client.process.SystemProcessConstants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.FormItemIfFunction;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 *
 * @author guangxun
 */
public class NewProcessActivityEdit extends DynamicForm {
	// 活动时限模块
	public static final String TIME_OUT_TYPE_HOUR = "小时";
	public static final String TIME_OUT_TYPE_DAY = "天";
	public static final String TIME_OUT_TYPE_WEEK = "周";
	// 审核对象模块
	public static final String AUDIT_OBJECT_TYPE_EMP = "指定人员";
	public static final String AUDIT_OBJECT_TYPE_ROLE = "指定角色";
	public static final String AUDIT_OBJECT_TYPE_DET_ROLE = "指定部门下角色";

	public NewProcessActivityEdit() {

		HeaderItem basicHeader = new HeaderItem();
		basicHeader.setDefaultValue("基础属性设置模块:");
		basicHeader.setColSpan(4);
		basicHeader.setWidth("*");

		TextItem activityName = new TextItem("activityName", "节点名称");
		activityName.setWidth("*");

		TextItem executeName = new TextItem("executeName", "处理名称");
		executeName.setWidth("*");
		
		SelectItem activityType = new SelectItem("activityType", "节点类型");
		activityType.setWidth("*");
		LinkedHashMap<Integer, String> activityTypeMap = new LinkedHashMap<>();
		activityTypeMap.put(SystemProcessConstants.ACTIVITY_TYPE_APPROVAL, "审批节点");
		activityTypeMap.put(SystemProcessConstants.ACTIVITY_TYPE_PROCESS, "处理节点");
		activityType.setValueMap(activityTypeMap);
		activityType.hide();

		SelectItem poolType = new SelectItem("poolType", "任务池类型");
		poolType.setWidth("*");
		poolType.setValueMap(KeyValueManager.getValueMap("poolTypes"));

		CheckboxItem ifAllowOverItem = new CheckboxItem("ifAllowOver", "是否允许跳过");
		ifAllowOverItem.setWidth("*");

		HeaderItem auditObjectHeader = new HeaderItem();
		auditObjectHeader.setDefaultValue("审核对象设置模块:");
		auditObjectHeader.setColSpan(4);
		auditObjectHeader.setWidth("*");

		final RadioGroupItem auditObjectItem = new RadioGroupItem("auditObject");
		auditObjectItem.setTitle("审核对象");
		auditObjectItem.setDefaultValue(AUDIT_OBJECT_TYPE_EMP);
		auditObjectItem.setValueMap(AUDIT_OBJECT_TYPE_EMP, AUDIT_OBJECT_TYPE_ROLE, AUDIT_OBJECT_TYPE_DET_ROLE);
		auditObjectItem.setWidth("*");
		auditObjectItem.setRedrawOnChange(true);
		auditObjectItem.setColSpan(4);

		ListGridField employeeIdField = new ListGridField("employeeId");
		ListGridField employeeNoField = new ListGridField("employeeNo");
		ListGridField employeeNameField = new ListGridField("employeeName");
		ListGridField departmentIdField = new ListGridField("departmentId");
		KeyValueManager.loadValueMap("all_departments",departmentIdField);
		
		ComboBoxItem employeeId = new ComboBoxItem("employeeId", "指定人");
		employeeId.setWidth("*");
        employeeId.setOptionDataSource(DSSelectGridEmployeeValue.getInstance());
        employeeId.setChangeOnKeypress(false);
        employeeId.setTextMatchStyle(TextMatchStyle.SUBSTRING);
        employeeId.setValueField(employeeIdField.getName());
        employeeId.setDisplayField(employeeNameField.getName());
        employeeId.setPickListFields(departmentIdField, employeeNoField, employeeNameField);
        employeeId.setPickListWidth(500);
        //只查询出在职的员工
        Criteria employeeCondition = new Criteria();
        employeeCondition.addCriteria("status", 0);
        employeeId.setPickListCriteria(employeeCondition);
		employeeId.setShowIfCondition(new FormItemIfFunction() {
			@Override
			public boolean execute(FormItem item, Object value, DynamicForm form) {
				return (Boolean) AUDIT_OBJECT_TYPE_EMP.equals(BaseHelpUtils.getString(auditObjectItem.getValue()));
			}
		});

		PickTreeItem departmentId = new PickTreeItem("departmentId", "指定部门");
		departmentId.setWidth("*");
		departmentId.setCanSelectParentItems(true);
		departmentId.setValueTree(KeyValueManager.getTree("departments"));
		departmentId.setValueField("treeId");
		departmentId.setShowIfCondition(new FormItemIfFunction() {
			@Override
			public boolean execute(FormItem item, Object value, DynamicForm form) {
				return (Boolean) AUDIT_OBJECT_TYPE_DET_ROLE.equals(BaseHelpUtils.getString(auditObjectItem.getValue()));
			}
		});

		ComboBoxItem roleId = new ComboBoxItem("roleId", "指定角色");
		roleId.setWidth("*");
		roleId.setTextMatchStyle(TextMatchStyle.SUBSTRING);
//		roleId.setValueMap(KeyValueManager.getValueMap("roles"));
		KeyValueManager.loadValueMap("roles",roleId);
		roleId.setShowIfCondition(new FormItemIfFunction() {
			@Override
			public boolean execute(FormItem item, Object value, DynamicForm form) {
				if (AUDIT_OBJECT_TYPE_DET_ROLE.equals(BaseHelpUtils.getString(auditObjectItem.getValue()))
						|| AUDIT_OBJECT_TYPE_ROLE.equals(BaseHelpUtils.getString(auditObjectItem.getValue()))) {
					return true;
				} else {
					return false;
				}
			}
		});

		HeaderItem timeOutHeader = new HeaderItem();
		timeOutHeader.setDefaultValue("活动时限设置模块:");
		timeOutHeader.setColSpan(4);
		timeOutHeader.setWidth("*");

		final RadioGroupItem timeOutItem = new RadioGroupItem("timeOut");
		timeOutItem.setTitle("时限类型");
		timeOutItem.setDefaultValue(TIME_OUT_TYPE_HOUR);
		timeOutItem.setValueMap(TIME_OUT_TYPE_HOUR, TIME_OUT_TYPE_DAY, TIME_OUT_TYPE_WEEK);
		timeOutItem.setWidth("*");
		timeOutItem.setColSpan(4);

		SpinnerItem timeOutAction = new SpinnerItem("timeOutAction", "活动时限");
		timeOutAction.setMin(0);
		timeOutAction.setDefaultValue(0);
		timeOutAction.setMax(720);
		timeOutAction.setWidth("*");

		setDataSource(DSSystemProcessActivity.getInstance());
		setNumCols(4);

		setItems(basicHeader, activityName, executeName, activityType, poolType, ifAllowOverItem, auditObjectHeader, auditObjectItem,
				employeeId, departmentId, roleId, timeOutHeader, timeOutItem, timeOutAction);
	}

	public Map getValues() {
		Map res = super.getValues();
		// 获取活动时限值
		int timeOutAction = BaseHelpUtils.getIntValue(res.get("timeOutAction"));
		// 获取时限类型
		String timeOut = BaseHelpUtils.getString(res.get("timeOut"));
		if (TIME_OUT_TYPE_WEEK.equals(timeOut)) {// 选择的是周
			timeOutAction = timeOutAction * 7 * 24;
		} else if (TIME_OUT_TYPE_DAY.equals(timeOut)) {// 选择的是天
			timeOutAction = timeOutAction * 24;
		}
		res.put("timeOutAction", timeOutAction);
		String auditObject = BaseHelpUtils.getString(res.get("auditObject"));
		if (AUDIT_OBJECT_TYPE_EMP.equals(auditObject)) {
			res.put("departmentId", 0);
			res.put("roleId", 0);
		} else if (AUDIT_OBJECT_TYPE_ROLE.equals(auditObject)) {
			res.put("departmentId", 0);
			res.put("employeeId", 0);
		} else {
			res.put("employeeId", 0);
		}
		return res;
	}

	public void editRecord(Record __data) {
		// 获取人员id、部门id、角色id，如果人员id>0,则选择对象类型的值为人员，如果部门id>0，则设置为部门下角色，如果角色id>0,则设置为角色
		int employeeId = BaseHelpUtils.getIntValue(__data.getAttribute("employeeId"));
		int departmentId = BaseHelpUtils.getIntValue(__data.getAttribute("departmentId"));
		int roleId = BaseHelpUtils.getIntValue(__data.getAttribute("roleId"));
		if (employeeId > 0) {
			__data.setAttribute("auditObject", AUDIT_OBJECT_TYPE_EMP);
		} else if (departmentId > 0) {
			__data.setAttribute("auditObject", AUDIT_OBJECT_TYPE_DET_ROLE);
		} else if (roleId > 0) {
			__data.setAttribute("auditObject", AUDIT_OBJECT_TYPE_ROLE);
		}
		// 获取活动时限,如果小于24，则表示是小时，如果大于24则判断是否是天(即除以24正好是整数)，如果大于7*24,则判断是否是周（即除以（7*24）正好是整数）
		int timeOutAction = BaseHelpUtils.getIntValue(__data.getAttribute("timeOutAction"));
		if (timeOutAction < 24) {
			__data.setAttribute("timeOut", TIME_OUT_TYPE_HOUR);
		} else if (timeOutAction >= 24 && timeOutAction < 24 * 7) {
			// 判断是否满足周，即求整
			if (timeOutAction % 24 == 0) {// 求整，说明是设置为天
				__data.setAttribute("timeOut", TIME_OUT_TYPE_DAY);
				__data.setAttribute("timeOutAction", timeOutAction / 24);
			} else {// 否则设置为小时
				__data.setAttribute("timeOut", TIME_OUT_TYPE_HOUR);
			}
		} else {
			// 判断是否满足周，即求整
			if (timeOutAction % (7 * 24) == 0) {// 求整，说明是设置为周
				__data.setAttribute("timeOut", TIME_OUT_TYPE_WEEK);
				__data.setAttribute("timeOutAction", timeOutAction / (7 * 24));
			} else if (timeOutAction % 24 == 0) {// 求整，说明是设置为天
				__data.setAttribute("timeOut", TIME_OUT_TYPE_DAY);
				__data.setAttribute("timeOutAction", timeOutAction / 24);
			} else {// 否则设置为小时
				__data.setAttribute("timeOut", TIME_OUT_TYPE_HOUR);
			}
		}
		super.editRecord(__data);
		int activityType = BaseHelpUtils.getIntValue(__data.getAttribute("activityType"));
		if(activityType == SystemProcessConstants.ACTIVITY_TYPE_APPROVAL || activityType == SystemProcessConstants.ACTIVITY_TYPE_PROCESS) {
			//只有审批节点和处理节点才可以修改节点类型
			getItem("activityType").show();
		}
	}

}
