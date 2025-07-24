package com.delicacy.client;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickHandler;

public class TransferImgBt extends ImgButton{
	private static String IMG_TYPE = "png";
	 
	public static TransferImg LEFT = new TransferImg("[SKIN]/TransferIcons/left." + IMG_TYPE);
    public static TransferImg LEFT_ALL = new TransferImg("[SKIN]/TransferIcons/left_all." + IMG_TYPE);
    public static TransferImg RIGHT = new TransferImg("[SKIN]/TransferIcons/right." + IMG_TYPE);
    public static TransferImg RIGHT_ALL = new TransferImg("[SKIN]/TransferIcons/right_all." + IMG_TYPE);
    public static TransferImg UP = new TransferImg("[SKIN]/TransferIcons/up." + IMG_TYPE);
    public static TransferImg UP_FIRST = new TransferImg("[SKIN]/TransferIcons/up_first." + IMG_TYPE);
    public static TransferImg DOWN = new TransferImg("[SKIN]/TransferIcons/down." + IMG_TYPE);
    public static TransferImg DOWN_LAST = new TransferImg("[SKIN]/TransferIcons/down_last." + IMG_TYPE);
    public static TransferImg DELETE = new TransferImg("[SKIN]/TransferIcons/delete." + IMG_TYPE);

    /**
     * Create a new HeaderControl with the specific icon.
     *
     * @param transferImg the icon
     */
    public TransferImgBt(TransferImg transferImg) {
        setSrc(transferImg.url);
        setWidth(24);
        setHeight(22);
        setLayoutAlign(Alignment.CENTER);
        setShowRollOver(true);
        setShowDownIcon(false);
        setShowDown(false);
    }

    /**
     * Create a new HeaderControl with the specific icon.
     *
     * @param transferImg  the icon
     * @param clickHandler the header control click handler
     */
    public TransferImgBt(TransferImg transferImg, ClickHandler clickHandler) {
        this(transferImg);
        addClickHandler(clickHandler);
    }

    public static class TransferImg {
        private String url;

        public TransferImg(String url) {
            this.url = url;
        }
    }
    
	public static String getIMG_TYPE() {
		return IMG_TYPE;
	}

	public static void setIMG_TYPE(String iMG_TYPE) {
		IMG_TYPE = iMG_TYPE;
	}
}
