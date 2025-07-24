package com.delicacy.client.app.form;

import java.util.HashMap;
import java.util.Map;

import com.delicacy.client.app.datasource.DSRoleWithR;
import com.delicacy.client.management.form.RoleDetaiRoleDepartment;
import com.delicacy.client.ui.AbstractWizadPage;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.layout.VLayout;

public class BindRoleToDepartmentForm extends AbstractWizadPage {

    private RoleDetaiRoleDepartment detailDepartment;

	public BindRoleToDepartmentForm() {
    	VLayout main = new VLayout(10);
        main.setHeight100();
        main.setWidth100();
        
        Label bel = new Label();
        bel.setHeight("5%");
        bel.setAlign(Alignment.CENTER);
        bel.setContents("<font style=\"font-weight:bold;font-size:16px;\">赋予该角色給选择部门</font>");
        main.addMember(bel);
        
        detailDepartment = new RoleDetaiRoleDepartment();
        detailDepartment.setWidth100();
        detailDepartment.setHeight("95%");
        main.addMember(detailDepartment);
        
        addMember(main);
        setPageMode(PAGE_MODE_UPDATE);
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public void startEdit() {
        if (getRecord() != null) {
            detailDepartment.setRecord(getRecord());
        } else {
            __form.editNewRecord();
        }
        detailDepartment.startEdit();
    
    }
    @Override
    public String getName() {
        return "赋予该角色給选择部门";
    }

    @Override
    public void setValueManage(ValuesManager manager) {
        manager.setDataSource(DSRoleWithR.getInstance());
        manager.addMember(__form);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public java.util.Map getValuesAsMap() {
	   Map vals = new HashMap<>();
       vals.putAll(detailDepartment.getValuesAsMap());
       return vals;
    }
    
    /**
     * @return the detailDepartment
     */
    public RoleDetaiRoleDepartment getDetailDepartment() {
        return detailDepartment;
    }

    /**
     * @param detailDepartment the detailDepartment to set
     */
    public void setDetailDepartment(RoleDetaiRoleDepartment detailDepartment) {
        this.detailDepartment = detailDepartment;
    }

}
