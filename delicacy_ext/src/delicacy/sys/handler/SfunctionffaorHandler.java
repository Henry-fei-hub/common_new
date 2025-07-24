package delicacy.sys.handler;

import org.apache.log4j.Logger;
import delicacy.sys.bean.BaseSfunctionffaor;
import delicacy.sys.bean.ConditionSfunctionffaor;
import delicacy.sys.query.QuerySfunctionffaor;
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

public class SfunctionffaorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SfunctionffaorHandler.class);

	public static BaseCollection<BaseSfunctionffaor> executeQuerySfunctionffaor(ConditionSfunctionffaor c, KeyValuePair[] replacements ) throws Exception {
		QuerySfunctionffaor dao = new QuerySfunctionffaor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSfunctionffaor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSfunctionffaor c = new ConditionSfunctionffaor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSfunctionffaor> result = executeQuerySfunctionffaor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSfunctionffaor> result;
		ConditionSfunctionffaor c = new ConditionSfunctionffaor();
		c.setDataFromJSON(creteria);
		QuerySfunctionffaor dao = new QuerySfunctionffaor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSfunctionffaor.ALL_CAPTIONS);
			for(BaseSfunctionffaor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


