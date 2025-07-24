package delicacy.sys.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseSystemProcessWithSs extends BaseSystemProcess {

    public static BaseSystemProcessWithSs newInstance() {
        return new BaseSystemProcessWithSs();
    }

    @Override
    public BaseSystemProcessWithSs make() {
        BaseSystemProcessWithSs b = new BaseSystemProcessWithSs();
        return b;
    }

    public List<BaseSystemProcessActivity> getDetailSystemProcessActivity() {
        return this.__detail_system_process_activities;
    }

    public void setDetailSystemProcessActivity(List<BaseSystemProcessActivity> value) {
        this.__detail_system_process_activities = value;
    }

    public List<BaseSystemProcessLink> getDetailSystemProcessLink() {
        return this.__detail_system_process_links;
    }

    public void setDetailSystemProcessLink(List<BaseSystemProcessLink> value) {
        this.__detail_system_process_links = value;
    }

    public BaseSystemProcess toBase() {
        BaseSystemProcess base = new BaseSystemProcess();
        cloneCopy(base);
        return base;
    }

    public void getDataFromBase(BaseSystemProcess base) {
        base.cloneCopy(this);
    }

    public static List<BaseSystemProcess> getBaseList(List<BaseSystemProcessWithSs> beans) {
        List<BaseSystemProcess> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseSystemProcessWithSs bean : beans) {
            result.add(bean.toBase());
        }
        return result;
    }

    public static List<BaseSystemProcessWithSs> getBeanList(List<BaseSystemProcess> beans) {
        List<BaseSystemProcessWithSs> result = new ArrayList<>();
        if (beans == null || beans.isEmpty()) {
            return result;
        }
        for (BaseSystemProcess bean : beans) {
            BaseSystemProcessWithSs val = new BaseSystemProcessWithSs();
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
        sb.append(__wrapList(count, "detailSystemProcessActivity", getDetailSystemProcessActivity()));
        count = sb.length();
        sb.append(__wrapList(count, "detailSystemProcessLink", getDetailSystemProcessLink()));
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        Object val;
        super.setDataFromMap(values);
        if ((val = values.get("detailSystemProcessActivity")) != null) {
            setDetailSystemProcessActivity(__getList(val, BaseSystemProcessActivity.newInstance()));
        }
        if ((val = values.get("detailSystemProcessLink")) != null) {
            setDetailSystemProcessLink(__getList(val, BaseSystemProcessLink.newInstance()));
        }
    }

    protected List<BaseSystemProcessActivity> __detail_system_process_activities;
    protected List<BaseSystemProcessLink> __detail_system_process_links;
}
