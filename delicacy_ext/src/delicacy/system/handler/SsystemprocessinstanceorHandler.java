package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSsystemprocessinstanceor;
import delicacy.system.bean.ConditionSsystemprocessinstanceor;
import delicacy.system.query.QuerySsystemprocessinstanceor;
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

public class SsystemprocessinstanceorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SsystemprocessinstanceorHandler.class);

	public static BaseCollection<BaseSsystemprocessinstanceor> executeQuerySsystemprocessinstanceor(ConditionSsystemprocessinstanceor c, KeyValuePair[] replacements ) throws Exception {
		QuerySsystemprocessinstanceor dao = new QuerySsystemprocessinstanceor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSsystemprocessinstanceor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSsystemprocessinstanceor c = new ConditionSsystemprocessinstanceor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSsystemprocessinstanceor> result = executeQuerySsystemprocessinstanceor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSsystemprocessinstanceor> result;
		ConditionSsystemprocessinstanceor c = new ConditionSsystemprocessinstanceor();
		c.setDataFromJSON(creteria);
		QuerySsystemprocessinstanceor dao = new QuerySsystemprocessinstanceor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSsystemprocessinstanceor.ALL_CAPTIONS);
			for(BaseSsystemprocessinstanceor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


