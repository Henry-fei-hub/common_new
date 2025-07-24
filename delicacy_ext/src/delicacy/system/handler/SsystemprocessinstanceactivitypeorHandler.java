package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSsystemprocessinstanceactivitypeor;
import delicacy.system.bean.ConditionSsystemprocessinstanceactivitypeor;
import delicacy.system.query.QuerySsystemprocessinstanceactivitypeor;
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

public class SsystemprocessinstanceactivitypeorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SsystemprocessinstanceactivitypeorHandler.class);

	public static BaseCollection<BaseSsystemprocessinstanceactivitypeor> executeQuerySsystemprocessinstanceactivitypeor(ConditionSsystemprocessinstanceactivitypeor c, KeyValuePair[] replacements ) throws Exception {
		QuerySsystemprocessinstanceactivitypeor dao = new QuerySsystemprocessinstanceactivitypeor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSsystemprocessinstanceactivitypeor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSsystemprocessinstanceactivitypeor c = new ConditionSsystemprocessinstanceactivitypeor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSsystemprocessinstanceactivitypeor> result = executeQuerySsystemprocessinstanceactivitypeor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSsystemprocessinstanceactivitypeor> result;
		ConditionSsystemprocessinstanceactivitypeor c = new ConditionSsystemprocessinstanceactivitypeor();
		c.setDataFromJSON(creteria);
		QuerySsystemprocessinstanceactivitypeor dao = new QuerySsystemprocessinstanceactivitypeor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSsystemprocessinstanceactivitypeor.ALL_CAPTIONS);
			for(BaseSsystemprocessinstanceactivitypeor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


