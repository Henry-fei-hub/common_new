package delicacy.sys.business;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.bean.BaseSystemProcessInstance;
import delicacy.sys.bean.BaseSystemProcessPooledTask;

/**
 *
 * @author Peter
 */
public class ProcessInstance extends BaseSystemProcessInstance {

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toJSONString());
        if (activities != null) {
            sb.append(__wrapList(1, "activities", activities));
        }
        if (attentions != null) {
            sb.append(__wrapList(1, "attentions", attentions));
        }
        if (tasks != null) {
            sb.append(__wrapList(1, "tasks", tasks));
        }
        return sb.toString();
    }

    @Override
    public void setDataFromMap(Map<String, Object> values) {
        super.setDataFromMap(values);
        Object val = null;
        if ((val = values.get("activities")) != null) {
            setActivities(__getList(val, ProcessInstanceActivity.newInstance()));
        }
        if ((val = values.get("attentions")) != null) {
            setAttentions(__getList(val, BaseSystemProcessAttention.newInstance()));
        }
        if ((val = values.get("tasks")) != null) {
            setTasks(__getList(val, BaseSystemProcessPooledTask.newInstance()));
        }
    }

    private List<ProcessInstanceActivity> activities = null;
    private List<BaseSystemProcessAttention> attentions = null;
    private List<BaseSystemProcessPooledTask> tasks = null;
    private ProcessInstanceActivity currentActivity = null;

    /**
     * @return the activities
     */
    public List<ProcessInstanceActivity> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(List<ProcessInstanceActivity> activities) {
        this.activities = activities;
    }

    /**
     * @return the attentions
     */
    public List<BaseSystemProcessAttention> getAttentions() {
        return attentions;
    }

    /**
     * @param attentions the attentions to set
     */
    public void setAttentions(List<BaseSystemProcessAttention> attentions) {
        this.attentions = attentions;
    }

    /**
     * @return the tasks
     */
    public List<BaseSystemProcessPooledTask> getTasks() {
        return tasks;
    }

    /**
     * @param tasks the tasks to set
     */
    public void setTasks(List<BaseSystemProcessPooledTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the currentActivity
     */
    public ProcessInstanceActivity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * @param currentActivity the currentActivity to set
     */
    public void setCurrentActivity(ProcessInstanceActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public ProcessInstanceActivity getActiveActivity() {
        if (activities == null || activities.isEmpty()) {
            return null;
        }
        for (ProcessInstanceActivity a : activities) {
            if (Objects.equals(a.getStatus(), 1)) {
                return a;
            }
        }
        return null;
    }

}
