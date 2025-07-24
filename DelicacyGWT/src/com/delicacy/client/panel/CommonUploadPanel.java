package com.delicacy.client.panel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.moxieapps.gwt.uploader.client.Uploader;
import org.moxieapps.gwt.uploader.client.events.FileDialogCompleteEvent;
import org.moxieapps.gwt.uploader.client.events.FileDialogCompleteHandler;
import org.moxieapps.gwt.uploader.client.events.FileDialogStartEvent;
import org.moxieapps.gwt.uploader.client.events.FileDialogStartHandler;
import org.moxieapps.gwt.uploader.client.events.FileQueueErrorEvent;
import org.moxieapps.gwt.uploader.client.events.FileQueueErrorHandler;
import org.moxieapps.gwt.uploader.client.events.FileQueuedEvent;
import org.moxieapps.gwt.uploader.client.events.FileQueuedHandler;
import org.moxieapps.gwt.uploader.client.events.UploadCompleteEvent;
import org.moxieapps.gwt.uploader.client.events.UploadCompleteHandler;
import org.moxieapps.gwt.uploader.client.events.UploadErrorEvent;
import org.moxieapps.gwt.uploader.client.events.UploadErrorHandler;
import org.moxieapps.gwt.uploader.client.events.UploadProgressEvent;
import org.moxieapps.gwt.uploader.client.events.UploadProgressHandler;
import org.moxieapps.gwt.uploader.client.events.UploadSuccessEvent;
import org.moxieapps.gwt.uploader.client.events.UploadSuccessHandler;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.data.ClientUtil;
import com.delicacy.client.data.DBDataSource;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
//import com.google.gwt.widgetideas.client.ProgressBar;
import com.smartgwt.client.widgets.Progressbar;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;  
  
public class CommonUploadPanel extends VLayout { 
	private final static Logger logger = Logger.getLogger("");
	private boolean multiply = false; 
	private Integer fileSize = null;
	private Integer fileType = null;
	private Integer fileNumber = null;
	private Window parentWindow;
	private int deleteNum = 0;
	final Uploader uploader = new Uploader(); 
	Map<String, Record> data = new HashMap<>();
	
	public CommonUploadPanel(){
		
	}
	/**
	 * 
     * @param multiply	是否支持选择多文件， true:支持; false:不支持;
	 * @param fileSize	限制上传的文件大小，为空则不限制，单位：MB;单个文件最大不能超过5120MB; 多文件总大小不能超过51200MB;
	 * @param fileType	限制上传的文件类型，为空则不限制。1:图片; 2:PDF; 3:文档; 4:音频; 5:视频; 6:压缩文件
	 * @param fileNumber限制上传的文件个数，为空则不限制。该数值以文件上传成功的个数为准
	 */
	public CommonUploadPanel(boolean multiply, Integer fileSize, Integer fileType, Integer fileNumber){
		this.multiply = multiply;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.fileNumber = fileNumber;
	}
    public void initComponent(final UploadCallBack callBack) { 
    	setBackgroundColor("#fff");
    	setLayoutTopMargin(5);
    	setWidth100();
    	setHeight100();
        final VLayout progressBarPanel = new VLayout();
        progressBarPanel.setOverflow(Overflow.AUTO);
        progressBarPanel.setHeight(363);
        progressBarPanel.setLayoutLeftMargin(10);
        final Map<String, Progressbar> progressBars = new LinkedHashMap<String, Progressbar>();  
        final Map<String, ImgButton> cancelButtons = new LinkedHashMap<String, ImgButton>(); 
        final Map<String, HLayout> progressBarAndButtonPanels = new HashMap<>();
        Label label = new Label();
        label.setHeight(26);
        label.setWidth(300);
  
        uploader.setButtonText("<span class=\"buttonText\">选择文件</span>")
				.setUploadURL("/FileUpload")
				.setButtonTextStyle(".buttonText {display: inline-block; font-size: 14px; color: #fff; background: #fa9632; border-radius: 4px; border: 1px solid #fa9632; width: 100px; height: 24px; line-height: 24px; text-align: center; margin-left: 6px;} .buttonText:hover{color: #fa9632; background: #fff} .fileNameLabelCss{display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 1; overflow: hidden; width: 230px; line-height: 24px;} .cancelButton{cursor: pointer} .thisPageCancleButton{cursor: pointer; width: 80px; height:26px; line-height: 26px; float: right; border-radius: 4px; text-align: center; margin-right: 12px; font-size: 14px; border: 1px solid #D4D2D2; background: #D4D2D2; color: #000;} .thisPageCancleButtonOver,.thisPageCancleButtonDown{cursor: pointer; width: 80px; height:26px; line-height: 26px; float: right; border-radius: 4px; text-align: center; margin-right: 12px; font-size: 14px; border: 1px solid #D4D2D2; background: #fff; color: #000;} .thisPageOkButton{cursor: pointer; width: 80px; height:26px; line-height: 26px; float: right; border-radius: 4px; text-align: center; margin-right: 10px; font-size: 15px; border: 1px solid #fa9632; background: #fa9632; color: #fff;} .thisPageOkButtonOver, .thisPageOkButtonDown{cursor: pointer; width: 80px; height:26px; line-height: 26px; float: right; border-radius: 4px; text-align: center; margin-right: 10px; font-size: 15px; border: 1px solid #fa9632; background: #fff; color: #fa9632;} .thisPageButtonPanel{width: 100%}")
				.setButtonCursor(Uploader.Cursor.HAND)
				.setButtonWidth(108)
				.setButtonHeight(26);
        //设置单选还是可以多选
        if(multiply){
        	uploader.setButtonAction(Uploader.ButtonAction.SELECT_FILES);
        }else{
        	uploader.setButtonAction(Uploader.ButtonAction.SELECT_FILE);
        }
        //上传的文件大小控制
        if(null != fileSize){
        	uploader.setFileSizeLimit(fileSize + " MB");
        }
        //上传文件上限控制，该数值以文件上传成功的个数为准
        if(null != fileNumber){
        	uploader.setFileUploadLimit(fileNumber);
        }
        //文件类型控制
        if(null != fileType){
        	int type = BaseHelpUtils.getIntValue(fileType);
        	switch(type){
        	case 1://图片
        		uploader.setFileTypes("*.jpg;*.gif;*.bmp;*.wbmp;*.png;*.jpeg;*.JPG;*.GIF;*.BMP;*.WBMP;*.PNG;*.JPEG");
                label.setContents("允许上传的文件类型：图片");
        		break;
        	case 2://PDF
        		uploader.setFileTypes("*.pdf;*.PDF");
        		label.setContents("允许上传的文件类型：PDF");
        		break;
        	case 3://文档
        		uploader.setFileTypes("*.doc;*.docx;*.docm;*.dotx;*.dotm;*.xls;*.xlsx;*.xlsm;*.xltx;*.xltm;*.xlsb;*.xlam;*.ppt;*.pptx;*.pptm;*.ppsx;*.potx;*.potm;*.ppam;*.accdb;*.vsd;*.mpp;*.DOC;*.DOCX;*.DOCM;*.DOTX;*.DOTM;*.XLS;*.XLSX;*.XLSM;*.XLTX;*.XLTM;*.XLSB;*.XLAM;*.PPT;*.PPTX;*.PPTM;*.PPSX;*.POTX;*.POTM;*.PPAM;*.ACCDB;*.VSD;*.MPP;");
        		label.setContents("允许上传的文件类型：文档(word,excel,ppt...)");
        		break;
        	case 4://音频
        		uploader.setFileTypes("*.au;*.aiff;*.cd;*.ogg;*.mp3;*.asf;*.wma;*.wav;*.mp3pro;*.rm;*.real;*.ape;*.module;*.midi;*.vqf;*.AU;*.AIFF;*.CD;*.OGG;*.MP3;*.ASF;*.WMA;*.WAV;*.MP3PRO;*.RM;*.REAL;*.APE;*.MODULE;*.MIDI;*.VQF;");
        		label.setContents("允许上传的文件类型：音频文件");
        		break;
        	case 5://视频
        		uploader.setFileTypes("*.avi;*.rmvb;*.rm;*.asf;*.divx;*.mpg;*.mpeg;*.mpe;*.wmv;*.mp4;*.mkv;*.vob;*.mov;*.moov;*.movie;*.flv;*.qt;*.ram;*.AVI;*.RMVB;*.RM;*.ASF;*.DIVX;*.MPG;*.MPEG;*.MPE;*.WMV;*.MP4;*.MKV;*.VOB;*.MOV;*.MOOV;*.MOVIE;*.FLV;*.QT;*.RAM");
        		label.setContents("允许上传的文件类型：视频文件");
        		break;
        	case 6://压缩文件
        		uploader.setFileTypes("*.rar;*.tar;*.zip;*.GZip;*.cab;*.uue;*.arj;*.bz2;*.lzh;*.jar;*.ace;*.iso;*.7z;*.z;*.RAR;*.TAR;*.ZIP;*.GZIP;*.CAB;*.UUE;*.ARJ;*.BZ2;*.LZH;*.JAR;*.ACE;*.ISO;*.7Z;*.Z;");
        		label.setContents("允许上传的文件类型：压缩文件");
        		break;
    		default:
    			label.setContents("允许上传的文件类型：所有文件");
    			break;
        	}
        }else{
        	label.setContents("允许上传的文件类型：所有文件");
        }
        JSONObject params = new JSONObject();
        //上传者
        params.put("uploader", new JSONString(ClientUtil.getUserId()));
        //应用系统ID    ERP：1
        params.put("applicationId", new JSONString("1"));
        //设置上传的通用参数，每个文件上传的时候都会携带这些参数传到后台
        uploader.setPostParams(params);
        
    	uploader.setFileQueuedHandler(new FileQueuedHandler() {
    		public boolean onFileQueued(final FileQueuedEvent fileQueuedEvent) {  
                // Create a Progress Bar for this file  
                final Progressbar progressBar = new Progressbar();  
                progressBar.setTitle(fileQueuedEvent.getFile().getName());  
                progressBar.setHeight(20);  
                progressBar.setWidth(100);  
                progressBars.put(fileQueuedEvent.getFile().getId(), progressBar);  
  
                // Add Cancel Button Image 
                final ImgButton cancelButton = new ImgButton();
                cancelButton.setWidth(20);
				cancelButton.setHeight(20);
				cancelButton.setSrc("/images/fileUpload/close.png");
				cancelButton.setCursor(Cursor.POINTER);
				cancelButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if(fileQueuedEvent.getFile().getPercentUploaded() == 100d) {
							uploader.setFileUploadLimit(fileNumber + (++deleteNum));
						}
						data.remove(fileQueuedEvent.getFile().getId());
						uploader.cancelUpload(fileQueuedEvent.getFile().getId(), false); 
//                    	setProgress(-1.0d);  
			            cancelButtons.remove(fileQueuedEvent.getFile().getId());
			            progressBars.remove(fileQueuedEvent.getFile().getId());
			            progressBarPanel.removeMember(progressBarAndButtonPanels.get(fileQueuedEvent.getFile().getId()));
			            progressBarAndButtonPanels.remove(fileQueuedEvent.getFile().getId());
					}
				});
                cancelButtons.put(fileQueuedEvent.getFile().getId(), cancelButton);  
  
                final Label fileNameLabel = new Label(fileQueuedEvent.getFile().getName());
                fileNameLabel.setHeight(26);
                fileNameLabel.setStyleName("fileNameLabelCss");
                
                final Label fileSizeLabel = new Label(getFileSize(fileQueuedEvent.getFile().getSize()));
                fileSizeLabel.setHeight(26);
                fileSizeLabel.setWidth(36);
                
                // Add the fileNameLayout Bar and Button to the interface  
                HLayout progressBarAndButtonPanel = new HLayout();
                progressBarAndButtonPanel.setWidth(398);
                progressBarAndButtonPanel.setHeight(26);
                progressBarAndButtonPanel.setMembersMargin(4);
                progressBarAndButtonPanel.setTitle(fileQueuedEvent.getFile().getName());
                progressBarAndButtonPanel.addMember(fileNameLabel);
                progressBarAndButtonPanel.addMember(fileSizeLabel);
                progressBarAndButtonPanel.addMember(progressBar);  
                progressBarAndButtonPanel.addMember(cancelButton);  
                progressBarAndButtonPanels.put(fileQueuedEvent.getFile().getId(), progressBarAndButtonPanel);
                progressBarPanel.addMember(progressBarAndButtonPanel);  
                return true;  
            }  
        }); 
    	//进度条显示
    	uploader.setUploadProgressHandler(new UploadProgressHandler() {  
            public boolean onUploadProgress(UploadProgressEvent uploadProgressEvent) {  
                Progressbar progressBar = progressBars.get(uploadProgressEvent.getFile().getId());  
                int percent = BaseHelpUtils.getIntValue((double)uploadProgressEvent.getBytesComplete() / uploadProgressEvent.getBytesTotal() * 100);
                progressBar.setPercentDone(percent > 100 ? 100 : percent);
                return true;  
            }  
        }); 
    	//上传成功后做的操作
    	uploader.setUploadSuccessHandler(new UploadSuccessHandler() {
					
			@Override
			public boolean onUploadSuccess(UploadSuccessEvent uploadSuccessEvent) {
				DSResponse res = new DSResponse();
				try {
					res = DBDataSource.processHttpResponse(uploadSuccessEvent.getServerData());
					if(res.getStatus() >= 0){
						if(null != res.getData() && res.getData().length > 0){
							data.put(uploadSuccessEvent.getFile().getId(), res.getData()[0]);
						}
					}else{
						SC.say(res.getErrors().toString());
					}
				} catch (Exception ex) {
					logger.severe(uploadSuccessEvent.getServerData());
				}
				return true;
			}
		});
    	//上传完成后做的操作
    	uploader.setUploadCompleteHandler(new UploadCompleteHandler() {  
            public boolean onUploadComplete(UploadCompleteEvent uploadCompleteEvent) {  
//                        cancelButtons.get(uploadCompleteEvent.getFile().getId()).removeFromParent();  
                // Call upload to see if any additional files are queued  
            	if(null != fileNumber){
            		if(data.size() < fileNumber.intValue()){
            			uploader.startUpload();  
            		}
            	}
                return true;  
            }  
        });
    	//点击“选择文件”之后，执行的操作
    	uploader.setFileDialogStartHandler(new FileDialogStartHandler() {  
            public boolean onFileDialogStartEvent(FileDialogStartEvent fileDialogStartEvent) {  
//                if (uploader.getStats().getUploadsInProgress() <= 0) {  
//                    // Clear the uploads that have completed, if none are in process  
//                    progressBarPanel.clear();  
//                    progressBars.clear();  
//                    cancelButtons.clear();  
//                }  
                return true;  
            }  
        });
    	
    	uploader.setFileDialogCompleteHandler(new FileDialogCompleteHandler() {  
            public boolean onFileDialogComplete(FileDialogCompleteEvent fileDialogCompleteEvent) {  
                if (uploader.getStats().getUploadsInProgress() <= 0) {  
                    uploader.startUpload();  
                }  
                return true;  
            }  
        });
    	
    	uploader.setFileQueueErrorHandler(new FileQueueErrorHandler() {  
            public boolean onFileQueueError(FileQueueErrorEvent fileQueueErrorEvent) {  
                SC.say("文件【" + fileQueueErrorEvent.getFile().getName() + "】上传失败的原因:[" +  
                        fileQueueErrorEvent.getErrorCode().toString() + "]: " + fileQueueErrorEvent.getMessage()  
                );  
                return true;  
            }  
        });
    	
    	uploader.setUploadErrorHandler(new UploadErrorHandler() {  
            public boolean onUploadError(UploadErrorEvent uploadErrorEvent) {  
                cancelButtons.get(uploadErrorEvent.getFile().getId()).removeFromParent();  
                SC.say("文件【" + uploadErrorEvent.getFile().getName() + "】上传失败的原因:[" +  
                        uploadErrorEvent.getErrorCode().toString() + "]: " + uploadErrorEvent.getMessage()  
                );  
                return true;  
            }  
        });  
  
    	HorizontalPanel uploaderPanel = new HorizontalPanel();
    	uploaderPanel.setHeight("26px");
        uploaderPanel.add(uploader);
        uploaderPanel.setSpacing(5);
        uploaderPanel.add(label);
        addMember(uploaderPanel);
        addMember(progressBarPanel);
        
        
        HLayout buttonPanel = new HLayout();
        buttonPanel.setHeight(26);
        buttonPanel.setLayoutLeftMargin(210);
        buttonPanel.setStyleName("thisPageButtonPanel");
        buttonPanel.setLayoutAlign(Alignment.RIGHT);
        buttonPanel.setMembersMargin(10);
        IButton okButton = new IButton("确定");
        okButton.setBaseStyle("thisPageOkButton");
        okButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				callBack.execute(data);;
				if (null == getParentWindow()) {
					return;
				}
				getParentWindow().destroy();
			}
		});
        
        
        IButton cancleButton = new IButton("取消");
        cancleButton.setBaseStyle("thisPageCancleButton");
        cancleButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				cancelUpload();
				if (null == getParentWindow()) {
					return;
				}
				getParentWindow().destroy();
			}
		});
        
        buttonPanel.addMember(okButton);
        buttonPanel.addMember(cancleButton);
        addMember(buttonPanel);
    }  
  
    public Record[] getValueAsRecord(){
    	int size = data.size();
    	Record[] records = new Record[size];
    	if(size == 0){
    		return records;
    	}else{
    		int i = 0;
    		for(String key : data.keySet()){
    			records[i++] = data.get(key);
    		}
    		return records;
    	}
    }

    public String getFileSize(long size){
    	if(size / 1024 == 0){
    		return size + "B";
    	}else if(size / (1024 * 1024) == 0){
    		return size / 1024 + "K";
    	}else if(size / (1024 * 1024 * 1024) == 0){
    		return size / (1024 * 1024) + "M";
    	}else if(size / (1024 * 1024 * 1024 * 1024) == 0){
    		return size / (1024 * 1024 * 1024) + "G";
    	}else {
    		return size / (1024 * 1024 * 1024 * 1024) + "T";
    	}
    }
    
	public Window getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(Window parentWindow) {
		this.parentWindow = parentWindow;
	}
  
	public final void cancelUpload(){
		uploader.cancelUpload(false);
	}
}  