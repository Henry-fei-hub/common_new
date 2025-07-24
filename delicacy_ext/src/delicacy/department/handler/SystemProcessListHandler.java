package delicacy.department.handler;

import org.apache.log4j.Logger;
import delicacy.department.bean.BaseSystemProcessList;
import delicacy.department.bean.ConditionSystemProcessList;
import delicacy.department.query.QuerySystemProcessList;
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

public class SystemProcessListHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SystemProcessListHandler.class);

	public static BaseCollection<BaseSystemProcessList> executeQuerySystemProcessList(ConditionSystemProcessList c, KeyValuePair[] replacements ) throws Exception {
		QuerySystemProcessList dao = new QuerySystemProcessList();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSystemProcessList> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSystemProcessList c = new ConditionSystemProcessList();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSystemProcessList> result = executeQuerySystemProcessList(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSystemProcessList> result;
		ConditionSystemProcessList c = new ConditionSystemProcessList();
		c.setDataFromJSON(creteria);
		QuerySystemProcessList dao = new QuerySystemProcessList();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSystemProcessList.ALL_CAPTIONS);
			for(BaseSystemProcessList b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


