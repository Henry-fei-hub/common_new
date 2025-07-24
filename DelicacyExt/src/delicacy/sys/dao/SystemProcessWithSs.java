package delicacy.sys.dao;

import java.util.ArrayList;
import java.util.List;

import delicacy.sys.bean.BaseSystemProcessActivity;
import delicacy.sys.bean.BaseSystemProcessLink;
import delicacy.sys.bean.BaseSystemProcessWithSs;


public class SystemProcessWithSs extends SystemProcess {

    public SystemProcessWithSs() throws java.sql.SQLException {
        initColumnNames();
    }

    public List<BaseSystemProcessActivity> getDetailSystemProcessActivity() {
        return this.__detail_systemprocessactivity;
    }

    public void setDetailSystemProcessActivity(List<BaseSystemProcessActivity> value) {
        this.__detail_systemprocessactivity = value;
    }

    public List<BaseSystemProcessLink> getDetailSystemProcessLink() {
        return this.__detail_systemprocesslink;
    }

    public void setDetailSystemProcessLink(List<BaseSystemProcessLink> value) {
        this.__detail_systemprocesslink = value;
    }

    private void deleteAndSaveDetailTables() throws java.sql.SQLException {
        // 实例化  DAO
        SystemProcessActivity systemprocessactivity = new SystemProcessActivity();
        // 装入数据库中原来明细数据
        systemprocessactivity.setConditionProcessId("=", getProcessId());
        List<BaseSystemProcessActivity> originalsystemprocessactivity = systemprocessactivity.conditionalLoad(null);
        // 如果当前明细不为空
        if (getDetailSystemProcessActivity() != null) {
            // 需要删除的明细数据
            List<BaseSystemProcessActivity> needToDeleteSystemProcessActivity = new ArrayList<>();
            // 需要增加的明细数据
            List<BaseSystemProcessActivity> needToAddSystemProcessActivity = new ArrayList<>();
            // 找到在原明细中存在的数据并修改该数据，如果在原数据中没有则加到需要添加的列表中
            for (BaseSystemProcessActivity __systemprocessactivity : getDetailSystemProcessActivity()) {
                // 赋值关联字段
                __systemprocessactivity.setProcessId(getProcessId());
                // 设置找到标志
                boolean foundSystemProcessActivity = false;
                // 在原明细中找
                for (BaseSystemProcessActivity __originalsystemprocessactivity : originalsystemprocessactivity) {
                    // 如果两个对象主键相同
                    if (__systemprocessactivity.compareTo(__originalsystemprocessactivity) == 0) {
                        // 设置找到标志
                        foundSystemProcessActivity = true;
                        // 修改该条数据
                        // 清空DAO中当前数据
                        systemprocessactivity.clearCurrentData();
                        // 把原明细数据装入DAO
                        systemprocessactivity.setDataFromBase(__originalsystemprocessactivity);
                        // 清空DAO数据修改标志, 就像数据刚从数据库中装入一样
                        systemprocessactivity.clearModifiedFlags();
                        // 把新的数据装入
                        systemprocessactivity.setDataFromBase(__systemprocessactivity);
                        // 执行修改
                        systemprocessactivity.update();
                        break;
                    }
                }
                // 如果没找到，则加入需要增加的列表中
                if (!foundSystemProcessActivity) {
                    needToAddSystemProcessActivity.add(__systemprocessactivity);
                }
            }
            // 找到那些在原明细中有，但当前明细中没有的条目
            for (BaseSystemProcessActivity __originalsystemprocessactivity : originalsystemprocessactivity) {
                boolean foundSystemProcessActivity = false;
                for (BaseSystemProcessActivity __systemprocessactivity : getDetailSystemProcessActivity()) {
                    if (__systemprocessactivity.compareTo(__originalsystemprocessactivity) == 0) {
                        foundSystemProcessActivity = true;
                        break;
                    }
                }
                if (!foundSystemProcessActivity) {
                    needToDeleteSystemProcessActivity.add(__originalsystemprocessactivity);
                }
            }
            // 处理需要增加的明细数据
            for (BaseSystemProcessActivity __systemprocessactivity : needToAddSystemProcessActivity) {
                systemprocessactivity.setPrimaryKeyFromBase(__systemprocessactivity);
                // 如果该条数据主键为空，则插入数据, 注意这种处理方式只对子表主键为自增的有效
                // 如果子表的主键不是自增类型，则需要考虑如何生成主键, 并修改下面的代码
                if (systemprocessactivity.isPrimaryKeyNull()) {
                    systemprocessactivity.clearCurrentData();
                    systemprocessactivity.setDataFromBase(__systemprocessactivity);
                    systemprocessactivity.save();
                } // 否则从数据中查询该数据，找到就修改，否则插入
                else if (systemprocessactivity.load()) {
                    systemprocessactivity.setDataFromBase(__systemprocessactivity);
                    systemprocessactivity.update();
                } else {
                    systemprocessactivity.clearCurrentData();
                    systemprocessactivity.setDataFromBase(__systemprocessactivity);
                    systemprocessactivity.save();
                }
            }
            // 处理需要删除的明细数据
            for (BaseSystemProcessActivity __systemprocessactivity : needToDeleteSystemProcessActivity) {
                deleteFromSystemProcessActivity(__systemprocessactivity, systemprocessactivity);
            }
        } else {
            // 如果当前明细为空，说明所有原有的明细都需要删除
            for (BaseSystemProcessActivity __systemprocessactivity : originalsystemprocessactivity) {
                deleteFromSystemProcessActivity(__systemprocessactivity, systemprocessactivity);
            }
        }
        // 实例化  DAO
        SystemProcessLink systemprocesslink = new SystemProcessLink();
        // 装入数据库中原来明细数据
        systemprocesslink.setConditionProcessId("=", getProcessId());
        List<BaseSystemProcessLink> originalsystemprocesslink = systemprocesslink.conditionalLoad(null);
        // 如果当前明细不为空
        if (getDetailSystemProcessLink() != null) {
            // 需要删除的明细数据
            List<BaseSystemProcessLink> needToDeleteSystemProcessLink = new ArrayList<>();
            // 需要增加的明细数据
            List<BaseSystemProcessLink> needToAddSystemProcessLink = new ArrayList<>();
            // 找到在原明细中存在的数据并修改该数据，如果在原数据中没有则加到需要添加的列表中
            for (BaseSystemProcessLink __systemprocesslink : getDetailSystemProcessLink()) {
                // 赋值关联字段
                __systemprocesslink.setProcessId(getProcessId());
                // 设置找到标志
                boolean foundSystemProcessLink = false;
                // 在原明细中找
                for (BaseSystemProcessLink __originalsystemprocesslink : originalsystemprocesslink) {
                    // 如果两个对象主键相同
                    if (__systemprocesslink.compareTo(__originalsystemprocesslink) == 0) {
                        // 设置找到标志
                        foundSystemProcessLink = true;
                        // 修改该条数据
                        // 清空DAO中当前数据
                        systemprocesslink.clearCurrentData();
                        // 把原明细数据装入DAO
                        systemprocesslink.setDataFromBase(__originalsystemprocesslink);
                        // 清空DAO数据修改标志, 就像数据刚从数据库中装入一样
                        systemprocesslink.clearModifiedFlags();
                        // 把新的数据装入
                        systemprocesslink.setDataFromBase(__systemprocesslink);
                        // 执行修改
                        systemprocesslink.update();
                        break;
                    }
                }
                // 如果没找到，则加入需要增加的列表中
                if (!foundSystemProcessLink) {
                    needToAddSystemProcessLink.add(__systemprocesslink);
                }
            }
            // 找到那些在原明细中有，但当前明细中没有的条目
            for (BaseSystemProcessLink __originalsystemprocesslink : originalsystemprocesslink) {
                boolean foundSystemProcessLink = false;
                for (BaseSystemProcessLink __systemprocesslink : getDetailSystemProcessLink()) {
                    if (__systemprocesslink.compareTo(__originalsystemprocesslink) == 0) {
                        foundSystemProcessLink = true;
                        break;
                    }
                }
                if (!foundSystemProcessLink) {
                    needToDeleteSystemProcessLink.add(__originalsystemprocesslink);
                }
            }
            // 处理需要增加的明细数据
            for (BaseSystemProcessLink __systemprocesslink : needToAddSystemProcessLink) {
                systemprocesslink.setPrimaryKeyFromBase(__systemprocesslink);
                // 如果该条数据主键为空，则插入数据, 注意这种处理方式只对子表主键为自增的有效
                // 如果子表的主键不是自增类型，则需要考虑如何生成主键, 并修改下面的代码
                if (systemprocesslink.isPrimaryKeyNull()) {
                    systemprocesslink.clearCurrentData();
                    systemprocesslink.setDataFromBase(__systemprocesslink);
                    systemprocesslink.save();
                } // 否则从数据中查询该数据，找到就修改，否则插入
                else if (systemprocesslink.load()) {
                    systemprocesslink.setDataFromBase(__systemprocesslink);
                    systemprocesslink.update();
                } else {
                    systemprocesslink.clearCurrentData();
                    systemprocesslink.setDataFromBase(__systemprocesslink);
                    systemprocesslink.save();
                }
            }
            // 处理需要删除的明细数据
            for (BaseSystemProcessLink __systemprocesslink : needToDeleteSystemProcessLink) {
                deleteFromSystemProcessLink(__systemprocesslink, systemprocesslink);
            }
        } else {
            // 如果当前明细为空，说明所有原有的明细都需要删除
            for (BaseSystemProcessLink __systemprocesslink : originalsystemprocesslink) {
                deleteFromSystemProcessLink(__systemprocesslink, systemprocesslink);
            }
        }
    }

    private void deleteFromSystemProcessActivity(BaseSystemProcessActivity bean, SystemProcessActivity dao) throws java.sql.SQLException {
        dao.setDataFromBase(bean);
        dao.clearModifiedFlags();
        dao.setProcessId(null);
        if (__detail_delete_systemprocessactivity) {
            dao.delete();
        } else {
            dao.update();
        }
    }

    private void deleteFromSystemProcessLink(BaseSystemProcessLink bean, SystemProcessLink dao) throws java.sql.SQLException {
        dao.setDataFromBase(bean);
        dao.clearModifiedFlags();
        dao.setProcessId(null);
        if (__detail_delete_systemprocesslink) {
            dao.delete();
        } else {
            dao.update();
        }
    }

    // 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
    private void deleteDetailTables() throws java.sql.SQLException {
        SystemProcessActivity systemprocessactivity = new SystemProcessActivity();
        systemprocessactivity.setConditionProcessId("=", getProcessId());
        systemprocessactivity.conditionalDelete();
        SystemProcessLink systemprocesslink = new SystemProcessLink();
        systemprocesslink.setConditionProcessId("=", getProcessId());
        systemprocesslink.conditionalDelete();
    }

    // 从数据中按主键查询数据，可以包含明细数据
    public boolean load(boolean loadDetail) throws java.sql.SQLException {
        if (!super.load()) {
            return false;
        }
        if (loadDetail) {
            SystemProcessActivity systemprocessactivity = new SystemProcessActivity();
            systemprocessactivity.setConditionProcessId("=", getProcessId());
            setDetailSystemProcessActivity(systemprocessactivity.conditionalLoad(null));
            SystemProcessLink systemprocesslink = new SystemProcessLink();
            systemprocesslink.setConditionProcessId("=", getProcessId());
            setDetailSystemProcessLink(systemprocesslink.conditionalLoad(null));
        }
        return true;
    }

    public List<BaseSystemProcessWithSs> conditionalLoadExt(String addtional) throws java.sql.SQLException {
        List<BaseSystemProcessWithSs> result = BaseSystemProcessWithSs.getBeanList(conditionalLoad(addtional));
        if (result.size() > 0) {
            SystemProcessActivity systemprocessactivity = new SystemProcessActivity();
            int varCount = 0;
            Object[] convars = new Object[result.size()];
            for (BaseSystemProcessWithSs b : result) {
                convars[varCount++] = b.getProcessId();
            }
            systemprocessactivity.addCondition(BaseSystemProcessActivity.CS_PROCESS_ID, "IN", convars);
            List<BaseSystemProcessActivity> systemprocessactivitys = systemprocessactivity.conditionalLoad(null);
            for (BaseSystemProcessWithSs ii : result) {
                ii.setDetailSystemProcessActivity(new ArrayList<BaseSystemProcessActivity>());
            }
            for (BaseSystemProcessActivity mm : systemprocessactivitys) {
                for (BaseSystemProcessWithSs nn : result) {
                    if (mm.getProcessId().equals(nn.getProcessId())) {
                        nn.getDetailSystemProcessActivity().add(mm);
                        break;
                    }
                }
            }
            SystemProcessLink systemprocesslink = new SystemProcessLink();
            systemprocesslink.addCondition(BaseSystemProcessLink.CS_PROCESS_ID, "IN", convars);
            List<BaseSystemProcessLink> systemprocesslinks = systemprocesslink.conditionalLoad(null);
            for (BaseSystemProcessWithSs ii : result) {
                ii.setDetailSystemProcessLink(new ArrayList<BaseSystemProcessLink>());
            }
            for (BaseSystemProcessLink mm : systemprocesslinks) {
                for (BaseSystemProcessWithSs nn : result) {
                    if (mm.getProcessId().equals(nn.getProcessId())) {
                        nn.getDetailSystemProcessLink().add(mm);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public BaseSystemProcessWithSs generateBaseExt() {
        BaseSystemProcessWithSs ____result = new BaseSystemProcessWithSs();
        setDataToBase(____result);
        return ____result;
    }

    public void setDataFromBase(BaseSystemProcessWithSs __base) {
        super.setDataFromBase(__base);
        setDetailSystemProcessActivity(__base.getDetailSystemProcessActivity());
        setDetailSystemProcessLink(__base.getDetailSystemProcessLink());
    }

    public void setDataToBase(BaseSystemProcessWithSs __base) {
        super.setDataToBase(__base);
        __base.setDetailSystemProcessActivity(getDetailSystemProcessActivity());
        __base.setDetailSystemProcessLink(getDetailSystemProcessLink());
    }

    @Override
    public int save() throws java.sql.SQLException {

        if (super.save() == 0) {
            return 0;
        }
        deleteAndSaveDetailTables();
        return 1;
    }

    @Override
    public int update() throws java.sql.SQLException {

        super.update();
        deleteAndSaveDetailTables();
        return 1;
    }

    @Override
    public int conditionalUpdate() throws java.sql.SQLException {

        super.conditionalUpdate();
        deleteAndSaveDetailTables();
        return 1;
    }

    @Override
    public int delete() throws java.sql.SQLException {
        super.delete();
        deleteDetailTables();
        return 1;
    }

    @Override
    public int conditionalDelete() throws java.sql.SQLException {
        super.conditionalDelete();
        if (getProcessId() != null) {
            deleteDetailTables();
        }
        return 1;
    }

    public boolean isDeleteDetailSystemProcessActivity() {
        return this.__detail_delete_systemprocessactivity;
    }

    public void setDeleteDetailSystemProcessActivity(boolean value) {
        this.__detail_delete_systemprocessactivity = value;
    }

    protected boolean __detail_delete_systemprocessactivity = false;
    protected List<BaseSystemProcessActivity> __detail_systemprocessactivity;

    public boolean isDeleteDetailSystemProcessLink() {
        return this.__detail_delete_systemprocesslink;
    }

    public void setDeleteDetailSystemProcessLink(boolean value) {
        this.__detail_delete_systemprocesslink = value;
    }

    protected boolean __detail_delete_systemprocesslink = false;
    protected List<BaseSystemProcessLink> __detail_systemprocesslink;
}
