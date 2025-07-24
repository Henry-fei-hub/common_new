package delicacy.system.bean;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BaseEmployeeWithE extends BaseEmployee {

    public static BaseEmployeeWithE newInstance() {
        return new BaseEmployeeWithE();
    }

    @Override
    public BaseEmployeeWithE make() {
        BaseEmployeeWithE b = new BaseEmployeeWithE();
        return b;
    }

    public List<BaseEmployeeRole> getDetailEmployeeRole() {
        return this.__detail_employee_roles;
    }

    public void setDetailEmployeeRole(List<BaseEmployeeRole> value) {
        this.__detail_employee_roles = value;
    }

    public BaseEmployee toBase() {
        BaseEmployee base = new BaseEmployee();
        cloneCopy(base);
        return base;
    }

    public void getDataFromBase(BaseEmployee base) {
        base.cloneCopy(this);
    }

    public static List<BaseEmployee> getBaseList(List<BaseEmployeeWithE> beans) {
        List<BaseEmployee> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseEmployeeWithE bean : beans) {
            result.add(bean.toBase());
        }
        return result;
    }

    public static List<BaseEmployeeWithE> getBeanList(List<BaseEmployee> beans) {
        List<BaseEmployeeWithE> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseEmployee bean : beans) {
            BaseEmployeeWithE val = new BaseEmployeeWithE();
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
        sb.append(__wrapList(count, "detailEmployeeRole", getDetailEmployeeRole()));
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        super.setDataFromMap(values);
        if ((val = values.get("detailEmployeeRole")) != null) {
            setDetailEmployeeRole(__getList(val, BaseEmployeeRole.newInstance()));
        }
    }

    protected List<BaseEmployeeRole> __detail_employee_roles;
}
