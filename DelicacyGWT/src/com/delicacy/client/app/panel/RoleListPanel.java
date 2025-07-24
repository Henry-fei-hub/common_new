package com.delicacy.client.app.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.PanelFactory;
import com.delicacy.client.app.form.RoleListSearchForm;
import com.delicacy.client.data.DataEditEvent;
import com.delicacy.client.data.DataEditedHandler;
import com.delicacy.client.management.datasource.DSRole;
import com.delicacy.client.ui.AbstractSearchPanel;
import com.delicacy.client.ui.DelicacyListGrid;
import com.delicacy.client.ui.LayoutConstant;
import com.delicacy.client.ui.PopupWindow;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.SearchForm;

public class RoleListPanel extends AbstractSearchPanel implements HasHandlers
{


	private static final Logger __LOGGER = Logger.getLogger("");
	private PopupWindow	parentWindow;
	private DepartmentLinkRolePanel linkPanel;
	
	public static class Factory implements PanelFactory {
		private String id;
		@Override
		public Canvas create() {
			RoleListPanel cm = new RoleListPanel();
			id = cm.getID();
			return cm;
		}

		@Override
		public String getID() {
			return id;
		}

		@Override
		public String getDescription() {
			return "SystemProcessList";
		}

	}

	@Override
	public void init(){
		super.init();
		__controlPosition = LayoutConstant.RIGHT;
		__needPagenation = false;
	}

	@Override
	public void initComponents() {
		IButton refreshButton = new IButton("刷新");
		controlLayout.addMember(refreshButton);
		refreshButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				commonQuery();
			}
		});

		IButton rightMoveData = new IButton("> >");
		controlLayout.addMember(rightMoveData);
		rightMoveData.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DataEditEvent dee = new DataEditEvent();
                fireEvent(dee);
				
			}
		});
		
		resultGrid.addDoubleClickHandler(new DoubleClickHandler() {
			
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				DataEditEvent dee = new DataEditEvent();
                fireEvent(dee);
			}
		});
		commonQuery();
	}

	@Override
	public boolean checkSearchCriteria(Map criteria) {
		String sql = "";
		String roleName = BaseHelpUtils.getString(criteria.get("roleName"));
		criteria.remove("roleName");
		if(!BaseHelpUtils.isNullOrEmpty(roleName)) {
			sql = " role_name like '%" + roleName + "%'";
		}
		if(null != linkPanel) {
			Record[] linkRecords = linkPanel.grid.getRecords();
			StringBuilder sb = new StringBuilder();
			for(Record r : linkRecords) {
				String roleId = r.getAttribute("roleId");
				if(BaseHelpUtils.isNullOrEmpty(roleId)) {
					continue;
				}else {
					if(sb.length() > 0) {
						sb.append(",");
					}
					sb.append(roleId);
				}
			}
			if(sb.length() > 0) {
				if(!BaseHelpUtils.isNullOrEmpty(sql)) {
					sql = sql + " and ";
				}
				sql = sql + "role_id not in (" + sb.toString() + ") order by role_id";
			}
		}
		if(!BaseHelpUtils.isNullOrEmpty(sql)) {
			criteria.put("addtionalCondition", sql);
		}
		return true;
	}


	@Override
	public SearchForm generateSearchForm() {
		return new RoleListSearchForm();
	}

	@Override
	public DataSource getDataSource() {
		return DSRole.getInstance();
	}
	
	public PopupWindow getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(PopupWindow parentWindow) {
		this.parentWindow = parentWindow;
	}

	protected final HandlerManager handlerManager = new HandlerManager(this);
    protected final List<DataEditedHandler> __dataEditedHandler = new ArrayList<>();

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addDataEditedHandler(DataEditedHandler handler) {
        return handlerManager.addHandler(DataEditEvent.TYPE, handler);
    }

    public DelicacyListGrid getGrid(){
    	return resultGrid;
    }

	public DepartmentLinkRolePanel getLinkPanel() {
		return linkPanel;
	}

	public void setLinkPanel(DepartmentLinkRolePanel linkPanel) {
		this.linkPanel = linkPanel;
	}
    
}

