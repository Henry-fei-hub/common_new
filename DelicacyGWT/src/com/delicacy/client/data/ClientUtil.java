package com.delicacy.client.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.ui.ShowPanelUtils;
import com.delicacy.client.ui.StandardShowWindow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Cookies;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.PageOrientation;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.Browser;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.drawing.DrawPane;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.RichTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ClientUtil {

    private static final Logger __logger = Logger.getLogger("");

    public static void showMessage(String message) {
        com.google.gwt.user.client.Window.alert(message);
    }

    // 常量
    public static String HOST_URL = GWT.getHostPageBaseURL();
    public static long DAYTIME = 86400000L;
    public static String EMPLOYEE_COOKIE = "employee";
    public static String PRIVILEGE_COOKIE = "FUNCTIONLIST";
    public static String AGENCY_COOKIE = "agency";
    public static String OPRERATORID_COOKIE = "OPRERATORID";
    public static String OPRERATORNO_COOKIE = "OPRERATORNNO";
    public static String OPRERATORNAME_COOKIE = "OPRERATORNAME";
    public static String OPRERATOR_DEPARTMENTID_COOKIE = "OPRERATORDEPARTMENTID";
    public static String OPRERATOR_ROLETYPEID_COOKIE = "OPRERATORROLETYPEID";
    public static String OPRERATOR_GRADEID_COOKIE = "OPRERATORGRADEID";
    public static String OPRERATOR_GRADENAME_COOKIE = "OPRERATORGRADENAME";
    public static String LAST_USED_TIME = "LAST_USED_TIME";
    public static String OPRERATOR_PLATEID_COOKIE = "OPRERATORPLATEID";
    public static String DUTY_ID_COOKIE = "dutyId";
    public static String DUTY_NAME_COOKIE = "dutyName";
    public static String COMPANYID_COOKIE = "COMPANYID";//归属公司ID
    public static String COMPANYNO_COOKIE = "COMPANYNO";//companies表主键
    public static String HEAD_IMG = "HEADIMG";
    public static String ROLE_DEPARTMENTID_COOKIE = "role_departmentId";
    public static String PLATE_RECORDS_TYPE = "plate_records_type";//模板类型

    public static void clearCookies() {
        if (Cookies.isCookieEnabled()) {
            Cookies.removeCookie(PRIVILEGE_COOKIE);
            Cookies.removeCookie(OPRERATORID_COOKIE);
            Cookies.removeCookie(OPRERATORNO_COOKIE);
            Cookies.removeCookie(OPRERATORNAME_COOKIE);
            Cookies.removeCookie(OPRERATOR_DEPARTMENTID_COOKIE);
            Cookies.removeCookie(OPRERATOR_ROLETYPEID_COOKIE);
            Cookies.removeCookie(OPRERATOR_PLATEID_COOKIE);
            Cookies.removeCookie(DUTY_ID_COOKIE);
            Cookies.removeCookie(DUTY_NAME_COOKIE);
            Cookies.removeCookie(OPRERATOR_GRADEID_COOKIE);
            Cookies.removeCookie(OPRERATOR_GRADENAME_COOKIE);
            Cookies.removeCookie(COMPANYID_COOKIE);
            Cookies.removeCookie(COMPANYNO_COOKIE);
            Cookies.removeCookie(PLATE_RECORDS_TYPE);
        }
        Storage sta = Storage.getLocalStorageIfSupported();
        if (sta != null) {
            sta.removeItem(PRIVILEGE_COOKIE);
            sta.removeItem(AGENCY_COOKIE);
        }
    }

    public static String getCurrentRole() {
        if (null == Cookies.getCookie(OPRERATOR_ROLETYPEID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            return sta.getItem(OPRERATOR_ROLETYPEID_COOKIE);
        } else {
            return Cookies.getCookie(OPRERATOR_ROLETYPEID_COOKIE);
        }
    }



    public static boolean hasPrivilege(String privId) {
        return getPrivilegeString().contains(privId);
    }

    public static String[] getPrivilegeList() {
        return getPrivilegeString().split(",");
    }

    public static String getProcessTypeName(Record r) {
        String sprocessType = r.getAttribute("processType");
        return KeyValueManager.getValue("system_process_types", sprocessType);
    }

    public static Set<String> getDefaultPrivileges() {
        Set<String> priSet = new HashSet<>();
        String functionStr = getPrivilegeString();
        if (!isNullOrEmpty(functionStr)) {
            String[] functionArra = functionStr.split(",");
            priSet.addAll(Arrays.asList(functionArra));
        }
        return priSet;
    }

    public static int getPlateId() {
        if (null == Cookies.getCookie(OPRERATOR_PLATEID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(OPRERATOR_PLATEID_COOKIE)) {
                return -1;
            }
            return checkAndGetIntValue(sta.getItem(OPRERATOR_PLATEID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(OPRERATOR_PLATEID_COOKIE));
        }
    }
    public static String getOpreratorGradeidCookie() {
        return OPRERATOR_GRADEID_COOKIE;
    }

    public static void setOpreratorGradeidCookie(String opreratorGradeidCookie) {
        OPRERATOR_GRADEID_COOKIE = opreratorGradeidCookie;
    }

    public static int getGradeId() {
        if (null == Cookies.getCookie(OPRERATOR_GRADEID_COOKIE)) {

            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(OPRERATOR_GRADEID_COOKIE)) {
                return -1;
            }
            return checkAndGetIntValue(sta.getItem(OPRERATOR_GRADEID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(OPRERATOR_GRADEID_COOKIE));
        }
    }

    public static String getGradeName() {
        if (null == Cookies.getCookie(OPRERATOR_GRADENAME_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            return sta.getItem(OPRERATOR_GRADENAME_COOKIE);
        } else {
            return Cookies.getCookie(OPRERATOR_GRADENAME_COOKIE);
        }
    }
    public static int getRoleId() {
        if (null == Cookies.getCookie(OPRERATOR_ROLETYPEID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(OPRERATOR_ROLETYPEID_COOKIE)) {
                return -1;
            }
            return checkAndGetIntValue(sta.getItem(OPRERATOR_ROLETYPEID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(OPRERATOR_ROLETYPEID_COOKIE));
        }
    }

    public static int getDutyId() {
        if (null == Cookies.getCookie(DUTY_ID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(DUTY_ID_COOKIE)) {
                return -1;
            }
            return checkAndGetIntValue(sta.getItem(DUTY_ID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(DUTY_ID_COOKIE));
        }
    }

    public static String getDutyName() {
        if (null == Cookies.getCookie(DUTY_NAME_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            return sta.getItem(DUTY_NAME_COOKIE);
        } else {
            return Cookies.getCookie(DUTY_NAME_COOKIE);
        }
    }

    public static int getEmployeeId() {
        if (null == Cookies.getCookie(OPRERATORID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(OPRERATORID_COOKIE)) {
                return -1;
            }
            Date expires = new Date(System.currentTimeMillis() + DAYTIME * 2);//设置两天
            Cookies.setCookie(OPRERATORID_COOKIE, sta.getItem(OPRERATORID_COOKIE), expires);
            return checkAndGetIntValue(sta.getItem(OPRERATORID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(OPRERATORID_COOKIE));
        }

    }

    public static int getCompanyId() {
        if (null == Cookies.getCookie(COMPANYID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(COMPANYID_COOKIE)) {
                return -1;
            }
            return checkAndGetIntValue(sta.getItem(COMPANYID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(COMPANYID_COOKIE));
        }
    }

    public static int getCompanyNo() {
        return checkAndGetIntValue(Cookies.getCookie(COMPANYNO_COOKIE));
    }

    public static int getDepartmentId() {
        if (null == Cookies.getCookie(OPRERATOR_DEPARTMENTID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            if (null == sta.getItem(OPRERATOR_DEPARTMENTID_COOKIE)) {
                return -1;
            }
            return checkAndGetIntValue(sta.getItem(OPRERATOR_DEPARTMENTID_COOKIE));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(OPRERATOR_DEPARTMENTID_COOKIE));
        }
    }

    public static void displayErrorMessage(DSResponse dsResponse) {
        SC.say("提示 " + dsResponse.getErrors().get("errorMsg"));
    }

    public static Criteria mapToCriteria(Map m) {
        Criteria c = new Criteria();
        if (m == null || m.isEmpty())
            return c;
        for (Object o : m.keySet()) {
            c.addCriteria(o.toString(), m.get(o));
        }
        return c;
    }

    public static Map criteriaToMap(Criteria c) {
        return c.getValues();
    }

    public static void showWorkFlow(Record r) {
        DrawPane drawPane = ShowPanelUtils.generateDetailProcessPane(r);
        StandardShowWindow ssw = new StandardShowWindow();
        ssw.setTitle("流程进度");
        ssw.setWidth(1000);
        ssw.setHeight(600);
        ssw.setPanel(drawPane);
        ssw.centerInPage();
        ssw.show();
    }

    public static double checkAndGetDoubleValue(String val) {
        if (isNullOrEmpty(val)) {
            return 0.0;
        }
        String v = val.trim();
        try {
            return Double.parseDouble(v);
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public static double checkAndGetDoubleValue(Object val) {
        if (isNullOrEmpty(val)) {
            return 0;
        }
        if (val instanceof Number) {
            return ((Number) val).doubleValue();
        }
        String v = val.toString().trim();
        try {
            return Double.parseDouble(v);
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public static String getStringFromMap(Map m, String name) {
        Object o = m.get(name);
        if (o == null)
            return null;
        return o.toString();
    }

    public static String getStringFromMapNotNull(Map m, String name) {
        Object o = m.get(name);
        if (o == null)
            return "";
        return o.toString();
    }

    public static double getDoubleFromMap(Map m, String name) {
        Object o = m.get(name);
        return checkAndGetDoubleValue(o);
    }

    public static int getIntFromMap(Map m, String name) {
        Object o = m.get(name);
        return checkAndGetIntValue(o);
    }

    public static void addReplacement(Map m, String o, String n) {
        List<Map> keyvalues = new ArrayList<>();
        Map<String, String> kv = new HashMap<>();
        kv.put("key", o);
        kv.put("value", n);
        keyvalues.add(kv);
        m.put("keyValues", keyvalues);
    }

    public static int checkAndGetIntValue(Object val) {
        if (isNullOrEmpty(val)) {
            return 0;
        }
        if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        String v = val.toString().trim();
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long checkAndGetLongValue(Object val) {
        if (isNullOrEmpty(val)) {
            return 0;
        }
        if (val instanceof Number) {
            return ((Number) val).longValue();
        }
        String v = val.toString().trim();
        try {
            return Long.parseLong(v);
        } catch (Exception ex) {
            return 0L;
        }
    }

    public static boolean isNullOrZero(Object val) {
        if (val == null) {
            return true;
        }
        if (val instanceof Number && ((Number) val).intValue() == 0) {
            return true;
        }
        if (val instanceof String) {
            if (((String) val).trim().length() == 0) {
                return true;
            }
            Double v = Double.parseDouble((String) val);
            if (v.intValue() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullOrEmpty(Object val) {
        if (val == null) {
            return true;
        }
        if (val instanceof Collection) {
            if (((Collection) val).isEmpty()) {
                return true;
            }
        }
        if (val instanceof String) {
            if (((String) val).trim().length() == 0) {
                return true;
            }
            if (((String) val).trim().equals("null")) {
                return true;
            }
        }
        return false;
    }

    public static String getPrivilegeString() {
        String privilege = null;
        Storage sta = Storage.getLocalStorageIfSupported();
        if (sta != null) {
            if (sta.getItem(PRIVILEGE_COOKIE) != null) {
                privilege = sta.getItem(PRIVILEGE_COOKIE);
            }
        } else if (Cookies.getCookie(PRIVILEGE_COOKIE) != null) {
            privilege = Cookies.getCookie(PRIVILEGE_COOKIE);
        }
        return privilege == null ? "" : privilege;
    }

    public static String[] getAgencyList() {
        String agency = "";
        if (Cookies.getCookie(AGENCY_COOKIE) != null) {
            agency = Cookies.getCookie(AGENCY_COOKIE);
        }
        return (agency.split(","));
    }

    public static String getUserId() {
        if (null == Cookies.getCookie(OPRERATORID_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            return sta.getItem(OPRERATORID_COOKIE);
        } else {
            return Cookies.getCookie(OPRERATORID_COOKIE);
        }
    }

    public static String getUserName() {
        if (null == Cookies.getCookie(OPRERATORNAME_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            return sta.getItem(OPRERATORNAME_COOKIE);
        } else {
            return Cookies.getCookie(OPRERATORNAME_COOKIE);
        }
    }

    public static String getUserNo() {
        if (null == Cookies.getCookie(OPRERATORNO_COOKIE)) {
            Storage sta = Storage.getLocalStorageIfSupported();
            return sta.getItem(OPRERATORNO_COOKIE);
        } else {
            return Cookies.getCookie(OPRERATORNO_COOKIE);
        }
    }

    public static boolean isJPGAndPDF(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        int idx = fileName.lastIndexOf('.');
        if (idx == -1) {
            return false;
        }
        String ext = fileName.substring(idx + 1);
        return ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("pdf");
    }

    public static boolean isImageFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        int idx = fileName.lastIndexOf('.');
        if (idx == -1) {
            return false;
        }
        String ext = fileName.substring(idx + 1);
        return ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg") || ext.equals("bmp") || ext.equals("gif");
    }

    public static boolean isExcelFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        int idx = fileName.lastIndexOf(".");
        if (idx == -1) {
            return false;
        }
        String ext = fileName.substring(idx + 1);
        return ext.equals("xls") || ext.equals("xlsx") || ext.equals("xlsm") || ext.equals("xltx") || ext.equals("xltm")
                || ext.equals("xlsb") || ext.equals("xlam") || ext.equals("doc") || ext.equals("arj")
                || ext.equals("rar") || ext.equals("zip") || ext.equals("pdf");

    }

    public static boolean isExcelFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        int idx = fileName.lastIndexOf(".");
        if (idx == -1) {
            return false;
        }
        String ext = fileName.substring(idx + 1);
        return ext.equals("xls") || ext.equals("xlsx");

    }

    public static boolean isPdfFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        int idx = fileName.lastIndexOf(".");
        if (idx == -1) {
            return false;
        }
        String ext = fileName.substring(idx + 1);
        return ext.equals("pdf");

    }

    public static boolean isExistPrivilege(String privilege) {
        return getPrivilegeString().contains(privilege);
    }

    public static boolean isExistChangedFile(String resultFiles, String changedFile) {
        return resultFiles.contains(changedFile);
    }

    public static boolean existAgency(String agencyString) {
        if (Cookies.getCookie(AGENCY_COOKIE) != null) {
            if (Cookies.getCookie(AGENCY_COOKIE).contains(agencyString)) {
                return true;
            }
        }
        return false;
    }

    public static void searchDateFormat(Date val, FormItem item) {
        if (val == null) {
            return;
        }
        val.setHours(0);
        val.setMinutes(0);
        val.setSeconds(0);
        item.setValue(val);
    }

    public static void setNoShowSummaryFields(ListGrid lg, String strFields) {
        String[] nssfs = strFields.split(",");
        for (String nssf : nssfs) {
            ListGridField field = lg.getField(nssf);
            if (field == null) {
                continue;
            }
            field.setShowGridSummary(false);
        }
    }

    /**
     * 隱藏字段
     *
     * @param resultGrid
     * @param hiddenFields
     */
    public static void setHiddenFields(ListGrid resultGrid, String hiddenFields) {
        ListGridField[] fields = resultGrid.getAllFields();
        String[] fieldsArr = hiddenFields.split(",");
        Set<String> fieldSet = new HashSet<>();
        for (String fi : fieldsArr) {
            if (!BaseHelpUtils.isNullOrEmpty(fi)) {
                fieldSet.add(fi.trim());
            }
        }
        for (ListGridField field : fields) {
            String fieldName = field.getName();
            //排除序列的列和复选框的列
            if (!fieldName.equals("_checkboxField") && !fieldName.equals("$74y")) {
                fieldName = fieldName + "Field";
                if (!BaseHelpUtils.isNullOrEmpty(hiddenFields) && fieldSet.contains(fieldName)) {
                    field.setHidden(true);
                }

            }
        }
        resultGrid.setFields(fields);
        resultGrid.redraw();
    }

    /**
     * @param @param resultGrid
     * @param @param showFields
     * @return void
     * @throws
     * @Title: setShowOrHiddenFields
     * @Description: 显示或隐藏字段
     */
    public static void setShowOrHiddenFields(ListGrid resultGrid, String showFields) {
        if (!BaseHelpUtils.isNullOrEmpty(showFields)) {
            ListGridField[] fields = resultGrid.getAllFields();
            String[] fieldsArr = showFields.split(",");
            Set<String> fieldSet = new HashSet<>();
            for (String fi : fieldsArr) {
                if (!BaseHelpUtils.isNullOrEmpty(fi)) {
                    fieldSet.add(fi.trim());
                }
            }
            for (ListGridField field : fields) {
                String fieldName = field.getName();
                //排除序列的列和复选框的列
                if (!fieldName.equals("_checkboxField") && !fieldName.equals("$74y")) {
                    fieldName = fieldName + "Field";
                    if (fieldSet.contains(fieldName)) {
                        field.setHidden(false);
                    } else {
                        field.setHidden(true);
                    }
                }
            }
            resultGrid.setFields(fields);
            resultGrid.redraw();
        }
    }

    /**
     * 设置求和的字段
     *
     * @param resultGrid
     * @param cancelSummaryFields
     */
    public static void setCancelGridSummaryFields(ListGrid resultGrid, String cancelSummaryFields) {
        if (!BaseHelpUtils.isNullOrEmpty(cancelSummaryFields)) {
            ListGridField[] fields = resultGrid.getAllFields();
            String[] fieldsArr = cancelSummaryFields.split(",");
            Set<String> fieldSet = new HashSet<>();
            for (String fi : fieldsArr) {
                if (!BaseHelpUtils.isNullOrEmpty(fi)) {
                    fieldSet.add(fi.trim());
                }
            }
            for (ListGridField field : fields) {
                String fieldName = field.getName() + "Field";
                if (!BaseHelpUtils.isNullOrEmpty(cancelSummaryFields) && fieldSet.contains(fieldName)) {
                    field.setShowGridSummary(false);
                } else {
                    field.setShowGridSummary(true);
                }
            }
            resultGrid.setFields(fields);
            resultGrid.redraw();
        }
    }

    /**
     * 格式化时间的显示格式； 默认为yyyy-MM-dd HH:mm:ss
     * type = 1 ：  显示yyyy-MM-dd
     *
     * @param grid
     * @param formatFields
     * @param type
     */
    public static void formatDateTime(ListGrid grid, String formatFields, int type) {
        if (null != grid && !BaseHelpUtils.isNullOrEmpty(formatFields)) {
            ListGridField[] fields = grid.getAllFields();
            if (null != fields) {
                String format = null;
                switch (type) {
                    case 1:
                        format = "yyyy-MM-dd";
                        break;
                    default:
                        format = "yyyy-MM-dd HH:mm:ss";
                        break;
                }
                for (ListGridField field : fields) {
                    String fieldName = field.getName() + "Field";
                    if (formatFields.contains(fieldName)) {
                        field.setFormat(format);
                    }
                }
                grid.setFields(fields);
                grid.redraw();
            }
        }
    }

    public static Map putMapAll(Map dest, Map src, Set names) {
        Set knames = null;
        if (!(names == null || names.isEmpty())) {
            knames = names;
        }
        Map notused = new HashMap();
        for (Object k : src.keySet()) {
            // 如果该主健不在名字列表中，并且当前数据中无此数据，则放入未使用数据中
            if (knames != null && !knames.contains(k)) {
                if (dest.get(k) == null) {
                    notused.put(k, src.get(k));
                }
                continue;
            }
            // 如果名字集合为空，则看数据集合中无此数据，则加入
            if (names == null) {
                if (dest.get(k) == null) {
                    dest.put(k, src.get(k));
                }
            } else {// 如果在名字列表中则无条件加入该数据
                dest.put(k, src.get(k));
            }
        }
        return notused;
    }

    public static void DynamicFormProcessAccordingToDevice(DynamicForm sf) {
        sf.setOverflow(Overflow.AUTO);
        sf.setCellPadding(5);
        FormItem[] items = sf.getFields();
        for (FormItem fi : items) {
            fi.setTitleOrientation(TitleOrientation.TOP);
            fi.setTitleAlign(Alignment.LEFT);
            if (!(fi instanceof RichTextItem || fi instanceof TextAreaItem)) {
                fi.setColSpan(2);
            }
        }
        if (Browser.getIsDesktop()) {
            return;
        }
        if (Page.getOrientation() == PageOrientation.PORTRAIT) {
            sf.setNumCols(2);
            sf.setColWidths(10, "*");
            for (FormItem fi : items) {
                fi.setColSpan(2);
            }
        } else {
            sf.setNumCols(4);
            sf.setColWidths(10, "*", 10, "*");
        }
    }

    public static void searchFormProcessAccordingToDevice(SearchForm sf) {
        sf.setOverflow(Overflow.AUTO);
        FormItem[] items = sf.getFields();
        for (FormItem fi : items) {
            fi.setTitleOrientation(TitleOrientation.TOP);
            fi.setTitleAlign(Alignment.LEFT);
            fi.setColSpan(2);
        }
        if (Browser.getIsDesktop()) {
            sf.setHeight(120);
            return;
        } else {
            sf.setHeight(84);
        }

        if (Page.getOrientation() == PageOrientation.PORTRAIT) {
            sf.setNumCols(2);
            sf.setColWidths(10, "*");
        } else {
            sf.setNumCols(4);
            sf.setColWidths(10, "*", 10, "*");
        }

    }

    public static void setApplicationID(int id) {
        Storage sta = Storage.getLocalStorageIfSupported();
        if (sta != null) {
            sta.setItem(HttpCookie.APPLICATIONID, String.valueOf(id));
        }
        Cookies.setCookie(HttpCookie.APPLICATIONID, String.valueOf(id));
    }

    public static int getApplicationID() {
        Storage sta = Storage.getLocalStorageIfSupported();
        if (sta != null) {
            return checkAndGetIntValue(sta.getItem(HttpCookie.APPLICATIONID));
        } else {
            return checkAndGetIntValue(Cookies.getCookie(HttpCookie.APPLICATIONID));
        }
    }

    /**
     * 获取部门列表
     *
     * @param params
     */
    public static void departmentParameterProcess(Map params) {
        // 部门列表
        String departmentId = String.valueOf(params.get("departmentId"));
        String parentId = String.valueOf(params.get("parentId"));
        if (departmentId != null) {
            int idx = departmentId.lastIndexOf("/");
            if (idx != -1) {
                departmentId = departmentId.substring(idx + 1);
                params.put("departmentId", Integer.parseInt(departmentId));
            }
        }

        if (parentId != null) {
            int idx = parentId.lastIndexOf("/");
            if (idx != -1) {
                parentId = parentId.substring(idx + 1);
                params.put("parentId", Integer.parseInt(parentId));
            }
        }

        // 职务列表
        String dutyId = String.valueOf(params.get("dutyId"));
        String parentDutyId = String.valueOf(params.get("parentDutyId"));
        if (dutyId != null) {
            int idx = dutyId.lastIndexOf("/");
            if (idx != -1) {
                dutyId = dutyId.substring(idx + 1);
                params.put("dutyId", Integer.parseInt(dutyId));
            }
        }

        if (parentDutyId != null) {
            int idx = parentDutyId.lastIndexOf("/");
            if (idx != -1) {
                parentDutyId = parentDutyId.substring(idx + 1);
                params.put("parentDutyId", Integer.parseInt(parentDutyId));
            }
        }

        // 项目面积
        String projectAreaId = String.valueOf(params.get("projectAreaId"));
        if (projectAreaId != null) {
            int idx = projectAreaId.lastIndexOf("/");
            if (idx != -1) {
                projectAreaId = projectAreaId.substring(idx + 1);
                params.put("projectAreaId", Integer.parseInt(projectAreaId));
            }
        }

        // 项目类型
        String projectTypeId = String.valueOf(params.get("projectTypeId"));
        if (projectTypeId != null) {
            int idx = projectTypeId.lastIndexOf("/");
            if (idx != -1) {
                projectTypeId = projectTypeId.substring(idx + 1);
                params.put("projectTypeId", Integer.parseInt(projectTypeId));
            }
        }

        // 功能
        String applicationId = String.valueOf(params.get("applicationId"));
        if (applicationId != null) {
            int idx = applicationId.lastIndexOf("/");
            if (idx != -1) {
                applicationId = applicationId.substring(idx + 1);
                params.put("applicationId", Integer.parseInt(applicationId));
            }
        }
    }

    /**
     * 检索当前登录人员是否有当前菜单或者按钮的权限
     *
     * @param funcionCode
     * @return
     */
    public static Boolean checkIsHavePermission(String funcionCode) {
    	if(Objects.equals(ClientUtil.getUserNo(), "admin")) {
    		return true;
    	}
        boolean isHavePermission = false;
        String privilege = getPrivilegeString();
        if (privilege != null && privilege.length() > 0) {
            String[] privilegeArr = privilege.split(",");
            for (String code : privilegeArr) {
                if (funcionCode.equals(code)) {
                    isHavePermission = true;
                    break;
                }
            }
        }
        return isHavePermission;
    }


    /**
     * 根据当前登录者的角色获取其角色相关联的部门Id
     *
     * @return
     */
    public static String getRoleDepartmentId() {
        String roleDepartmentId = null;
        Storage sta = Storage.getLocalStorageIfSupported();
        if (sta != null) {
            if (sta.getItem(ROLE_DEPARTMENTID_COOKIE) != null) {
                roleDepartmentId = sta.getItem(ROLE_DEPARTMENTID_COOKIE);
            }
        } else if (Cookies.getCookie(ROLE_DEPARTMENTID_COOKIE) != null) {
            roleDepartmentId = Cookies.getCookie(ROLE_DEPARTMENTID_COOKIE);
        }
        return roleDepartmentId == null ? "" : roleDepartmentId;
    }

}
