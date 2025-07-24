package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSsystemprocesspor;
import delicacy.system.bean.ConditionSsystemprocesspor;
import delicacy.system.query.QuerySsystemprocesspor;
import delicacy.common.KeyValuePair;
import delicacy.common.GenericQuery;
import delicacy.common.GenericDownloadProcessor;
import delicacy.common.GenericBase;
import java.io.File;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import delicacy.system.bean.BaseSystemProcessActivity;
import delicacy.system.dao.SystemProcessActivity;
import delicacy.common.BaseCollection;

public class SsystemprocessporHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SsystemprocessporHandler.class);

	public static BaseCollection<BaseSsystemprocesspor> executeQuerySsystemprocesspor(ConditionSsystemprocesspor c, KeyValuePair[] replacements ) throws Exception {
		QuerySsystemprocesspor dao = new QuerySsystemprocesspor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSsystemprocesspor> result = dao.executeQuery( c.getKeyValues(), c) ;
		if(c.isIncludeDetail() && result.getRecordNumber() > 0){
			SystemProcessActivity daoSystemProcessActivity = new SystemProcessActivity();
			for(BaseSsystemprocesspor bean : result.getCollections()){
				daoSystemProcessActivity.setConditionProcessId("=", bean.getProcessId());
				bean.setDetailSystemProcessActivity(daoSystemProcessActivity.conditionalLoad(null));
			}
		}
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSsystemprocesspor c = new ConditionSsystemprocesspor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSsystemprocesspor> result = executeQuerySsystemprocesspor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSsystemprocesspor> result;
		ConditionSsystemprocesspor c = new ConditionSsystemprocesspor();
		c.setDataFromJSON(creteria);
		QuerySsystemprocesspor dao = new QuerySsystemprocesspor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSsystemprocesspor.ALL_CAPTIONS);
			for(BaseSsystemprocesspor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


