package com.delicacy.client.app.form;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.UploadEmployeeOnboardInformation;
import com.delicacy.client.app.datasource.DSEmployeeOnboardInformation;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.panel.CommonUploadWindow;
import com.delicacy.client.panel.UploadCallBack;
import com.delicacy.client.ui.AbstractWizadPage;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;


public class EmployeeOnboardInformationEmployeeattachmentUpdate extends AbstractWizadPage
{
	private final TextItem cardAttachmentItem;
	private final TextItem cardAttachmentIdItem;
	private final TextItem educationProofItem;
	private final TextItem educationProofIdItem;
	private final TextItem degreeProofItem;
	private final TextItem degreeProofIdItem;
	private final TextItem laborAttachmentsItem;
	private final TextItem laborAttachmentIdItem;
	private final TextItem technicalAttachmentItem;
	private final TextItem technicalAttachmentIdItem;
	private final TextItem attachmentRemarkItem;
	private final TextItem bankCardAttachmentItem;
	private final TextItem bankCardAttachmentIdItem;
	private final ButtonItem uploadButton1;
	private final ButtonItem uploadButton2;
	private final ButtonItem uploadButton3;
	private final ButtonItem uploadButton4;
	private final ButtonItem uploadButton5;
	private final ButtonItem uploadButton6;

	private Map<String,Object> param = new HashMap<>();
	private UploadEmployeeOnboardInformation uploadPanel;
	private static final Logger __logger = Logger.getLogger("");

	public EmployeeOnboardInformationEmployeeattachmentUpdate() {
		DSEmployeeOnboardInformation ds = DSEmployeeOnboardInformation.getInstance();
		__form.setWidth100();
		__form.setHeight100();
		cardAttachmentItem = new TextItem("cardAttachment", "身份证附件");
		cardAttachmentItem.setWidth("*");
		cardAttachmentItem.setCanEdit(false);
		__formItems.add(cardAttachmentItem);
		cardAttachmentItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!BaseHelpUtils.isNullOrEmpty(cardAttachmentItem.getValue())){
					Window.open(cardAttachmentItem.getValue().toString(), "查看附件", "");
				}
			}
		});
		
		cardAttachmentIdItem= new TextItem("cardAttachmentId", "身份证附件ID");
		cardAttachmentIdItem.hide();
		__formItems.add(cardAttachmentIdItem);
		
		uploadButton1 = new ButtonItem("上传身份证");
		uploadButton1.setStartRow(false);  
		uploadButton1.setWidth(120);
		uploadButton1.setVAlign(VerticalAlignment.BOTTOM);
		uploadButton1.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
				new CommonUploadWindow("上传身份证", false, 50, 2, 1, new UploadCallBack() {
					
					@Override
					public void execute(Map<String, Record> data) {
						// 上传附件文件成功
						for(String key : data.keySet()) {
							String fileId = data.get(key).getAttribute("fileId");
							String fileUrl = data.get(key).getAttribute("fileUrl");
							cardAttachmentItem.setValue(fileUrl);
							cardAttachmentIdItem.setValue(fileId);
						}
					}
				});
            }  
        }); 
		__formItems.add(uploadButton1);
		
		
		educationProofItem = new TextItem("educationProof", "学历证");
		educationProofItem.setWidth("*");
		educationProofItem.setCanEdit(false);
		__formItems.add(educationProofItem);
		educationProofItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!BaseHelpUtils.isNullOrEmpty(educationProofItem.getValue())){
					Window.open(educationProofItem.getValue().toString(), "查看附件", "");
				}
			}
		});
		
		educationProofIdItem = new TextItem("educationProofId", "学历证ID");
		educationProofIdItem.hide();
		__formItems.add(educationProofIdItem);
		
		uploadButton2 = new ButtonItem("上传学历证");
		uploadButton2.setStartRow(false);  
		uploadButton2.setWidth(120);
		uploadButton2.setVAlign(VerticalAlignment.BOTTOM);
		uploadButton2.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {
				new CommonUploadWindow("上传学历证", false, 50, 2, 1, new UploadCallBack() {
					
					@Override
					public void execute(Map<String, Record> data) {
						// 上传附件文件成功
						for(String key : data.keySet()) {
							String fileId = data.get(key).getAttribute("fileId");
							String fileUrl = data.get(key).getAttribute("fileUrl");
							educationProofItem.setValue(fileUrl);
							educationProofIdItem.setValue(fileId);
						}
					}
				});
            }  
        }); 
		__formItems.add(uploadButton2);
		
		
		degreeProofItem = new TextItem("degreeProof", "学位证");
		degreeProofItem.setWidth("*");
		degreeProofItem.setCanEdit(false);
		__formItems.add(degreeProofItem);
		degreeProofItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!BaseHelpUtils.isNullOrEmpty(degreeProofItem.getValue())){
					Window.open(degreeProofItem.getValue().toString(), "查看附件", "");
				}
			}
		});
		
		degreeProofIdItem = new TextItem("degreeProofId", "学位证ID");
		degreeProofIdItem.hide();
		__formItems.add(degreeProofIdItem);
		
		uploadButton3 = new ButtonItem("上传学位证");
		uploadButton3.setStartRow(false);  
		uploadButton3.setWidth(120);
		uploadButton3.setVAlign(VerticalAlignment.BOTTOM);
		uploadButton3.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {
				new CommonUploadWindow("上传学位证", false, 50, 2, 1, new UploadCallBack() {
					
					@Override
					public void execute(Map<String, Record> data) {
						// 上传附件文件成功
						for(String key : data.keySet()) {
							String fileId = data.get(key).getAttribute("fileId");
							String fileUrl = data.get(key).getAttribute("fileUrl");
							degreeProofItem.setValue(fileUrl);
							degreeProofIdItem.setValue(fileId);
						}
					}
				});
            }  
        }); 
		__formItems.add(uploadButton3);
		

		laborAttachmentsItem = new TextItem("laborAttachments", "劳动合同附件");
		laborAttachmentsItem.setWidth("*");
		laborAttachmentsItem.setCanEdit(false);
		__formItems.add(laborAttachmentsItem);
		laborAttachmentsItem.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(!BaseHelpUtils.isNullOrEmpty(laborAttachmentsItem.getValue())){
					Window.open(laborAttachmentsItem.getValue().toString(), "查看附件", "");
				}
			}
		});

		laborAttachmentIdItem = new TextItem("laborAttachmentId", "劳动合同附件ID");
		laborAttachmentIdItem.hide();
		__formItems.add(laborAttachmentIdItem);

		uploadButton4 = new ButtonItem("上传劳动合同");
		uploadButton4.setStartRow(false);
		uploadButton4.setWidth(120);
		uploadButton4.setVAlign(VerticalAlignment.BOTTOM);
		uploadButton4.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
				new CommonUploadWindow("上传劳动合同", false, 50, 2, 1, new UploadCallBack() {

					@Override
					public void execute(Map<String, Record> data) {
						// 上传附件文件成功
						for(String key : data.keySet()) {
							String fileId = data.get(key).getAttribute("fileId");
							String fileUrl = data.get(key).getAttribute("fileUrl");
							laborAttachmentsItem.setValue(fileUrl);
							laborAttachmentIdItem.setValue(fileId);
						}
					}
				});
            }
        });
		__formItems.add(uploadButton4);
		
		technicalAttachmentItem = new TextItem("technicalAttachment", "职称证附件");
		technicalAttachmentItem.setWidth("*");
		technicalAttachmentItem.setCanEdit(false);
		__formItems.add(technicalAttachmentItem);
		technicalAttachmentItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!BaseHelpUtils.isNullOrEmpty(technicalAttachmentItem.getValue())){
					Window.open(technicalAttachmentItem.getValue().toString(), "查看附件", "");
				}
			}
		});
		
		technicalAttachmentIdItem = new TextItem("technicalAttachmentId", "职称证附件ID");
		technicalAttachmentIdItem.hide();
		__formItems.add(technicalAttachmentIdItem);
		
		uploadButton5 = new ButtonItem("上传职称证明");
		uploadButton5.setStartRow(false);  
		uploadButton5.setWidth(120);
		uploadButton5.setVAlign(VerticalAlignment.BOTTOM);
		uploadButton5.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {
				new CommonUploadWindow("上传职称证明", false, 50, 2, 1, new UploadCallBack() {
					
					@Override
					public void execute(Map<String, Record> data) {
						// 上传附件文件成功
						for(String key : data.keySet()) {
							String fileId = data.get(key).getAttribute("fileId");
							String fileUrl = data.get(key).getAttribute("fileUrl");
							technicalAttachmentItem.setValue(fileUrl);
							technicalAttachmentIdItem.setValue(fileId);
						}
					}
				});
            }  
        }); 
		__formItems.add(uploadButton5);
		
		
		
		bankCardAttachmentItem = new TextItem("bankCardAttachment", "银行卡附件");
		bankCardAttachmentItem.setWidth("*");
		bankCardAttachmentItem.setCanEdit(false);
		__formItems.add(bankCardAttachmentItem);
		bankCardAttachmentItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(!BaseHelpUtils.isNullOrEmpty(bankCardAttachmentItem.getValue())){
					Window.open(bankCardAttachmentItem.getValue().toString(), "查看附件", "");
				}
			}
		});
		
		bankCardAttachmentIdItem = new TextItem("bankCardAttachmentId", "银行卡附件ID");
		bankCardAttachmentIdItem.hide();
		__formItems.add(bankCardAttachmentIdItem);
		
		uploadButton6 = new ButtonItem("上传银行卡");
		uploadButton6.setStartRow(false);  
		uploadButton6.setWidth(120);
		uploadButton6.setVAlign(VerticalAlignment.BOTTOM);
		uploadButton6.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {
				new CommonUploadWindow("上传银行卡", false, 50, 2, 1, new UploadCallBack() {
					
					@Override
					public void execute(Map<String, Record> data) {
						// 上传附件文件成功
						for(String key : data.keySet()) {
							String fileId = data.get(key).getAttribute("fileId");
							String fileUrl = data.get(key).getAttribute("fileUrl");
							bankCardAttachmentItem.setValue(fileUrl);
							bankCardAttachmentIdItem.setValue(fileId);
						}
					}
				});
            }  
        }); 
		__formItems.add(uploadButton6);


		
		attachmentRemarkItem = new TextItem("attachmentRemark","附件备注");
		attachmentRemarkItem.setWidth(380);
		__formItems.add(attachmentRemarkItem);
		
		__form.setItems(getFormItemArray());
		__form.setDataSource(ds);
		__form.setNumCols(4);
		ClientUtil.DynamicFormProcessAccordingToDevice(__form);
		setPageMode(PAGE_MODE_UPDATE);
		attachmentRemarkItem.setColSpan(4);
		setName("职员相关附件");
		addMember(__form);
	}

	@Override
	public void setValueManage(ValuesManager manager) {
		manager.setDataSource(DSEmployeeOnboardInformation.getInstance());
		manager.addMember(__form);
	}

	@Override
	public boolean checkData() {
		return true;
	}

	@Override
	public void startEdit() {
		if(getRecord() != null) {
			__form.editRecord(getRecord());
		}
	}

	@Override
	public java.util.Map getValuesAsMap() {
		/*Map<String,Object> value = new HashMap<>();
		value.put("cardAttachment", cardAttachmentItem.getValue());
		value.put("educationProof", educationProofItem.getValue());
		value.put("degreeProof", degreeProofItem.getValue());
		value.put("laborAttachments", laborAttachmentsItem.getValue());
		value.put("technicalAttachment", technicalAttachmentItem.getValue());
		value.put("bankCardAttachment", bankCardAttachmentItem.getValue());
		value.put("attachmentRemark", attachmentRemarkItem.getValue());
		return value;*/
		return __form.getValues();
	}


}
