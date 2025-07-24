package com.delicacy.client.app.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
public class NewMemployeeForm extends GenericWizadWindow {

     private static final Logger __logger = Logger.getLogger("NewMemployeeForm");
    @Override
    public String getActionName() {
        // 需要根据实际业务修改
        return "ST_CustomEmployeeWithRole";
    }

    @Override
    public boolean checkData(Map data) {
        //提交的时候检索该职员的必填项登录名(编号)，角色，业务部门、部门是否填写
        boolean isCanSubmit = true;
        if(data.isEmpty()){
            isCanSubmit = false;
        }else{
            //获取登录名(编号)
            Object employeeNo = data.get("employeeNo");
            if(employeeNo == null || employeeNo == ""){
                SC.say("提示","登录名不可为空");
                isCanSubmit = false;
            }
            
             //获取角色列表
            SC.debugger();
            List<Map> detailEmployeeRoles = (List<Map>) data.get("detailEmployeeRole");
             ////////////////////////
            if(BaseHelpUtils.isNullOrEmpty(data.get("employeeName"))){
                 SC.say("提示", "员工姓名不可为空");
                isCanSubmit = false;
            }
            if(BaseHelpUtils.isNullOrEmpty(data.get("departmentId"))){
                 SC.say("提示", "部门不可为空");
                isCanSubmit = false;
            }
            boolean isFlag = true;
            if (detailEmployeeRoles.size() >0) {
                for (Map map : detailEmployeeRoles) {
                    if (map.containsKey("departmentId") && map.containsKey("roleId")) {
                        isFlag = false;
                        break;
                    }
                }
            }
            if (isFlag) {
                SC.say("提示", "角色不可为空，请选择角色");
                isCanSubmit = false;
            }
            
        }
        return isCanSubmit;
    }

    @Override
    public int getPageCount() {
        return 3;
    }

    @Override
    public List<AbstractWizadPage> getPages() {
        setCallback(new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                // 请在此编写成功保存后要做的工作
            }
        });
        List<AbstractWizadPage> res = new ArrayList<>();
        res.add(new MemployeeMaininfoNew());
        res.add(new MemployeeBaseinfoNew());
//        addEmployeeDeptRole employeeDeptRole = new addEmployeeDeptRole();
//        employeeDeptRole.setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
//        res.add(employeeDeptRole);
        
        res.add(new MemployeeDetailEmployeeRole());
        return res;
    }

}
