package com.delicacy.client.data;

/**
 *
 * @author caogx
 */
public class BusinessAbstract {
    
    public BusinessAbstract(int id, String subject){
        this.__businessId = id;
        this.__businessSubject = subject;
    }
    
    private int __businessId;
    private String __businessSubject;

    /**
     * @return the __businessId
     */
    public int getBusinessId() {
        return __businessId;
    }

    /**
     * @param __businessId the __businessId to set
     */
    public void setBusinessId(int __businessId) {
        this.__businessId = __businessId;
    }

    /**
     * @return the __businessSubject
     */
    public String getBusinessSubject() {
        return __businessSubject;
    }

    /**
     * @param __businessSubject the __businessSubject to set
     */
    public void setBusinessSubject(String __businessSubject) {
        this.__businessSubject = __businessSubject;
    }
    
}
