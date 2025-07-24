package delicacy.sys.handler;

import org.apache.log4j.Logger;
import delicacy.sys.bean.BaseSapplicationaaor;
import delicacy.sys.bean.ConditionSapplicationaaor;
import delicacy.sys.query.QuerySapplicationaaor;
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

public class SapplicationaaorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SapplicationaaorHandler.class);

	public static BaseCollection<BaseSapplicationaaor> executeQuerySapplicationaaor(ConditionSapplicationaaor c, KeyValuePair[] replacements ) throws Exception {
		QuerySapplicationaaor dao = new QuerySapplicationaaor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSapplicationaaor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSapplicationaaor c = new ConditionSapplicationaaor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSapplicationaaor> result = executeQuerySapplicationaaor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSapplicationaaor> result;
		ConditionSapplicationaaor c = new ConditionSapplicationaaor();
		c.setDataFromJSON(creteria);
		QuerySapplicationaaor dao = new QuerySapplicationaaor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSapplicationaaor.ALL_CAPTIONS);
			for(BaseSapplicationaaor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


