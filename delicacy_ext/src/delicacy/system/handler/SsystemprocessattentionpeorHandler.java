package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSsystemprocessattentionpeor;
import delicacy.system.bean.ConditionSsystemprocessattentionpeor;
import delicacy.system.query.QuerySsystemprocessattentionpeor;
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

public class SsystemprocessattentionpeorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SsystemprocessattentionpeorHandler.class);

	public static BaseCollection<BaseSsystemprocessattentionpeor> executeQuerySsystemprocessattentionpeor(ConditionSsystemprocessattentionpeor c, KeyValuePair[] replacements ) throws Exception {
		QuerySsystemprocessattentionpeor dao = new QuerySsystemprocessattentionpeor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSsystemprocessattentionpeor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSsystemprocessattentionpeor c = new ConditionSsystemprocessattentionpeor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSsystemprocessattentionpeor> result = executeQuerySsystemprocessattentionpeor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSsystemprocessattentionpeor> result;
		ConditionSsystemprocessattentionpeor c = new ConditionSsystemprocessattentionpeor();
		c.setDataFromJSON(creteria);
		QuerySsystemprocessattentionpeor dao = new QuerySsystemprocessattentionpeor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSsystemprocessattentionpeor.ALL_CAPTIONS);
			for(BaseSsystemprocessattentionpeor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


