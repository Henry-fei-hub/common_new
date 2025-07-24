package delicacy.bean.utils;

import java.sql.SQLException;
import java.util.Date;

import delicacy.common.BaseHelpUtils;
import delicacy.system.dao.SystemLog;
import delicacy.system.executor.SelectValueCache;

public class SystemLogUtils {
	/**
	 * 保存系统日志
	 * @param operator
	 * @param operatorName
	 * @param logContent
	 * @param logType
	 * @param fromHost
	 * @throws SQLException
	 */
	public static void saveSystemLog(Integer operator, String operatorName, String logContent, Integer logType, String fromHost) throws SQLException{
		SystemLog dao = new SystemLog();
		dao.setOperator(operator);
		if(BaseHelpUtils.isNullOrEmpty(operatorName) && null != operator) {
			operatorName = SelectValueCache.getSelectValue("employees", operator+"");
		}
		dao.setOperatorName(operatorName);
		dao.setLogContent(logContent);
		dao.setLogType(logType);
		dao.setLogTime(new Date());
		dao.setFromHost(fromHost);
		dao.save();
	}
}
