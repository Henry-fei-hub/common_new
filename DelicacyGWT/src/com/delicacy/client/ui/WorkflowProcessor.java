package com.delicacy.client.ui;

/**
 *
 * @author guangxun
 */
public interface WorkflowProcessor {
    
    public AbstractProcessPanel getNewCreatePanel();
    
    public AbstractDetailViewer getViewPanel(String processName, Integer processType);
    
    public AbstractProcessPanel getProcessPanel(String processName);
    
    public AbstractPrint getPrintProcessor();
    
}
