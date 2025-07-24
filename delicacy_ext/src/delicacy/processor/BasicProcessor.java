package delicacy.processor;

import delicacy.servlet.AbstractProcessores;

/**
 *
 * @author guangxun
 */
public class BasicProcessor extends AbstractProcessores {

    @Override
    public void initTables() {
    	DAOS.put("CustomDepartmentWithSd", "delicacy.department.CustomDepartmentWithSdHandler");
    	PROCESSORS.put("GetSystemProcessDepartmentTree", "delicacy.extsystem.query.GetSystemProcessDepartmentTree");
		PROCESSORS.put("GenerateSourceDepartmentTree", "delicacy.extsystem.query.GenerateSourceDepartmentTree");
        PROCESSORS.put("GenerateMainFunctionTree", "delicacy.system.query.GenerateMainFunctionTree");
    	DAOS.put("ProcessorTestResult", "delicacy.system.handler.ProcessorTestResultHandler");
    	QUERIES.put("SystemProcessorTestResult", "delicacy.system.handler.SystemProcessorTestResultHandler");
    	QUERIES.put("ProcessTestThreadTask", "delicacy.system.handler.ProcessTestThreadTaskHandler");
    	QUERIES.put("LoginEmployeeInfo", "delicacy.employee.handler.LoginEmployeeInfoHandler");
        DAOS.put("User", "delicacy.system.handler.UserHandler");
        PROCESSORS.put("CustomSystemWorkFlowProcess", "delicacy.system.custom.handler.CustomSystemWorkFlowProcess");
        DAOS.put("MobileFunction", "delicacy.system.handler.MobileFunctionHandler");
        DOWNLOADPROCESSORS.put("OnLoadEmployeeHandler", "delicacy.system.handler.OnLoadEmployeeHandler");
        QUERIES.put("SemployeeRoleInfo", "delicacy.system.handler.SemployeeRoleInfoHandler");
        PROCESSORS.put("GetFunctionTree", "delicacy.system.executor.GetFunctionTree");
        PROCESSORS.put("GenerateFunctionTree", "delicacy.system.executor.GenerateFunctionTree");
        PROCESSORS.put("GenerateDepartmentTree", "delicacy.system.executor.GenerateDepartmentTree");
        PROCESSORS.put("OnEmpDepartmentRoleUpdate", "delicacy.system.executor.OnEmpDepartmentRoleUpdate");
        QUERIES.put("Sapplicationaaor", "delicacy.sys.handler.SapplicationaaorHandler");
        PROCESSORS.put("GetProcessInstance", "delicacy.system.executor.GetProcessInstance");
        PROCESSORS.put("NewCreateWorkFlow", "delicacy.system.executor.NewCreateWorkFlow");
        QUERIES.put("Ssystemprocessattentionpeor", "delicacy.system.handler.SsystemprocessattentionpeorHandler");
        PROCESSORS.put("AddDomainValue", "delicacy.system.executor.AddDomainValue");
        DAOS.put("SystemProcessInstanceWithSss", "delicacy.system.handler.SystemProcessInstanceWithSssHandler");
        PROCESSORS.put("AttentionProcessor", "delicacy.system.executor.AttentionProcessor");
        QUERIES.put("Ssystemprocesspooledtaskpeor", "delicacy.system.handler.SsystemprocesspooledtaskpeorHandler");
        PROCESSORS.put("EmployeePasswordReset", "delicacy.system.executor.EmployeePwdReset");
        DAOS.put("DepartmentWithD", "delicacy.system.handler.DepartmentWithDHandler");
        PROCESSORS.put("UpdateEmpDefaultRole", "delicacy.system.executor.UpdateEmpDefaultRole");
        PROCESSORS.put("DepartmentRoleFunction", "delicacy.system.executor.DepartmentRoleFunction");
        PROCESSORS.put("UpdateApplication", "delicacy.sys.biz.UpdateApplication");
        PROCESSORS.put("WorkFlowPooledTaskProcessor", "delicacy.system.executor.WorkFlowPooledTaskProcessor");
        QUERIES.put("EmployeeProcess", "delicacy.system.handler.EmployeeProcessHandler");
        DAOS.put("SystemProcessType", "delicacy.system.handler.SystemProcessTypeHandler");
        DAOS.put("SystemProcessHumanTask", "delicacy.system.handler.SystemProcessHumanTaskHandler");
        PROCESSORS.put("OnDepartmentRoleUpdate", "delicacy.system.executor.OnDepartmentRoleUpdate");
        DAOS.put("SystemLog", "delicacy.system.handler.SystemLogHandler");
        PROCESSORS.put("CustomEmpSelectRoleProcessor", "delicacy.system.executor.CustomEmpSelectRoleProcessor");
//		PROCESSORS.put("EmployeeLogin", "delicacy.system.executor.DefaultLoginProcessor");
        DAOS.put("SystemProcesseWithS", "delicacy.system.handler.SystemProcesseWithSHandler");
        PROCESSORS.put("DepartmentPart", "delicacy.system.executor.DepartmentPart");
        PROCESSORS.put("CustomEmpRoleFindProcessor", "delicacy.system.executor.CustomEmpRoleFindProcessor");
        DAOS.put("SystemProcessInstanceWithSs", "delicacy.system.handler.SystemProcessInstanceWithSsHandler");
        DAOS.put("SystemProcessAttention", "delicacy.system.handler.SystemProcessAttentionHandler");
        DAOS.put("SystemProcessLink", "delicacy.system.handler.SystemProcessLinkHandler");
        DAOS.put("CustomDepartment", "delicacy.department.CustomDepartmentHandler");
        DAOS.put("SystemProcessDepartment", "delicacy.system.handler.SystemProcessDepartmentHandler");
        PROCESSORS.put("OnRoleFunctionUpdate", "delicacy.system.executor.OnRoleFunctionUpdate");
        DAOS.put("RoleFunction", "delicacy.system.handler.RoleFunctionHandler");
        DAOS.put("DepartmentRole", "delicacy.system.handler.DepartmentRoleHandler");
        PROCESSORS.put("DepartmentRoles", "delicacy.system.executor.DepartmentRoles");
        QUERIES.put("Msystemprocesssor", "delicacy.system.handler.MsystemprocesssorHandler");
        QUERIES.put("Ssystemprocessepor", "delicacy.system.handler.SsystemprocesseporHandler");
        DAOS.put("RoleWithR", "delicacy.system.custom.handler.CustomRoleWithRHandler");
        DAOS.put("Application", "delicacy.system.handler.ApplicationHandler");
        DAOS.put("SystemProcess", "delicacy.system.handler.SystemProcessHandler");
        PROCESSORS.put("EmployeeDelete", "delicacy.system.executor.EmployeeDelete");
        QUERIES.put("Ssystemprocesspor", "delicacy.system.handler.SsystemprocessporHandler");
        DAOS.put("SystemProcessWithSs", "delicacy.system.handler.SystemProcessWithSsHandler");
        QUERIES.put("Ssystemprocessinstanceactivitypeor",
                "delicacy.system.handler.SsystemprocessinstanceactivitypeorHandler");
        DAOS.put("EmployeeWithE", "delicacy.system.handler.EmployeeWithEHandler");
        PROCESSORS.put("ModifyProcessorDefinition", "delicacy.system.executor.ModifyProcessorDefinition");
        PROCESSORS.put("DeleteProcessorDefinition", "delicacy.system.executor.DeleteProcessorDefinition");
        DAOS.put("SystemProcessWithS", "delicacy.system.handler.SystemProcessWithSHandler");
        PROCESSORS.put("NewApplication", "delicacy.sys.biz.NewApplication");
        DAOS.put("SystemProcessInstanceWithS", "delicacy.system.handler.SystemProcessInstanceWithSHandler");
        DAOS.put("Function", "delicacy.system.custom.handler.CustomFunctionHandler");
        DAOS.put("Employee", "delicacy.system.handler.EmployeeHandler");
        PROCESSORS.put("WorkFlowActivityProcessor", "delicacy.system.executor.WorkFlowActivityProcessor");
        QUERIES.put("Memployee", "delicacy.system.handler.MemployeeHandler");
        QUERIES.put("MemployeeNew", "delicacy.system.handler.MemployeeNewHandler");
        QUERIES.put("MemployeeTow", "delicacy.system.handler.MemployeeTowHandler");
		QUERIES.put("DomainValue", "delicacy.system.executor.DomainValueHandler");
        DAOS.put("SystemProcessActivity", "delicacy.system.handler.SystemProcessActivityHandler");
        QUERIES.put("Ssystemprocessinstanceor", "delicacy.system.handler.SsystemprocessinstanceorHandler");
        DAOS.put("SystemProcessPooledTask", "delicacy.system.handler.SystemProcessPooledTaskHandler");
        DAOS.put("DepartmentRoleWithR", "delicacy.system.handler.DepartmentRoleWithRHandler");
        DAOS.put("SystemProcessInstance", "delicacy.system.handler.SystemProcessInstanceHandler");
        DAOS.put("EmployeeRole", "delicacy.system.handler.EmployeeRoleHandler");
        QUERIES.put("Sfunctionffaor", "delicacy.sys.handler.SfunctionffaorHandler");
        PROCESSORS.put("CustomDeptRoleProcessor", "delicacy.system.executor.CustomDeptRoleProcessor");
        DAOS.put("SystemProcessInstanceActivity", "delicacy.system.handler.SystemProcessInstanceActivityHandler");
        PROCESSORS.put("NewProcessorDefinition", "delicacy.system.executor.NewProcessorDefinition");
        DAOS.put("Role", "delicacy.system.handler.RoleHandler");
        PROCESSORS.put("WorkFlowModification", "delicacy.system.executor.WorkFlowModification");
        PROCESSORS.put("DefaultLoginProcessor", "delicacy.system.executor.DefaultLoginProcessor");
        PROCESSORS.put("NewRoleManageWithDepartmentAndFunAndEmp", "delicacy.system.executor.NewRoleManageWithDepartmentAndFunAndEmp");
        PROCESSORS.put("UpdateRoleManageWithDepartmentAndFunAndEmp", "delicacy.system.executor.UpdateRoleManageWithDepartmentAndFunAndEmp");
        PROCESSORS.put("DeleteRoleManageWithDepartmentAndFunAndEmp", "delicacy.system.executor.DeleteRoleManageWithDepartmentAndFunAndEmp");
        PROCESSORS.put("SerachRoleFunction", "delicacy.system.executor.SerachRoleFunction");
        PROCESSORS.put("SerachRoleDepartmentEmployee", "delicacy.system.executor.SerachRoleDepartmentEmployee");
        PROCESSORS.put("SaveRoleDepartmentEmployeeFunction", "delicacy.system.executor.SaveRoleDepartmentEmployeeFunction");
        PROCESSORS.put("UpdateRoleDepartmentEmployeeFunction", "delicacy.system.executor.UpdateRoleDepartmentEmployeeFunction");
        PROCESSORS.put("RoleDepartmentEmployee", "delicacy.system.executor.RoleDepartmentEmployee");
        PROCESSORS.put("CopyEmployeeFunction", "delicacy.system.executor.CopyEmployeeFunction");
        DAOS.put("EmployeeFunction", "delicacy.system.handler.EmployeeFunctionHandler");
        DAOS.put("DepartmentRoleFunctionHandler", "delicacy.system.handler.DepartmentRoleFunctionHandler");
        DAOS.put("DepartmentId", "delicacy.system.handler.DepartmentIdHandler");
        QUERIES.put("CopyEmployeeAllFunctionToOther", "delicacy.functions.handler.CopyEmployeeAllFunctionToOtherHandler");
        PROCESSORS.put("EditorRoleAndDepartmentRoleToEmp", "delicacy.system.executor.EditorRoleAndDepartmentRoleToEmp");
    }

}
