package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSsystemprocesspooledtaskpeor;
import delicacy.system.bean.ConditionSsystemprocesspooledtaskpeor;
import delicacy.system.query.QuerySsystemprocesspooledtaskpeor;
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

public class SsystemprocesspooledtaskpeorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SsystemprocesspooledtaskpeorHandler.class);

	public static BaseCollection<BaseSsystemprocesspooledtaskpeor> executeQuerySsystemprocesspooledtaskpeor(ConditionSsystemprocesspooledtaskpeor c, KeyValuePair[] replacements ) throws Exception {
		QuerySsystemprocesspooledtaskpeor dao = new QuerySsystemprocesspooledtaskpeor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSsystemprocesspooledtaskpeor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSsystemprocesspooledtaskpeor c = new ConditionSsystemprocesspooledtaskpeor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSsystemprocesspooledtaskpeor> result = executeQuerySsystemprocesspooledtaskpeor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSsystemprocesspooledtaskpeor> result;
		ConditionSsystemprocesspooledtaskpeor c = new ConditionSsystemprocesspooledtaskpeor();
		c.setDataFromJSON(creteria);
		QuerySsystemprocesspooledtaskpeor dao = new QuerySsystemprocesspooledtaskpeor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSsystemprocesspooledtaskpeor.ALL_CAPTIONS);
			for(BaseSsystemprocesspooledtaskpeor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


