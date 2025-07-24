package delicacy.system.custom.handler;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseSystemProcessInstanceActivity;
import delicacy.system.bean.BaseSystemProcessPooledTask;
import delicacy.system.dao.Employee;
import delicacy.system.dao.SystemProcessInstanceActivity;
import delicacy.system.dao.SystemProcessPooledTask;

public class CustomSystemWorkFlowProcess implements GenericProcessor{
	
	private static final String GET_APPROVALED_COMMENT = "getApprovaledComment";//获取流程已审意见
	
	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		Map<String, Object> result = null;
		if (!BaseHelpUtils.isNullOrEmpty(creteria)) {
			@SuppressWarnings("rawtypes")
			JSON parser = new JSON(new StringReader(creteria));
			result = (Map<String, Object>) parser.parse(new BasicHandler());
		}
		String optType = BaseHelpUtils.getStringValue(result, "opt_type");
		if (!BaseHelpUtils.isNullOrEmpty(result) && !BaseHelpUtils.isNullOrEmpty(optType)) {
			switch (optType) {
				case GET_APPROVALED_COMMENT:
					return getApprovaledComment(result);
			}
		}
		return null;
	}
	
	private String getApprovaledComment(Map<String, Object> params) throws SQLException{
		int processInstanceId = BaseHelpUtils.getIntValue(params, "processInstanceId");
		Employee eDao = new Employee();
		StringBuilder sb = new StringBuilder("");
		SystemProcessInstanceActivity spiaDao = new SystemProcessInstanceActivity();
		spiaDao.setConditionProcessInstanceId("=", processInstanceId);
		List<BaseSystemProcessInstanceActivity> spiaList = spiaDao.conditionalLoad(" order by activity_id");
		SystemProcessPooledTask spptDao = new SystemProcessPooledTask();
		for (BaseSystemProcessInstanceActivity baseSystemProcessInstanceActivity : spiaList) {
			Integer activityType = baseSystemProcessInstanceActivity.getActivityType();
			Integer status = baseSystemProcessInstanceActivity.getStatus();
			Integer employeeId = baseSystemProcessInstanceActivity.getEmployeeId();
			String processComment = baseSystemProcessInstanceActivity.getProcessComment();
			if(activityType == 2 || activityType == 3){
				if(status == 2 || status == 4 || status == 5 || status == 6){
					if(null == employeeId){
						spptDao.clear();
						spptDao.setConditionActivityId("=", baseSystemProcessInstanceActivity.getActivityId());
						spptDao.setConditionProcessInstanceId("=", processInstanceId);
						spptDao.setConditionStatus("=", 2);
						List<BaseSystemProcessPooledTask> spptList = spptDao.conditionalLoad();
						for (BaseSystemProcessPooledTask baseSystemProcessPooledTask : spptList) {
							if(!BaseHelpUtils.isNullOrEmpty(baseSystemProcessPooledTask.getProcessComment())){
								eDao.clear();
								eDao.setEmployeeId(baseSystemProcessPooledTask.getEmployeeId());
								eDao.load();
								sb.append(eDao.getEmployeeName())
									.append("(通过)：")
									.append(baseSystemProcessPooledTask.getProcessComment())
									.append("。\r\n");
							}
							
						}
					}else{
						if(!BaseHelpUtils.isNullOrEmpty(processComment)){
							eDao.clear();
							eDao.setEmployeeId(employeeId);
							eDao.load();
							sb.append(eDao.getEmployeeName());
							sb.append("(");
							if(status == 2){
								sb.append("通过");
							}else if(status == 4){
								sb.append("回退给申请人");
							}else if(status == 5){
								sb.append("回退");
							}else if(status == 6){
								sb.append("转交");
							}
							sb.append(")：")
								.append(processComment)
								.append("。\r\n");
						}
						
					}
				}
			}
		}
		return sb.toString();
	}
}
