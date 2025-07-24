package delicacy.system.bean;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BaseRoleWithR extends BaseRole {

    public static BaseRoleWithR newInstance() {
        return new BaseRoleWithR();
    }

    @Override
    public BaseRoleWithR make() {
        BaseRoleWithR b = new BaseRoleWithR();
        return b;
    }

    public List<BaseRoleFunction> getDetailRoleFunction() {
        return this.__detail_role_functions;
    }

    public void setDetailRoleFunction(List<BaseRoleFunction> value) {
        this.__detail_role_functions = value;
    }

    public BaseRole toBase() {
        BaseRole base = new BaseRole();
        cloneCopy(base);
        return base;
    }

    public void getDataFromBase(BaseRole base) {
        base.cloneCopy(this);
    }

    public static List<BaseRole> getBaseList(List<BaseRoleWithR> beans) {
        List<BaseRole> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseRoleWithR bean : beans) {
            result.add(bean.toBase());
        }
        return result;
    }

    public static List<BaseRoleWithR> getBeanList(List<BaseRole> beans) {
        List<BaseRoleWithR> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseRole bean : beans) {
            BaseRoleWithR val = new BaseRoleWithR();
            val.getDataFromBase(bean);
            result.add(val);
        }
        return result;
    }

    @Override
    public java.lang.String toJSONString() {

        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toJSONString());
        count = sb.length();
        sb.append(__wrapList(count, "detailRoleFunction", getDetailRoleFunction()));
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        super.setDataFromMap(values);
        if ((val = values.get("detailRoleFunction")) != null) {
            setDetailRoleFunction(__getList(val, BaseRoleFunction.newInstance()));
        }
    }

    protected List<BaseRoleFunction> __detail_role_functions;
}
