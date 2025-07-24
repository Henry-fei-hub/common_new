package com.delicacy.client.panel;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;

public class CommonUploadWindow extends Window {
	private int width = 438;
	private int height = 500;
	
    /**
     * 
     * @param title			上传页面的标题
     * @param multiply		是否支持选择多文件， true:支持; false:不支持;
     * @param fileSize		限制上传的文件大小，为空则不限制，单位：MB;单个文件最大不能超过5120MB; 多文件总大小不能超过51200MB;
     * @param fileType		限制上传的文件类型，为空则不限制。1:图片; 2:PDF; 3:文档; 4:音频; 5:视频; 6:压缩文件
     * @param fileNumber	限制上传的文件个数，为空则不限制。该数值以文件上传成功的个数为准
     * @param uploadCallBack点击确定按钮时，执行的方法
     */
    public  CommonUploadWindow(String title, boolean multiply, Integer fileSize, Integer fileType, Integer fileNumber, final UploadCallBack uploadCallBack) {
        setWidth(width);
        setHeight(height);
        setTitle(title);
        initComponent(multiply, fileSize, fileType, fileNumber, uploadCallBack);
    }

    protected void initComponent(boolean multiply, Integer fileSize, Integer fileType, Integer fileNumber, final UploadCallBack uploadCallBack) {
        setHiliteBodyColor("#C3D9FF");
        setShowMinimizeButton(true);
        setShowMaximizeButton(false);
        setShowCloseButton(true);
        setCanDragReposition(true);
        setCanDragResize(false);
        setShowShadow(true);
        setIsModal(true);
        setShowModalMask(true);
        setPadding(10);
        setMembersMargin(10);
        centerInPage();
        
        final CommonUploadPanel uploader = new CommonUploadPanel(multiply, fileSize, fileType, fileNumber);
        uploader.initComponent(uploadCallBack);
        uploader.setParentWindow(this);
        addMember(uploader);
        show();

        this.addCloseClickHandler(new CloseClickHandler() {
            @Override
            public void onCloseClick(CloseClickEvent event) {
            	uploader.cancelUpload();
            	destroy();
            }
        });
        
    }
}
