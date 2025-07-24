package delicacy.system.bean;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BaseDepartmentWithD extends BaseDepartment {

    public static BaseDepartmentWithD newInstance() {
        return new BaseDepartmentWithD();
    }

    @Override
    public BaseDepartmentWithD make() {
        BaseDepartmentWithD b = new BaseDepartmentWithD();
        return b;
    }

    public List<BaseDepartmentRole> getDetailDepartmentRole() {
        return this.__detail_department_roles;
    }

    public void setDetailDepartmentRole(List<BaseDepartmentRole> value) {
        this.__detail_department_roles = value;
    }

    public BaseDepartment toBase() {
        BaseDepartment base = new BaseDepartment();
        cloneCopy(base);
        return base;
    }

    public void getDataFromBase(BaseDepartment base) {
        base.cloneCopy(this);
    }

    public static List<BaseDepartment> getBaseList(List<BaseDepartmentWithD> beans) {
        List<BaseDepartment> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseDepartmentWithD bean : beans) {
            result.add(bean.toBase());
        }
        return result;
    }

    public static List<BaseDepartmentWithD> getBeanList(List<BaseDepartment> beans) {
        List<BaseDepartmentWithD> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseDepartment bean : beans) {
            BaseDepartmentWithD val = new BaseDepartmentWithD();
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
        sb.append(__wrapList(count, "detailDepartmentRole", getDetailDepartmentRole()));
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        super.setDataFromMap(values);
        if ((val = values.get("detailDepartmentRole")) != null) {
            setDetailDepartmentRole(__getList(val, BaseDepartmentRole.newInstance()));
        }
    }

    protected List<BaseDepartmentRole> __detail_department_roles;
}
