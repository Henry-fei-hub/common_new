package delicacy.servlet;


public class MainFunctionProcessors extends AbstractProcessores{


        @Override
	public void initTables() {
		DAOS.put("DepartmentWithSd", "delicacy.department.handler.DepartmentWithSdHandler");
		QUERIES.put("SystemProcessList", "delicacy.department.handler.SystemProcessListHandler");
		DAOS.put("MainFunction", "delicacy.system.handler.MainFunctionHandler");
	}

}

