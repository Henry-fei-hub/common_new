package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseMsystemprocesssor;
import delicacy.system.bean.ConditionMsystemprocesssor;
import delicacy.system.query.QueryMsystemprocesssor;
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

public class MsystemprocesssorHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(MsystemprocesssorHandler.class);

	public static BaseCollection<BaseMsystemprocesssor> executeQueryMsystemprocesssor(ConditionMsystemprocesssor c, KeyValuePair[] replacements ) throws Exception {
		QueryMsystemprocesssor dao = new QueryMsystemprocesssor();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseMsystemprocesssor> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionMsystemprocesssor c = new ConditionMsystemprocesssor();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseMsystemprocesssor> result = executeQueryMsystemprocesssor(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseMsystemprocesssor> result;
		ConditionMsystemprocesssor c = new ConditionMsystemprocesssor();
		c.setDataFromJSON(creteria);
		QueryMsystemprocesssor dao = new QueryMsystemprocesssor();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseMsystemprocesssor.ALL_CAPTIONS);
			for(BaseMsystemprocesssor b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


