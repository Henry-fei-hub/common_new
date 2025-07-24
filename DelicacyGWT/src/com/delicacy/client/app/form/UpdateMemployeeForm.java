package com.delicacy.client.app.form;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.GenericWizadWindow;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class UpdateMemployeeForm extends GenericWizadWindow {

    private static final Logger __logger = Logger.getLogger("UpdateMemployeeForm");

    @Override
    public String getActionName() {
        // 需要根据实际业务修改
        return "EP_OnEmployeeInfoEdit";
    }
    
    public void reloadData(int employeeId){
        Map<String,Object> param = new HashMap<>();
        param.put("employeeId", employeeId);
        param.put("optType", "onLoadEmployeeEditData");
        param.put("OperateEmployeeId", ClientUtil.getEmployeeId());
        DBDataSource.callOperation("EP_MyPersonnelProcessor", param, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
					if(__pages == null) __pages = getPages();
					for(AbstractWizadPage wp : __pages) { 
						wp.setRecord(dsResponse.getData()[0]); 
						wp.startEdit(); 
						}
                } else {
                    Map errors = dsResponse.getErrors();
                    SC.say("delete failure" + errors);
                }
            }
        });
    }

    @Override
    public boolean checkData(Map data) {
    	boolean status = true;
		for(AbstractWizadPage wp : __pages){
			if(!wp.checkData()){
				status = false;
			}
		}
		return status;
    }

    @Override
    public int getPageCount() {
        return 2;
    }

    @Override
    public List<AbstractWizadPage> getPages() {
        setCallback(new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                // 请在此编写成功保存后要做的工作
            }
        });

        setSubaction("update");
        List<AbstractWizadPage> res = new ArrayList<>();
//        res.add(new MemployeeMaininfoUpdate());
        res.add(new EmployeeOnboardInformationBasicinfoUpdate(operatorId));
//        res.add(new MemployeeBaseinfoUpdate());
        res.add(new EmployeeOnboardInformationContactinfoUpdate());
        return res;
    }
    
    int operatorId;
    public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

    
    
    @Override
	public Map getValues() {
		Map<String, Object> values = new HashMap<>();
		for (AbstractWizadPage p : __pages) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = p.getValuesAsMap();
			Set<String> set = p.getItemNames();
			if (!BaseHelpUtils.isNullOrEmpty(map) && map.size() > 0) {
				if (!BaseHelpUtils.isNullOrEmpty(set) && set.size() > 0) {
					for (String key : map.keySet()) {
						if (set.contains(key)) {
							values.put(key, map.get(key));
						}
					}
				}
			}
			//获取每一页的列表数据集，并重新封装
			Object educationList = map.get("detailEmployeeEducationInformation");
			Object familyList = map.get("detailEmployeeFamilyInformation");
			Object rewardList = map.get("detailEmployeeRewardExperience");
			Object technicalList = map.get("detailEmployeeTechnicalTitle");
			Object workList = map.get("detailEmployeeWorkExperience");
			if(!BaseHelpUtils.isNullOrEmpty(educationList)){
				values.put("detailEmployeeEducationInformation", educationList);
			}
			if(!BaseHelpUtils.isNullOrEmpty(familyList)){
				values.put("detailEmployeeFamilyInformation", familyList);
			}
			if(!BaseHelpUtils.isNullOrEmpty(rewardList)){
				values.put("detailEmployeeRewardExperience", rewardList);
			}
			if(!BaseHelpUtils.isNullOrEmpty(technicalList)){
				values.put("detailEmployeeTechnicalTitle", technicalList);
			}
			if(!BaseHelpUtils.isNullOrEmpty(workList)){
				values.put("detailEmployeeWorkExperience", workList);
			}
			
		}
		return values;
	}

}
