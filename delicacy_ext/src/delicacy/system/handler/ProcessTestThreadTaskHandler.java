package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseProcessTestThreadTask;
import delicacy.system.bean.ConditionProcessTestThreadTask;
import delicacy.system.query.QueryProcessTestThreadTask;
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

public class ProcessTestThreadTaskHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(ProcessTestThreadTaskHandler.class);

	public static BaseCollection<BaseProcessTestThreadTask> executeQueryProcessTestThreadTask(ConditionProcessTestThreadTask c, KeyValuePair[] replacements ) throws Exception {
		QueryProcessTestThreadTask dao = new QueryProcessTestThreadTask();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseProcessTestThreadTask> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionProcessTestThreadTask c = new ConditionProcessTestThreadTask();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseProcessTestThreadTask> result = executeQueryProcessTestThreadTask(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseProcessTestThreadTask> result;
		ConditionProcessTestThreadTask c = new ConditionProcessTestThreadTask();
		c.setDataFromJSON(creteria);
		QueryProcessTestThreadTask dao = new QueryProcessTestThreadTask();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseProcessTestThreadTask.ALL_CAPTIONS);
			for(BaseProcessTestThreadTask b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


