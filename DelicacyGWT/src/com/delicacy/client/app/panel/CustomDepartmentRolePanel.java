package com.delicacy.client.app.panel;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RemoveRecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RemoveRecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.SelectionAppearance;
import java.util.logging.Logger;
import com.delicacy.client.app.datasource.DSDepartmentRole;
import com.smartgwt.client.util.SC;

public class CustomDepartmentRolePanel extends AbstractWizadPage {

    private static final Logger __logger = Logger.getLogger("");
    public static DelicacyListGrid SourceGrid;

    public CustomDepartmentRolePanel() {
    	SourceGrid = new DelicacyListGrid(true);
    	SourceGrid.setCanEdit(false);
        VLayout SearchSourceLayout = new VLayout();
        SearchSourceLayout.setWidth100();
        SourceGrid.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
        SourceGrid.setDataSource(DSDepartmentRole.getInstance());
        SourceGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 先检索部门,如果没有指定部门，则返回
				if (departmentId <= 0) {
					SC.say("提示", "未指定部门,请先指定部门");
					return;
				}
				ListGridRecord[] selected = SourceGrid.getSelectedRecords();
				// 获取到角色id
				int roleId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("roleId"));
				if (roleId <= 0) {
					return;
				}
				// 获取应用系统代码
				int applicationId = ClientUtil.checkAndGetIntValue(selected[0].getAttribute("applicationId"));
				// 获取角色名称
				String roleName = selected[0].getAttribute("roleName");
				DepartmentRoleFunPanel.functionTitle.setContents(roleName+"的功能信息");
				DepartmentRoleFunPanel.function.setDepartmentId(departmentId);
				DepartmentRoleFunPanel.function.setRoleId(roleId);
				DepartmentRoleFunPanel.function.startEdit();
			}
		});
        
        SourceGrid.addRemoveRecordClickHandler(new RemoveRecordClickHandler() {
			@Override
			public void onRemoveRecordClick(RemoveRecordClickEvent event) {
				int rowNum = event.getRowNum();
				ListGridRecord record = SourceGrid.getRecord(rowNum);
				// 获取到角色id
				int roleId = ClientUtil.checkAndGetIntValue(record.getAttribute("roleId"));
				if (roleId <= 0) {
					return;
				}
				// 获取应用系统代码
				int applicationId = ClientUtil.checkAndGetIntValue(record.getAttribute("applicationId"));
				// 删除数据
				onDepartmentRoleUpdate(1, roleId, applicationId);
				//判断如果当前功能的角色id=当前要删除的角色id则重置
				if(DepartmentRoleFunPanel.function.getRoleId() == roleId){
					DepartmentRoleFunPanel.functionTitle.setContents("功能信息");
					DepartmentRoleFunPanel.function.setDepartmentId(departmentId);
					DepartmentRoleFunPanel.function.setRoleId(-1);
					DepartmentRoleFunPanel.function.startEdit();
				}
			}
		});
        
        SearchSourceLayout.setHeight100();
        SearchSourceLayout.setLayoutTopMargin(10);
        SearchSourceLayout.setLayoutRightMargin(5);
        SearchSourceLayout.setMembersMargin(10);
        SearchSourceLayout.addMember(SourceGrid);
        addMember(SearchSourceLayout);
    }

    @Override
    public void startEdit() {
    	Map condition = new HashMap();
		condition.put("departmentId", departmentId);
        DBDataSource.callOperation("EP_DepartmentRoles", "find", condition, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    SourceGrid.setData(dsResponse.getData());
                }
            }
        });
    }

    
    /**
     * 双击角色gird时进行更新操作
     * @param flag:1:表示取消操作;2:表示选中操作
     * @param roleId 
     * @param applicationId 
     */
    public void onDepartmentRoleUpdate(int flag,final int roleId,final int applicationId){
        Map param = new LinkedHashMap();
        param.put("flag", flag);
        param.put("departmentId", departmentId);
        param.put("roleId", roleId);
        param.put("applicationId", applicationId);
        String message = MapUtils.toJSON(param);
        //更新方法
        DBDataSource.callOperation("EP_OnDepartmentRoleUpdate", "update", message, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                } else {
                    Map errors = dsResponse.getErrors();
                    SC.say("operate failure" + errors);
                }
            }
        });
    }
    
    /**
     * 重新加载功能信息
     * @param content
     * @param departmentId
     * @param roleId 
     */
    public void reloadFunctionGrid(String content,int departmentId,int roleId){
        DepartmentRoleFunPanel.functionTitle.setContents(content);
        DepartmentRoleFunPanel.function.setDepartmentId(departmentId);
        DepartmentRoleFunPanel.function.setRoleId(roleId);
        DepartmentRoleFunPanel.function.startEdit();
    }

    @Override
    public boolean checkData() {
        for (ListGridRecord r : SourceGrid.getSelectedRecords()) {
            __logger.info(MapUtils.convertRecordToMap(SourceGrid.getDataSource(), r).toString());
        }
        return true;
    }

    @Override
    public Set<String> getItemNames() {
        Set<String> res = new HashSet<>();
        res.add("detailRoleFunction");
        return res;
    }

    @Override
    public Map getValuesAsMap() {
        Map param = new HashMap();
        ListGridRecord[] selected = SourceGrid.getSelectedRecords();
        List resList = new ArrayList();
        for (ListGridRecord r : selected) {
            Map lm = new HashMap();
            lm.put("roleId", r.getAttribute("roleId"));
            lm.put("applicationId", r.getAttribute("applicationId"));
            resList.add(lm);
        }
        param.put("detailRoleFunction", resList);
        return param;
    }

    @Override
    public String getName() {
        return "";
    }

    private int departmentId;

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

}
