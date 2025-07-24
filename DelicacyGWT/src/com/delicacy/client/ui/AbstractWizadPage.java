package com.delicacy.client.ui;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Cao Guangxun
 */
public abstract class AbstractWizadPage extends HLayout {

    public final static int PAGE_MODE_ADD = 0;
    public final static int PAGE_MODE_UPDATE = 1;
    public final static int PAGE_TYPE_MASTER = 0;
    public final static int PAGE_TYPE_DETAIL = 1;

    public abstract Map getValuesAsMap();

    public abstract boolean checkData();

    public void reloadSourceData() {
    }

    public int getPageType() {
        return PAGE_TYPE_MASTER;
    }

    public abstract void startEdit();

    public void initEventHandlers() {
    }

    public Map<String, FormItem> getItems() {
        Map<String, FormItem> res = new HashMap<>();
        for (FormItem i : __formItems) {
            res.put(i.getName(), i);
        }
        return res;
    }

    public Set<String> getItemNames() {
        Set<String> res = new HashSet<>();
        for (FormItem i : __formItems) {
            res.add(i.getName());
        }
        return res;
    }

    public void setValueManage(ValuesManager manager) {
    }
	
	public boolean isTheValuesEmpty(){
		return false;
	}

    protected Record record = null;
    protected SearchForm searchForm;
    protected GenericWizadWindow parentWindow;
    protected int pageMode = PAGE_MODE_ADD;
    protected boolean haveSource = false;
    protected String name = "";
    protected List<FormItem> __formItems = new ArrayList<>();
    protected DynamicForm __form = new DynamicForm();
    protected ListGrid __dataGrid;
    protected Record __instanceData;
    

    public FormItem[] getFormItemArray() {
        return __formItems.toArray(new FormItem[__formItems.size()]);
    }

    public FormItem findNamedItem(String name){
        for(FormItem fi : __formItems){
            if(fi.getName().equals(name)){
                return fi;
            }
        }
        return null;
    }
    /**
     * @return the record
     */
    public Record getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(Record record) {
        this.record = record;
    }

    /**
     * @return the parentWindow
     */
    public GenericWizadWindow getParentWindow() {
        return parentWindow;
    }

    /**
     * @param parentWindow the parentWindow to set
     */
    public void setParentWindow(GenericWizadWindow parentWindow) {
        this.parentWindow = parentWindow;
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
     * @return the searchForm
     */
    public SearchForm getSearchForm() {
        return searchForm;
    }

    /**
     * @param searchForm the searchForm to set
     */
    public void setSearchForm(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     * @return the haveSource
     */
    public boolean isHaveSource() {
        return haveSource;
    }

    /**
     * @param haveSource the haveSource to set
     */
    public void setHaveSource(boolean haveSource) {
        this.haveSource = haveSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the __dataGrid
     */
    public ListGrid getDataGrid() {
        return __dataGrid;
    }

    /**
     * @param __dataGrid the __dataGrid to set
     */
    public void setDataGrid(ListGrid __dataGrid) {
        this.__dataGrid = __dataGrid;
    }

    public Record get__instanceData() {
        return __instanceData;
    }

    public void set__instanceData(Record __instanceData) {
        this.__instanceData = __instanceData;
    }
}
