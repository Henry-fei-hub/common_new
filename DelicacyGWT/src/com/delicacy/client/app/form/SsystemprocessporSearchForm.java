package com.delicacy.client.app.form;

import com.delicacy.client.app.datasource.CDSsystemprocesspor;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.KeyValueManager;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;

public class SsystemprocessporSearchForm extends SearchForm {

    private final PickTreeItem processTypeIdItem;

    public SsystemprocessporSearchForm() {
        setWidth100();
        setHeight100();
        setDataSource(CDSsystemprocesspor.getInstance());
        processTypeIdItem = new PickTreeItem("processTypeId", "流程类型");
        processTypeIdItem.setWidth("*");
        processTypeIdItem.setCanSelectParentItems(true);
        processTypeIdItem.setValueField("treeId");
        processTypeIdItem.setValueTree(KeyValueManager.getTree("system_process_types", "0"));

        setItems(processTypeIdItem);

        setNumCols(3);
        ClientUtil.searchFormProcessAccordingToDevice(this);
    }

}
