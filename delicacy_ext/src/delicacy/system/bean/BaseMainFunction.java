package delicacy.system.bean;

import java.util.Map;
import java.util.Objects;
import delicacy.common.GenericBase;
import delicacy.common.BaseFactory;

public class BaseMainFunction extends GenericBase implements BaseFactory<BaseMainFunction>, Comparable<BaseMainFunction> {

    public static BaseMainFunction newInstance() {
        return new BaseMainFunction();
    }

    @Override
    public BaseMainFunction make() {
        BaseMainFunction b = new BaseMainFunction();
        return b;
    }

    public final static java.lang.String CS_FUNCTION_ID = "function_id";
    public final static java.lang.String CS_FUNCTION_NAME = "function_name";
    public final static java.lang.String CS_PARENT_ID = "parent_id";
    public final static java.lang.String CS_FUNCTION_CODE = "function_code";
    public final static java.lang.String CS_APPLICATION_ID = "application_id";
    public final static java.lang.String CS_FUNCTION_TYPE = "function_type";
    public final static java.lang.String CS_PRIVILEGE_TYPE = "privilege_type";
    public final static java.lang.String CS_ENABLED = "enabled";
    public final static java.lang.String CS_LEAF = "leaf";
    public final static java.lang.String CS_IS_MOBILE = "is_mobile";
    public final static java.lang.String CS_ICON = "icon";
    public final static java.lang.String CS_STYLE_NAME = "style_name";
    public final static java.lang.String CS_CLASS_NAME = "class_name";
    public final static java.lang.String CS_ICON_COLOR = "icon_color";
    public final static java.lang.String CS_MARGIN_TOP = "margin_top";
    public final static java.lang.String CS_MARGIN_BOTTOM = "margin_bottom";
    public final static java.lang.String CS_EXECUTE_NAME = "execute_name";
    public final static java.lang.String CS_REMARK = "remark";
    public final static java.lang.String CS_SEQ_NO = "seq_no";

    public final static java.lang.String ALL_CAPTIONS = "功能编码,功能名称,上级代码,功能代码,应用系统,功能类型,1-菜单,2-按钮,3-数据权限,权限类型,可用,叶子节点,移动端,图标,样式,类名称,,顶部空间,底部,执行程序,备注,";

    public java.lang.Integer getFunctionId() {
        return this.__function_id;
    }

    public void setFunctionId(java.lang.Integer value) {
        this.__function_id = value;
    }

    public java.lang.String getFunctionName() {
        return this.__function_name;
    }

    public void setFunctionName(java.lang.String value) {
        this.__function_name = value;
    }

    public java.lang.Integer getParentId() {
        return this.__parent_id;
    }

    public void setParentId(java.lang.Integer value) {
        this.__parent_id = value;
    }

    public java.lang.String getFunctionCode() {
        return this.__function_code;
    }

    public void setFunctionCode(java.lang.String value) {
        this.__function_code = value;
    }

    public java.lang.Integer getApplicationId() {
        return this.__application_id;
    }

    public void setApplicationId(java.lang.Integer value) {
        this.__application_id = value;
    }

    public java.lang.Integer getFunctionType() {
        return this.__function_type;
    }

    public void setFunctionType(java.lang.Integer value) {
        this.__function_type = value;
    }

    public java.lang.Integer getPrivilegeType() {
        return this.__privilege_type;
    }

    public void setPrivilegeType(java.lang.Integer value) {
        this.__privilege_type = value;
    }

    public java.lang.Boolean getEnabled() {
        return this.__enabled;
    }

    public void setEnabled(java.lang.Boolean value) {
        this.__enabled = value;
    }

    public java.lang.Boolean getLeaf() {
        return this.__leaf;
    }

    public void setLeaf(java.lang.Boolean value) {
        this.__leaf = value;
    }

    public java.lang.Boolean getIsMobile() {
        return this.__is_mobile;
    }

    public void setIsMobile(java.lang.Boolean value) {
        this.__is_mobile = value;
    }

    public java.lang.String getIcon() {
        return this.__icon;
    }

    public void setIcon(java.lang.String value) {
        this.__icon = value;
    }

    public java.lang.String getStyleName() {
        return this.__style_name;
    }

    public void setStyleName(java.lang.String value) {
        this.__style_name = value;
    }

    public java.lang.String getClassName() {
        return this.__class_name;
    }

    public void setClassName(java.lang.String value) {
        this.__class_name = value;
    }

    public java.lang.String getIconColor() {
        return this.__icon_color;
    }

    public void setIconColor(java.lang.String value) {
        this.__icon_color = value;
    }

    public java.lang.Integer getMarginTop() {
        return this.__margin_top;
    }

    public void setMarginTop(java.lang.Integer value) {
        this.__margin_top = value;
    }

    public java.lang.Integer getMarginBottom() {
        return this.__margin_bottom;
    }

    public void setMarginBottom(java.lang.Integer value) {
        this.__margin_bottom = value;
    }

    public java.lang.String getExecuteName() {
        return this.__execute_name;
    }

    public void setExecuteName(java.lang.String value) {
        this.__execute_name = value;
    }

    public java.lang.String getRemark() {
        return this.__remark;
    }

    public void setRemark(java.lang.String value) {
        this.__remark = value;
    }

    public java.lang.Integer getSeqNo() {
        return this.__seq_no;
    }

    public void setSeqNo(java.lang.Integer value) {
        this.__seq_no = value;
    }

    public void cloneCopy(BaseMainFunction __bean) {
        __bean.setFunctionId(getFunctionId());
        __bean.setFunctionName(getFunctionName());
        __bean.setParentId(getParentId());
        __bean.setFunctionCode(getFunctionCode());
        __bean.setApplicationId(getApplicationId());
        __bean.setFunctionType(getFunctionType());
        __bean.setPrivilegeType(getPrivilegeType());
        __bean.setEnabled(getEnabled());
        __bean.setLeaf(getLeaf());
        __bean.setIsMobile(getIsMobile());
        __bean.setIcon(getIcon());
        __bean.setStyleName(getStyleName());
        __bean.setClassName(getClassName());
        __bean.setIconColor(getIconColor());
        __bean.setMarginTop(getMarginTop());
        __bean.setMarginBottom(getMarginBottom());
        __bean.setExecuteName(getExecuteName());
        __bean.setRemark(getRemark());
        __bean.setSeqNo(getSeqNo());
    }

    public java.lang.String toCSVString() {

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        StringBuilder sb = new StringBuilder();
        sb.append(getFunctionId() == null ? "" : getFunctionId());
        sb.append(",");
        sb.append(getFunctionName() == null ? "" : getFunctionName());
        sb.append(",");
        sb.append(getParentId() == null ? "" : getParentId());
        sb.append(",");
        sb.append(getFunctionCode() == null ? "" : getFunctionCode());
        sb.append(",");
        sb.append(getApplicationId() == null ? "" : getApplicationId());
        sb.append(",");
        sb.append(getFunctionType() == null ? "" : getFunctionType());
        sb.append(",");
        sb.append(getPrivilegeType() == null ? "" : getPrivilegeType());
        sb.append(",");
        sb.append(getEnabled() == null ? "" : getEnabled());
        sb.append(",");
        sb.append(getLeaf() == null ? "" : getLeaf());
        sb.append(",");
        sb.append(getIsMobile() == null ? "" : getIsMobile());
        sb.append(",");
        sb.append(getIcon() == null ? "" : getIcon());
        sb.append(",");
        sb.append(getStyleName() == null ? "" : getStyleName());
        sb.append(",");
        sb.append(getClassName() == null ? "" : getClassName());
        sb.append(",");
        sb.append(getIconColor() == null ? "" : getIconColor());
        sb.append(",");
        sb.append(getMarginTop() == null ? "" : getMarginTop());
        sb.append(",");
        sb.append(getMarginBottom() == null ? "" : getMarginBottom());
        sb.append(",");
        sb.append(getExecuteName() == null ? "" : getExecuteName());
        sb.append(",");
        sb.append(getRemark() == null ? "" : getRemark());
        sb.append(",");
        sb.append(getSeqNo() == null ? "" : getSeqNo());
        return sb.toString();
    }

    @Override
    public int compareTo(BaseMainFunction o) {
        return __function_id == null ? -1 : __function_id.compareTo(o.getFunctionId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.__function_id);
        hash = 97 * hash + Objects.hashCode(this.__function_name);
        hash = 97 * hash + Objects.hashCode(this.__parent_id);
        hash = 97 * hash + Objects.hashCode(this.__function_code);
        hash = 97 * hash + Objects.hashCode(this.__application_id);
        hash = 97 * hash + Objects.hashCode(this.__function_type);
        hash = 97 * hash + Objects.hashCode(this.__privilege_type);
        hash = 97 * hash + Objects.hashCode(this.__enabled);
        hash = 97 * hash + Objects.hashCode(this.__leaf);
        hash = 97 * hash + Objects.hashCode(this.__is_mobile);
        hash = 97 * hash + Objects.hashCode(this.__icon);
        hash = 97 * hash + Objects.hashCode(this.__style_name);
        hash = 97 * hash + Objects.hashCode(this.__class_name);
        hash = 97 * hash + Objects.hashCode(this.__icon_color);
        hash = 97 * hash + Objects.hashCode(this.__margin_top);
        hash = 97 * hash + Objects.hashCode(this.__margin_bottom);
        hash = 97 * hash + Objects.hashCode(this.__execute_name);
        hash = 97 * hash + Objects.hashCode(this.__remark);
        hash = 97 * hash + Objects.hashCode(this.__seq_no);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseMainFunction o = (BaseMainFunction) obj;
        if (!Objects.equals(this.__function_id, o.getFunctionId())) {
            return false;
        }
        if (!Objects.equals(this.__function_name, o.getFunctionName())) {
            return false;
        }
        if (!Objects.equals(this.__parent_id, o.getParentId())) {
            return false;
        }
        if (!Objects.equals(this.__function_code, o.getFunctionCode())) {
            return false;
        }
        if (!Objects.equals(this.__application_id, o.getApplicationId())) {
            return false;
        }
        if (!Objects.equals(this.__function_type, o.getFunctionType())) {
            return false;
        }
        if (!Objects.equals(this.__privilege_type, o.getPrivilegeType())) {
            return false;
        }
        if (!Objects.equals(this.__enabled, o.getEnabled())) {
            return false;
        }
        if (!Objects.equals(this.__leaf, o.getLeaf())) {
            return false;
        }
        if (!Objects.equals(this.__is_mobile, o.getIsMobile())) {
            return false;
        }
        if (!Objects.equals(this.__icon, o.getIcon())) {
            return false;
        }
        if (!Objects.equals(this.__style_name, o.getStyleName())) {
            return false;
        }
        if (!Objects.equals(this.__class_name, o.getClassName())) {
            return false;
        }
        if (!Objects.equals(this.__icon_color, o.getIconColor())) {
            return false;
        }
        if (!Objects.equals(this.__margin_top, o.getMarginTop())) {
            return false;
        }
        if (!Objects.equals(this.__margin_bottom, o.getMarginBottom())) {
            return false;
        }
        if (!Objects.equals(this.__execute_name, o.getExecuteName())) {
            return false;
        }
        if (!Objects.equals(this.__remark, o.getRemark())) {
            return false;
        }
        if (!Objects.equals(this.__seq_no, o.getSeqNo())) {
            return false;
        }
        return true;
    }

    @Override
    public java.lang.String toJSONString() {

        StringBuilder sb = new StringBuilder();
        int count = 0;
        if (getFunctionId() != null) {
            sb.append(__wrapNumber(count++, "functionId", getFunctionId()));
        }
        if (getFunctionName() != null) {
            sb.append(__wrapString(count++, "functionName", getFunctionName()));
        }
        if (getParentId() != null) {
            sb.append(__wrapNumber(count++, "parentId", getParentId()));
        }
        if (getFunctionCode() != null) {
            sb.append(__wrapString(count++, "functionCode", getFunctionCode()));
        }
        if (getApplicationId() != null) {
            sb.append(__wrapNumber(count++, "applicationId", getApplicationId()));
        }
        if (getFunctionType() != null) {
            sb.append(__wrapNumber(count++, "functionType", getFunctionType()));
        }
        if (getPrivilegeType() != null) {
            sb.append(__wrapNumber(count++, "privilegeType", getPrivilegeType()));
        }
        if (getEnabled() != null) {
            sb.append(__wrapBoolean(count++, "enabled", getEnabled()));
        }
        if (getLeaf() != null) {
            sb.append(__wrapBoolean(count++, "leaf", getLeaf()));
        }
        if (getIsMobile() != null) {
            sb.append(__wrapBoolean(count++, "isMobile", getIsMobile()));
        }
        if (getIcon() != null) {
            sb.append(__wrapString(count++, "icon", getIcon()));
        }
        if (getStyleName() != null) {
            sb.append(__wrapString(count++, "styleName", getStyleName()));
        }
        if (getClassName() != null) {
            sb.append(__wrapString(count++, "className", getClassName()));
        }
        if (getIconColor() != null) {
            sb.append(__wrapString(count++, "iconColor", getIconColor()));
        }
        if (getMarginTop() != null) {
            sb.append(__wrapNumber(count++, "marginTop", getMarginTop()));
        }
        if (getMarginBottom() != null) {
            sb.append(__wrapNumber(count++, "marginBottom", getMarginBottom()));
        }
        if (getExecuteName() != null) {
            sb.append(__wrapString(count++, "executeName", getExecuteName()));
        }
        if (getRemark() != null) {
            sb.append(__wrapString(count++, "remark", getRemark()));
        }
        if (getSeqNo() != null) {
            sb.append(__wrapNumber(count++, "seqNo", getSeqNo()));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        if ((val = values.get("functionId")) != null) {
            setFunctionId(__getInt(val));
        }
        if ((val = values.get("functionName")) != null) {
            setFunctionName(__getString(val));
        }
        if ((val = values.get("parentId")) != null) {
            setParentId(__getInt(val));
        }
        if ((val = values.get("functionCode")) != null) {
            setFunctionCode(__getString(val));
        }
        if ((val = values.get("applicationId")) != null) {
            setApplicationId(__getInt(val));
        }
        if ((val = values.get("functionType")) != null) {
            setFunctionType(__getInt(val));
        }
        if ((val = values.get("privilegeType")) != null) {
            setPrivilegeType(__getInt(val));
        }
        if ((val = values.get("enabled")) != null) {
            setEnabled(__getBoolean(val));
        }
        if ((val = values.get("leaf")) != null) {
            setLeaf(__getBoolean(val));
        }
        if ((val = values.get("isMobile")) != null) {
            setIsMobile(__getBoolean(val));
        }
        if ((val = values.get("icon")) != null) {
            setIcon(__getString(val));
        }
        if ((val = values.get("styleName")) != null) {
            setStyleName(__getString(val));
        }
        if ((val = values.get("className")) != null) {
            setClassName(__getString(val));
        }
        if ((val = values.get("iconColor")) != null) {
            setIconColor(__getString(val));
        }
        if ((val = values.get("marginTop")) != null) {
            setMarginTop(__getInt(val));
        }
        if ((val = values.get("marginBottom")) != null) {
            setMarginBottom(__getInt(val));
        }
        if ((val = values.get("executeName")) != null) {
            setExecuteName(__getString(val));
        }
        if ((val = values.get("remark")) != null) {
            setRemark(__getString(val));
        }
        if ((val = values.get("seqNo")) != null) {
            setSeqNo(__getInt(val));
        }
    }

    protected java.lang.Integer __function_id;
    protected java.lang.String __function_name;
    protected java.lang.Integer __parent_id;
    protected java.lang.String __function_code;
    protected java.lang.Integer __application_id;
    protected java.lang.Integer __function_type;
    protected java.lang.Integer __privilege_type;
    protected java.lang.Boolean __enabled;
    protected java.lang.Boolean __leaf;
    protected java.lang.Boolean __is_mobile;
    protected java.lang.String __icon;
    protected java.lang.String __style_name;
    protected java.lang.String __class_name;
    protected java.lang.String __icon_color;
    protected java.lang.Integer __margin_top;
    protected java.lang.Integer __margin_bottom;
    protected java.lang.String __execute_name;
    protected java.lang.String __remark;
    protected java.lang.Integer __seq_no;
}
