package com.delicacy.client.ui;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author guangxun
 */
public class Workflows {
    
    public final static Map<String, WorkflowProcessor> PROCESSORS = new HashMap<>();
    
    public static void addProcessor(String name, WorkflowProcessor wp){
        PROCESSORS.put(name, wp);
    }
    
    public static WorkflowProcessor getProcessor(String name){
        return PROCESSORS.get(name);
    }
    
    public static void init(){
        
    }
}
