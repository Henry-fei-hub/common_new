package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSystemProcessorTestResult;
import delicacy.system.bean.ConditionSystemProcessorTestResult;
import delicacy.system.query.QuerySystemProcessorTestResult;
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

public class SystemProcessorTestResultHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SystemProcessorTestResultHandler.class);

	public static BaseCollection<BaseSystemProcessorTestResult> executeQuerySystemProcessorTestResult(ConditionSystemProcessorTestResult c, KeyValuePair[] replacements ) throws Exception {
		QuerySystemProcessorTestResult dao = new QuerySystemProcessorTestResult();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSystemProcessorTestResult> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSystemProcessorTestResult c = new ConditionSystemProcessorTestResult();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSystemProcessorTestResult> result = executeQuerySystemProcessorTestResult(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSystemProcessorTestResult> result;
		ConditionSystemProcessorTestResult c = new ConditionSystemProcessorTestResult();
		c.setDataFromJSON(creteria);
		QuerySystemProcessorTestResult dao = new QuerySystemProcessorTestResult();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSystemProcessorTestResult.ALL_CAPTIONS);
			for(BaseSystemProcessorTestResult b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


