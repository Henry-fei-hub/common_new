package delicacy.system.handler;

import delicacy.system.bean.BaseMemployeeNew;
import delicacy.system.bean.ConditionMemployeeNew;
import delicacy.system.query.QueryMemployeeNew;
import org.apache.log4j.Logger;
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

public class MemployeeNewHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(MemployeeNewHandler.class);

	public static BaseCollection<BaseMemployeeNew> executeQueryMemployeeNew(ConditionMemployeeNew c, KeyValuePair[] replacements ) throws Exception {
		QueryMemployeeNew dao = new QueryMemployeeNew();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseMemployeeNew> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionMemployeeNew c = new ConditionMemployeeNew();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseMemployeeNew> result = executeQueryMemployeeNew(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseMemployeeNew> result;
		ConditionMemployeeNew c = new ConditionMemployeeNew();
		c.setDataFromJSON(creteria);
		QueryMemployeeNew dao = new QueryMemployeeNew();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseMemployeeNew.ALL_CAPTIONS);
			for(BaseMemployeeNew b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


