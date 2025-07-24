package com.delicacy.client.app.datasource;

import com.delicacy.client.data.DBDataSource;
import com.smartgwt.client.data.fields.DataSourceLinkField;

public class DSMemployeeAttachment extends DBDataSource {

    public static DSMemployeeAttachment instance = null;

    public static DSMemployeeAttachment getInstance() {
        if (instance == null) {
            instance = new DSMemployeeAttachment("DSMemployeeAttachment");
        }
        return instance;
    }


    private final DataSourceLinkField cardAttachmentField;
    private final DataSourceLinkField laborAttachmentsField;
    private final DataSourceLinkField educationProofField;
    private final DataSourceLinkField degreeProofField;
    private final DataSourceLinkField technicalAttachmentField;
    private final DataSourceLinkField autographField;
    private final DataSourceLinkField bankCardAttachmentField;


    public DSMemployeeAttachment(String dataSourceID) {

        super();
        setID(dataSourceID);
        setActionMode(ACTION_MODE_TABLE);
        setActionName("Employee");

        cardAttachmentField = new DataSourceLinkField("cardAttachment", "身份证附件");
        cardAttachmentField.setLength(20);
        cardAttachmentField.setRequired(false);
        cardAttachmentField.setHidden(false);

        educationProofField = new DataSourceLinkField("educationProof", "学历证");
        educationProofField.setLength(20);
        educationProofField.setRequired(false);
        educationProofField.setHidden(false);

        degreeProofField = new DataSourceLinkField("degreeProof", "学位证");
        degreeProofField.setLength(20);
        degreeProofField.setRequired(false);
        degreeProofField.setHidden(false);

        laborAttachmentsField = new DataSourceLinkField("laborAttachments", "劳动合同附件");
        laborAttachmentsField.setLength(20);
        laborAttachmentsField.setRequired(false);
        laborAttachmentsField.setHidden(false);


        technicalAttachmentField = new DataSourceLinkField("technicalAttachment", "职称证附件");
        technicalAttachmentField.setLength(20);
        technicalAttachmentField.setHidden(false);

        bankCardAttachmentField = new DataSourceLinkField("bankCardAttachment", "银行卡附件");
        bankCardAttachmentField.setLength(20);
        bankCardAttachmentField.setRequired(false);
        bankCardAttachmentField.setHidden(false);

        autographField = new DataSourceLinkField("autograph", "简历附件");
        autographField.setLength(20);
        autographField.setRequired(false);
        autographField.setHidden(false);

        setFields(cardAttachmentField,educationProofField,degreeProofField,laborAttachmentsField,technicalAttachmentField,bankCardAttachmentField,autographField);
    }

}
