package com.delicacy.client.data;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Cookies;

/**
 *
 * @author guanxgun
 */
public final class HttpCookie {

    public final static String JSESSION_NAME = "JSESSIONID";
    public final static String OPRERATORDEPARTMENTID = "OPRERATORDEPARTMENTID";
    public final static String OPRERATORID = "OPRERATORID";
    public final static String OPRERATORNO = "OPRERATORNNO";
    public final static String OPRERATORNAME = "OPRERATORNAME";
    public final static String OPRERATORPLATEID = "OPRERATORPLATEID";
    public final static String OPRERATORROLETYPEID = "OPRERATORROLETYPEID";
    public final static String APPLICATIONID = "APPLICATIONID";
    public final static String HEADIMG = "HEADIMG";
    public final static String LOGINID = "loginId";
    public final static String SIGNNAME = "signName";
    public final static String USERTYPE = "userType";

    private int departmentId = 0;
    private int operatorId = 0;
    private String operatorName;
    private String operatorNo;
    private int plateId = 0;
    private int roleTypeId = 0;
    private String sessionId;
    private int loginId = 0;
    private String signName;
    private String headImg;
    private int userType = 0;
    private int applicationId = 0;

    public HttpCookie() {
        for (String n : Cookies.getCookieNames()) {
            switch (n.trim()) {
                case JSESSION_NAME:
                    setSessionId(Cookies.getCookie(n));
                    break;
                case OPRERATORDEPARTMENTID:
                    setDepartmentId(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case OPRERATORID:
                    setOperatorId(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case OPRERATORNO:
                    setOperatorNo(Cookies.getCookie(n));
                    break;
                case OPRERATORNAME:
                    setOperatorName(URL.decode(Cookies.getCookie(n)));
                    break;
                case OPRERATORPLATEID:
                    setPlateId(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case APPLICATIONID:
                    setApplicationId(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case OPRERATORROLETYPEID:
                    setRoleTypeId(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case LOGINID:
                    setLoginId(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case USERTYPE:
                    setUserType(ClientUtil.checkAndGetIntValue(Cookies.getCookie(n)));
                    break;
                case SIGNNAME:
                    setSignName(URL.decode(Cookies.getCookie(n)));
                    break;
                case HEADIMG:
                	setHeadImg(URL.decode(Cookies.getCookie(n)));
                	break;
            }
        }
    }

    public void setToCookies() {
        Cookies.setCookie(APPLICATIONID, String.valueOf(getApplicationId()));
        if (getDepartmentId() != 0) {
            Cookies.setCookie(OPRERATORDEPARTMENTID, String.valueOf(getDepartmentId()));
        }
        if (getOperatorId() != 0) {
            Cookies.setCookie(OPRERATORID, String.valueOf(getOperatorId()));
        }
        if (getPlateId() != 0) {
            Cookies.setCookie(OPRERATORPLATEID, String.valueOf(getPlateId()));
        }
        if (getLoginId() != 0) {
            Cookies.setCookie(LOGINID, String.valueOf(getLoginId()));
        }
        if (getUserType() != 0) {
            Cookies.setCookie(USERTYPE, String.valueOf(getUserType()));
        }
        if (getOperatorName() != null) {
            Cookies.setCookie(OPRERATORNAME, String.valueOf(getOperatorName()));
        }
        if (getOperatorNo() != null) {
            Cookies.setCookie(OPRERATORNO, String.valueOf(getOperatorNo()));
        }
        if (getSessionId() != null) {
            Cookies.setCookie(JSESSION_NAME, String.valueOf(getSessionId()));
        }
        if (getSignName() != null) {
            Cookies.setCookie(SIGNNAME, String.valueOf(getSignName()));
        }
        if (getHeadImg() != null) {
            Cookies.setCookie(HEADIMG, String.valueOf(getHeadImg()));
        }
    }

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the operatorId
     */
    public int getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId the operatorId to set
     */
    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName the operatorName to set
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return the plateId
     */
    public int getPlateId() {
        return plateId;
    }

    /**
     * @param plateId the plateId to set
     */
    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    /**
     * @return the roleTypeId
     */
    public int getRoleTypeId() {
        return roleTypeId;
    }

    /**
     * @param roleTypeId the roleTypeId to set
     */
    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the operatorNo
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    /**
     * @param operatorNo the operatorNo to set
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    /**
     * @return the loginId
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the signName
     */
    public String getSignName() {
        return signName;
    }

    /**
     * @param signName the signName to set
     */
    public void setSignName(String signName) {
        this.signName = signName;
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the applicationId
     */
    public int getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
    
}
