package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseEmployeeProcess;
import delicacy.system.bean.ConditionEmployeeProcess;
import delicacy.system.query.QueryEmployeeProcess;
import delicacy.common.KeyValuePair;
import delicacy.common.GenericQuery;
import delicacy.common.GenericDownloadProcessor;
import delicacy.common.GenericBase;
import java.io.File;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import delicacy.common.BaseCollection;

public class EmployeeProcessHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(EmployeeProcessHandler.class);

	public static BaseCollection<BaseEmployeeProcess> executeQueryEmployeeProcess(ConditionEmployeeProcess c, KeyValuePair[] replacements ) throws Exception {
		QueryEmployeeProcess dao = new QueryEmployeeProcess();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseEmployeeProcess> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionEmployeeProcess c = new ConditionEmployeeProcess();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseEmployeeProcess> result = executeQueryEmployeeProcess(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseEmployeeProcess> result;
		ConditionEmployeeProcess c = new ConditionEmployeeProcess();
		c.setDataFromJSON(creteria);
		QueryEmployeeProcess dao = new QueryEmployeeProcess();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseEmployeeProcess.ALL_CAPTIONS);
			for(BaseEmployeeProcess b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


