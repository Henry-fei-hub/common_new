package delicacy.sys.business;

import java.util.ArrayList;
import java.util.List;

import delicacy.sys.bean.BaseSystemProcessActivity;

/**
 *
 * @author caogx
 */
public class ProcessActivity extends BaseSystemProcessActivity {
    
    private String condition;
    private List<ProcessActivity> previousActivities = new ArrayList<>();
    private List<ProcessActivity> nextActivities = new ArrayList<>();
    private List<BaseSystemProcessActivity> attentions = new ArrayList<>();

    /**
     * @return the previousActivities
     */
    public List<ProcessActivity> getPreviousActivities() {
        return previousActivities;
    }

    /**
     * @param previousActivities the previousActivities to set
     */
    public void setPreviousActivities(List<ProcessActivity> previousActivities) {
        this.previousActivities = previousActivities;
    }

    /**
     * @return the nextActivities
     */
    public List<ProcessActivity> getNextActivities() {
        return nextActivities;
    }

    /**
     * @param nextActivities the nextActivities to set
     */
    public void setNextActivities(List<ProcessActivity> nextActivities) {
        this.nextActivities = nextActivities;
    }

    /**
     * @return the attentions
     */
    public List<BaseSystemProcessActivity> getAttentions() {
        return attentions;
    }

    /**
     * @param attentions the attentions to set
     */
    public void setAttentions(List<BaseSystemProcessActivity> attentions) {
        this.attentions = attentions;
    }

    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
}
