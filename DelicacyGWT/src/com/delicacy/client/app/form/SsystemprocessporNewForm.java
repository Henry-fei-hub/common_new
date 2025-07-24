package com.delicacy.client.app.form;

import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.*;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.app.datasource.DSSsystemprocesspor;
import com.delicacy.client.management.form.SystemProcessDetailSystemProcessDepartment;
import com.smartgwt.client.widgets.layout.HLayout;
import java.util.Map;

public class SsystemprocessporNewForm extends AbstractWizadPage {

    private final PickTreeItem processTypeIdItem;
//    private final PickTreeItem departmentIdItem;
//    private final PickTreeItem includeDepartmentIdItem;
    private final TextItem processNameItem;
//    private final PickTreeItem holdDepartmentIdItem;
//    private final SelectItem holdDutyIdItem;
    private final TextAreaItem descriptionItem;
    private SystemProcessDetailSystemProcessDepartment detailDepartment;

    public SsystemprocessporNewForm() {
        HLayout main = new HLayout();
        main.setHeight100();
        main.setWidth100();
        DSSsystemprocesspor ds = DSSsystemprocesspor.getInstance();
        __form.setWidth("50%");
        __form.setHeight100();
        processTypeIdItem = new PickTreeItem("processTypeId", "流程类型");
        processTypeIdItem.setWidth("*");
        processTypeIdItem.setCanSelectParentItems(true);
        processTypeIdItem.setValueField("treeId");
        processTypeIdItem.setValueTree(KeyValueManager.getTree("system_process_types", "0"));
        __formItems.add(processTypeIdItem);
//        departmentIdItem = new PickTreeItem("departmentId", "归属部门");
//        departmentIdItem.setCanSelectParentItems(true);
//        departmentIdItem.setValueField("treeId");
//        departmentIdItem.setValueTree(KeyValueManager.getTree("departments", "0"));
//        __formItems.add(departmentIdItem);
//        includeDepartmentIdItem = new PickTreeItem("includeDepartmentId", "归属部门(包括下级部门)");
//        includeDepartmentIdItem.setCanSelectParentItems(true);
//        includeDepartmentIdItem.setValueField("treeId");
//        includeDepartmentIdItem.setValueTree(KeyValueManager.getTree("project_departments", "0"));
//        __formItems.add(includeDepartmentIdItem);
//        createEmployeeIdItem = new ComboBoxItem("createEmployeeId", "创建人");
//        createEmployeeIdItem.setValueMap(KeyValueManager.getValueMap("employees"));
//        createEmployeeIdItem.setChangeOnKeypress(false);
//        __formItems.add(createEmployeeIdItem);
        processNameItem = new TextItem("processName", "流程名称");
        processNameItem.setWidth("*");
        __formItems.add(processNameItem);
//        holdDepartmentIdItem = new PickTreeItem("holdDepartmentId", "归档部门");
//        holdDepartmentIdItem.setCanSelectParentItems(true);
//        holdDepartmentIdItem.setValueField("treeId");
//        holdDepartmentIdItem.setValueTree(KeyValueManager.getTree("project_departments", "0"));
//        __formItems.add(holdDepartmentIdItem);
//        holdDutyIdItem = new SelectItem("holdDutyId", "归档职务");
//        holdDutyIdItem.setValueMap(KeyValueManager.getValueMap("roles"));
//        __formItems.add(holdDutyIdItem);
       
        descriptionItem = new TextAreaItem("description", "描述");
        descriptionItem.setColSpan(2);
        descriptionItem.setWidth("*");
        descriptionItem.setRowSpan(3);
        __formItems.add(descriptionItem);

        __form.setItems(getFormItemArray());
        __form.setDataSource(ds);
        __form.setNumCols(2);
        ClientUtil.DynamicFormProcessAccordingToDevice(__form);
        main.addMember(__form);
        detailDepartment = new SystemProcessDetailSystemProcessDepartment();
        detailDepartment.setWidth("50%");
        detailDepartment.setHeight100();
        main.addMember(detailDepartment);
        addMember(main);
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public void startEdit() {
        if (getRecord() != null) {
            __form.editRecord(getRecord());
            detailDepartment.setRecord(getRecord());
        } else {
            __form.editNewRecord();
        }
        detailDepartment.startEdit();
    }

    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSSsystemprocesspor.getInstance());
        manager.addMember(__form);
    }

    @Override
    public java.util.Map getValuesAsMap() {
        Map vals = __form.getValues();
        vals.putAll(detailDepartment.getValuesAsMap());
        return vals;
    }
    
    @Override
    public String getName() {
        return "选择流程类型";
    }

    /**
     * @return the detailDepartment
     */
    public SystemProcessDetailSystemProcessDepartment getDetailDepartment() {
        return detailDepartment;
    }

    /**
     * @param detailDepartment the detailDepartment to set
     */
    public void setDetailDepartment(SystemProcessDetailSystemProcessDepartment detailDepartment) {
        this.detailDepartment = detailDepartment;
    }


}
