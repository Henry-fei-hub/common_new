package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class DSCompanyReportIntegralDataSource extends DBDataSource {

    public static DSCompanyReportIntegralDataSource instance = null;

    public static DSCompanyReportIntegralDataSource getInstance() {
        if (instance == null) {
            instance = new DSCompanyReportIntegralDataSource("DSConpanyReportIntegral");
        }
        return instance;
    }

    private final DataSourceTextField percentField;
    private final DataSourceFloatField achieveIntegralField;
    private final DataSourceFloatField signingMoneySumField;
    
    private final DataSourceFloatField sheetTotalIntegralField;
    private final DataSourceFloatField companySubsidyField;
    private final DataSourceFloatField winIntegralField;
    
    private final DataSourceFloatField sheetIntegralField;
    private final DataSourceFloatField payCompanySubsidyField;
    private final DataSourceFloatField payWinIntegralField;
    private final DataSourceFloatField payComplaintIntegralField;

    public DSCompanyReportIntegralDataSource(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_CUSTOM);
        setActionName("ConpanyReportIntegral");

        percentField = new DataSourceTextField("percent", "比率");
        percentField.setLength(64);
        percentField.setRequired(false);
        
        achieveIntegralField = new DataSourceFloatField("achieveIntegral", "积分余额");
        achieveIntegralField.setLength(64);
        achieveIntegralField.setRequired(false);
        
        signingMoneySumField = new DataSourceFloatField("signingMoneySum", "合同总额");
        signingMoneySumField.setLength(64);
        signingMoneySumField.setRequired(false);
        
        sheetTotalIntegralField = new DataSourceFloatField("sheetTotalIntegral", "订单总积分");
        sheetTotalIntegralField.setLength(64);
        sheetTotalIntegralField.setRequired(false);
        
        companySubsidyField = new DataSourceFloatField("companySubsidy", "公司补贴");
        companySubsidyField.setLength(64);
        companySubsidyField.setRequired(false);
        
        winIntegralField = new DataSourceFloatField("winIntegral", "中标奖金");
        winIntegralField.setLength(64);
        winIntegralField.setRequired(false);
        
        
        sheetIntegralField = new DataSourceFloatField("sheetIntegral", "订单积分");
        sheetIntegralField.setLength(64);
        sheetIntegralField.setRequired(false);
        
        payCompanySubsidyField = new DataSourceFloatField("payCompanySubsidy", "公司补贴(支出)");
        payCompanySubsidyField.setLength(64);
        payCompanySubsidyField.setRequired(false);
        
        payWinIntegralField = new DataSourceFloatField("payWinIntegral", "中标奖金(支出)");
        payWinIntegralField.setLength(64);
        payWinIntegralField.setRequired(false);
        
        payComplaintIntegralField = new DataSourceFloatField("payComplaintIntegral", "投诉保证金");
        payComplaintIntegralField.setLength(64);
        payComplaintIntegralField.setRequired(false);
        

        setFields(percentField,achieveIntegralField,signingMoneySumField,sheetTotalIntegralField,companySubsidyField,winIntegralField,sheetIntegralField,payCompanySubsidyField,payWinIntegralField,payComplaintIntegralField);
    }

}
