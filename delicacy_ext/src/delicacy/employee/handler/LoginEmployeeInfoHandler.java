package delicacy.employee.handler;

import java.io.File;
import java.io.PrintStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import delicacy.common.BaseCollection;
import delicacy.common.GenericBase;
import delicacy.common.GenericDownloadProcessor;
import delicacy.common.GenericQuery;
import delicacy.common.KeyValuePair;
import delicacy.employee.bean.BaseLoginEmployeeInfo;
import delicacy.employee.bean.ConditionLoginEmployeeInfo;
import delicacy.employee.query.QueryLoginEmployeeInfo;

public class LoginEmployeeInfoHandler implements GenericQuery, GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(LoginEmployeeInfoHandler.class);

	public static BaseCollection<BaseLoginEmployeeInfo> executeQueryLoginEmployeeInfo(ConditionLoginEmployeeInfo c,
			KeyValuePair[] replacements) throws Exception {
		QueryLoginEmployeeInfo dao = new QueryLoginEmployeeInfo();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseLoginEmployeeInfo> result = dao.executeQuery(c.getKeyValues(), c);
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionLoginEmployeeInfo c = new ConditionLoginEmployeeInfo();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseLoginEmployeeInfo> result = executeQueryLoginEmployeeInfo(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BaseCollection<BaseLoginEmployeeInfo> result;
		ConditionLoginEmployeeInfo c = new ConditionLoginEmployeeInfo();
		c.setDataFromJSON(creteria);
		QueryLoginEmployeeInfo dao = new QueryLoginEmployeeInfo();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery(c.getKeyValues(), c);
		if (result.getTotalLines() > GenericBase.MAX_EXPORT_LINES) {
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery(c.getKeyValues(), c);
		try (PrintStream ps = new PrintStream(downFile, "GBK")) {
			ps.println(BaseLoginEmployeeInfo.ALL_CAPTIONS);
			for (BaseLoginEmployeeInfo b : result.getCollections()) {
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}
}
