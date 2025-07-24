package com.delicacy.client.domain.datasource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;

public class CDDomainValue extends DataSource {

    public static CDDomainValue instance = null;

    public static CDDomainValue getInstance() {
        if (instance == null) {
            instance = new CDDomainValue("CDDomainValue");
        }
        return instance;
    }

    public CDDomainValue(String dataSourceID) {

        setID(dataSourceID);
        DataSourceIntegerField currentPageField
                = new DataSourceIntegerField("currentPage", "当前页");
        currentPageField.setRequired(true);
        currentPageField.setLength(10);
        currentPageField.setHidden(true);

        DataSourceIntegerField pageLinesField
                = new DataSourceIntegerField("pageLines", "每页行数");
        pageLinesField.setRequired(true);
        pageLinesField.setLength(10);
        pageLinesField.setHidden(true);

        setFields(currentPageField, pageLinesField);

        setClientOnly(true);
    }

}
