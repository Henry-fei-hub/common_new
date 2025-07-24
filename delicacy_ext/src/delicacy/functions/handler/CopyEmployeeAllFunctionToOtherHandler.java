package delicacy.functions.handler;

import org.apache.log4j.Logger;
import delicacy.functions.bean.BaseCopyEmployeeAllFunctionToOther;
import delicacy.functions.bean.ConditionCopyEmployeeAllFunctionToOther;
import delicacy.functions.query.QueryCopyEmployeeAllFunctionToOther;
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

public class CopyEmployeeAllFunctionToOtherHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(CopyEmployeeAllFunctionToOtherHandler.class);

	public static BaseCollection<BaseCopyEmployeeAllFunctionToOther> executeQueryCopyEmployeeAllFunctionToOther(ConditionCopyEmployeeAllFunctionToOther c, KeyValuePair[] replacements ) throws Exception {
		QueryCopyEmployeeAllFunctionToOther dao = new QueryCopyEmployeeAllFunctionToOther();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseCopyEmployeeAllFunctionToOther> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionCopyEmployeeAllFunctionToOther c = new ConditionCopyEmployeeAllFunctionToOther();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseCopyEmployeeAllFunctionToOther> result = executeQueryCopyEmployeeAllFunctionToOther(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseCopyEmployeeAllFunctionToOther> result;
		ConditionCopyEmployeeAllFunctionToOther c = new ConditionCopyEmployeeAllFunctionToOther();
		c.setDataFromJSON(creteria);
		QueryCopyEmployeeAllFunctionToOther dao = new QueryCopyEmployeeAllFunctionToOther();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseCopyEmployeeAllFunctionToOther.ALL_CAPTIONS);
			for(BaseCopyEmployeeAllFunctionToOther b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


