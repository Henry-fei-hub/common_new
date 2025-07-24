package com.delicacy.client.app.panel;

import java.util.*;
import com.delicacy.client.MapUtils;
import com.delicacy.client.ui.AbstractWizadPage;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.types.SelectionAppearance;
import java.util.logging.Logger;
import com.delicacy.client.app.datasource.DSDepartmentRole;
import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.util.SC;

public class CustomRolePanel extends AbstractWizadPage {

	private static final Logger __logger = Logger.getLogger("");
	private final DelicacyListGrid SourceGrid = new DelicacyListGrid();

	public CustomRolePanel() {

		VLayout SearchSourceLayout = new VLayout();
		SearchSourceLayout.setWidth100();
		SourceGrid.setSelectionAppearance(SelectionAppearance.ROW_STYLE);
		SourceGrid.setDataSource(DSDepartmentRole.getInstance());
		SourceGrid.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
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
				// 获取右边所有的gird数据
				ListGridRecord[] records = CustomDepartmentRolePanel.SourceGrid.getRecords();
				// 根据当前roleId检索是否已存在右边的grid，如果存在则不新增，如果不存在则新增
				if (records.length > 0) {
					for (ListGridRecord record : records) {
						if (ClientUtil.checkAndGetIntValue(record.getAttribute("roleId")) == roleId) {
							return;
						}
					}
				}
				// 左边gird新增一条记录
				Record record = new Record();
				record.setAttribute("roleId", roleId);
				record.setAttribute("roleName", roleName);
				record.setAttribute("applicationId", applicationId);
				CustomDepartmentRolePanel.SourceGrid.addData(record);
				// 插入数据
				onDepartmentRoleUpdate(2, roleId, applicationId);
				DepartmentRoleFunPanel.functionTitle.setContents(roleName+"的功能信息");
				DepartmentRoleFunPanel.function.setDepartmentId(departmentId);
				DepartmentRoleFunPanel.function.setRoleId(roleId);
				DepartmentRoleFunPanel.function.startEdit();
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
		DBDataSource.callOperation("ST_Role", "find", new HashMap(), new DSCallback() {
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
	 * 
	 * @param flag:1:表示取消操作;2:表示选中操作
	 * @param roleId
	 * @param applicationId
	 */
	public void onDepartmentRoleUpdate(int flag, final int roleId, final int applicationId) {
		Map param = new LinkedHashMap();
		param.put("flag", flag);
		param.put("departmentId", departmentId);
		param.put("roleId", roleId);
		param.put("applicationId", applicationId);
		String message = MapUtils.toJSON(param);
		// 更新方法
		DBDataSource.callOperation("EP_OnDepartmentRoleUpdate", "update", message, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					// 执行成功后重新刷新当前表格
				} else {
					Map errors = dsResponse.getErrors();
					SC.say("operate failure" + errors);
				}
			}
		});
	}

	/**
	 * 重新加载功能信息
	 * 
	 * @param content
	 * @param departmentId
	 * @param roleId
	 */
	public void reloadFunctionGrid(String content, int departmentId, int roleId) {
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
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

}
