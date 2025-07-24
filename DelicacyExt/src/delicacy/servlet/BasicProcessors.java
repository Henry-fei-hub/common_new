package delicacy.servlet;

public class BasicProcessors extends AbstractProcessores {

	@Override
	public void initTables() {
		PROCESSORS.put("AttentionProcessor", "delicacy.sys.business.AttentionProcessor");
		PROCESSORS.put("WorkFlowModification", "delicacy.sys.business.WorkFlowModification");
		PROCESSORS.put("WorkFlowActivityProcessor", "delicacy.sys.business.WorkFlowActivityProcessor");
		PROCESSORS.put("WorkFlowPooledTaskProcessor", "delicacy.sys.business.WorkFlowPooledTaskProcessor");
		PROCESSORS.put("NewCreateWorkFlow", "delicacy.sys.business.NewCreateWorkFlow");
		PROCESSORS.put("GetProcessInstance", "delicacy.sys.business.GetProcessInstance");
		DAOS.put("SystemDictionary", "delicacy.sys.handler.SystemDictionaryHandler");
		DAOS.put("WorkFlowPage", "delicacy.sys.handler.WorkFlowPageHandler");
		DAOS.put("SystemProcess", "delicacy.sys.handler.SystemProcessHandler");
		DAOS.put("SystemProcessType", "delicacy.sys.handler.SystemProcessTypeHandler");
		DAOS.put("SystemProcessPooledTask", "delicacy.sys.handler.SystemProcessPooledTaskHandler");
		DAOS.put("SystemProcessLink", "delicacy.sys.handler.SystemProcessLinkHandler");
		DAOS.put("SystemProcessDepartment", "delicacy.sys.handler.SystemProcessDepartmentHandler");
		DAOS.put("SystemProcessInstanceActivity", "delicacy.sys.handler.SystemProcessInstanceActivityHandler");
		DAOS.put("SystemProcessAttention", "delicacy.sys.handler.SystemProcessAttentionHandler");
		DAOS.put("SystemProcessActivity", "delicacy.sys.handler.SystemProcessActivityHandler");
		DAOS.put("SystemProcessInstance", "delicacy.sys.handler.SystemProcessInstanceHandler");
		DAOS.put("SerialNumber", "delicacy.sys.handler.SerialNumberHandler");
	}

}
