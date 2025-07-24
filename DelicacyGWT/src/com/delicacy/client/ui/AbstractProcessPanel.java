package com.delicacy.client.ui;

import static com.delicacy.client.ui.GenericWizadWindow.BACKGROUND_COLOR;
import static com.delicacy.client.ui.GenericWizadWindow.BORDER_STYLE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delicacy.client.data.ClientUtil;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 *
 * @author guangxun
 */
public abstract class AbstractProcessPanel extends VLayout {

    public final static int PAGE_DISPLAY_NAV = 0;
    public final static int PAGE_DIAPLAY_ONE = 1;

    public final static int LAYOUT_LEFTRIGHT = 0;
    public final static int LAYOUT_TOPBOTTOM = 1;
    
    protected int __processId = 0;
    protected int __processTypeId = 0;
    protected boolean needApprovalComment = false;
    protected Window parentWindow;

    public int getProcessTypeId() {
		return __processTypeId;
	}

	public void setProcessTypeId(int __processTypeId) {
		this.__processTypeId = __processTypeId;
	}

	public int getProcessId() {
		return __processId;
	}

	public void setProcessId(int __processId) {
		this.__processId = __processId;
	}

	public boolean isNeedApprovalComment() {
		return needApprovalComment;
	}

	public void setNeedApprovalComment(boolean needApprovalComment) {
		this.needApprovalComment = needApprovalComment;
	}

	public Window getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(Window parentWindow) {
		this.parentWindow = parentWindow;
	}

	public void initComponents() {
        //设置页面属性
        setWidth100();
        setHeight100();
        __pages = getPages();
        for (AbstractWizadPage __page : __pages) {
            if (__page.getPageMode() != AbstractWizadPage.PAGE_MODE_ADD) {
                setPageMode(AbstractWizadPage.PAGE_MODE_UPDATE);
            }
            __page.setBorder(BORDER_STYLE);
            __page.setPadding(5);
            __page.setBackgroundColor(BACKGROUND_COLOR);
        }
        VLayout mainLayout = new VLayout();
        mainLayout.setOverflow(Overflow.HIDDEN);
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        switch (__pageMode) {
            case PAGE_DISPLAY_NAV:
                __pageNum = getPageCount();
                customLayout = new HLayout();
                customLayout.setWidth100();
                customLayout.setHeight100();
                customLayout.setPadding(5);
                customLayout.addMember(__pages.get(getCurrentPage()));
                mainLayout.addMember(customLayout);
                break;
            default:
                __pageNum = 1;
                switch (__layoutMode) {
                    case LAYOUT_LEFTRIGHT:
                        HLayout hl = new HLayout();
                        hl.setWidth100();
                        hl.setHeight100();
                        hl.setPadding(5);
                        for (AbstractWizadPage __page : __pages) {
                            hl.addMember(__page);
                        }
                        mainLayout.addMember(hl);
                        break;
                    default:
                        VLayout vl = new VLayout();
                        vl.setWidth100();
                        vl.setHeight100();
                        vl.setPadding(5);
                        for (AbstractWizadPage __page : __pages) {
                            vl.addMember(__page);
                        }
                        mainLayout.addMember(vl);
                        break;
                }
                break;
        }
        addMember(mainLayout);
    }

    public Map getValues() {
        Map values = new HashMap();
        Map notused = new HashMap();
        for (AbstractWizadPage p : __pages) {
            notused.putAll(ClientUtil.putMapAll(values, p.getValuesAsMap(), p.getItemNames()));
        }
        if (!notused.isEmpty()) {
            ClientUtil.putMapAll(values, notused, null);
        }
        ClientUtil.putMapAll(values, valueManager.getValues(), null);
        return values;
    }

    public void startEdit() {
        for (AbstractWizadPage __page : __pages) {
            __page.startEdit();
        }
    }

    public void setAllPageData() {
        for (AbstractWizadPage __page : __pages) {
            __page.setRecord(__data);
        }
    }

    public void next() {
        if (__pages == null) {
            return;
        }
        if (currentPage >= __pages.size() - 1) {
            return;
        }
        customLayout.removeMember(__pages.get(currentPage));
        currentPage++;
        customLayout.addMember(__pages.get(currentPage));
    }

    public void previous() {
        if (__pages == null) {
            return;
        }
        if (currentPage <= 0) {
            return;
        }
        customLayout.removeMember(__pages.get(currentPage));
        currentPage--;
        customLayout.addMember(__pages.get(currentPage));
    }
    
    public void refreshPageData() {
        if (__pages == null) {
            __pages = getPages();
        }
        for (AbstractWizadPage wp : __pages) {
            wp.setRecord(getData());
            wp.startEdit();
        }
    }

    public abstract void load();

    public abstract int getPageCount();

    public abstract boolean checkData(Map data);

    protected int currentPage = 0;
    protected ValuesManager valueManager = new ValuesManager();
    protected List<AbstractWizadPage> __pages;
    protected DSCallback callback;
    protected Map<String, FormItem> formItems = new HashMap<>();
    protected HLayout customLayout = null;
    protected String businessId = null;
    protected int __pageNum = 1;
    protected int pageMode = AbstractWizadPage.PAGE_MODE_ADD;
    protected Record __data;
    protected int __pageMode = PAGE_DIAPLAY_ONE;
    protected int __layoutMode = LAYOUT_TOPBOTTOM;
    protected String customWidth = "100%";
    protected String customHeight = "100%";
    protected int __customerOperation = 0;
    protected Record __instanceData;

    public int getCustomerOperation() {
		return __customerOperation;
	}

	public void setCustomerOperation(int __customerOperation) {
		this.__customerOperation = __customerOperation;
	}

	/**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the valueManager
     */
    public ValuesManager getValueManager() {
        return valueManager;
    }

    /**
     * @param valueManager the valueManager to set
     */
    public void setValueManager(ValuesManager valueManager) {
        this.valueManager = valueManager;
    }

    /**
     * @return the __pages
     */
    public abstract List<AbstractWizadPage> getPages();

    /**
     * @param __pages the __pages to set
     */
    public void setPages(List<AbstractWizadPage> __pages) {
        this.__pages = __pages;
    }

    /**
     * @return the callback
     */
    public DSCallback getCallback() {
        return callback;
    }

    /**
     * @param callback the callback to set
     */
    public void setCallback(DSCallback callback) {
        this.callback = callback;
    }

    /**
     * @return the formItems
     */
    public Map<String, FormItem> getFormItems() {
        return formItems;
    }

    /**
     * @param formItems the formItems to set
     */
    public void setFormItems(Map<String, FormItem> formItems) {
        this.formItems = formItems;
    }

    /**
     * @return the customLayout
     */
    public HLayout getCustomLayout() {
        return customLayout;
    }

    /**
     * @param customLayout the customLayout to set
     */
    public void setCustomLayout(HLayout customLayout) {
        this.customLayout = customLayout;
    }

    /**
     * @return the businessId
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * @param businessId the businessId to set
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    /**
     * @return the pageMode
     */
    public int getPageMode() {
        return pageMode;
    }

    /**
     * @param pageMode the pageMode to set
     */
    public void setPageMode(int pageMode) {
        this.pageMode = pageMode;
    }

    /**
     * @return the __data
     */
    public Record getData() {
        return __data;
    }

    /**
     * @param __data the __data to set
     */
    public void setData(Record __data) {
        this.__data = __data;
    }

    /**
     * @return the __pageNum
     */
    public int getPageNum() {
        return __pageNum;
    }

    /**
     * @param __pageNum the __pageNum to set
     */
    public void setPageNum(int __pageNum) {
        this.__pageNum = __pageNum;
    }

    /**
     * @return the __layoutMode
     */
    public int getLayoutMode() {
        return __layoutMode;
    }

    /**
     * @param __layoutMode the __layoutMode to set
     */
    public void setLayoutMode(int __layoutMode) {
        this.__layoutMode = __layoutMode;
    }

	public String getCustomWidth() {
		return customWidth;
	}

	public void setCustomWidth(String customWidth) {
		this.customWidth = customWidth;
	}

	public String getCustomHeight() {
		return customHeight;
	}

	public void setCustomHeight(String customHeight) {
		this.customHeight = customHeight;
	}

    public Record get__instanceData() {
        return __instanceData;
    }

    public void set__instanceData(Record __instanceData) {
        this.__instanceData = __instanceData;
    }
}
