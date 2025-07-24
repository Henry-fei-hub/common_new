package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseMemployee;
import delicacy.system.bean.ConditionMemployee;
import delicacy.system.query.QueryMemployee;
import delicacy.common.KeyValuePair;
import delicacy.common.GenericQuery;
import delicacy.common.GenericDownloadProcessor;
import delicacy.common.GenericBase;
import java.io.File;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import delicacy.system.bean.BaseEmployeeRole;
import delicacy.system.dao.EmployeeRole;
import delicacy.common.BaseCollection;

public class MemployeeHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(MemployeeHandler.class);

	public static BaseCollection<BaseMemployee> executeQueryMemployee(ConditionMemployee c, KeyValuePair[] replacements ) throws Exception {
		QueryMemployee dao = new QueryMemployee();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseMemployee> result = dao.executeQuery( c.getKeyValues(), c) ;
		if(c.isIncludeDetail() && result.getRecordNumber() > 0){
			EmployeeRole daoEmployeeRole = new EmployeeRole();
			for(BaseMemployee bean : result.getCollections()){
				daoEmployeeRole.setConditionEmployeeId("=", bean.getEmployeeId());
				bean.setDetailEmployeeRole(daoEmployeeRole.conditionalLoad(null));
			}
		}
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionMemployee c = new ConditionMemployee();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseMemployee> result = executeQueryMemployee(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseMemployee> result;
		ConditionMemployee c = new ConditionMemployee();
		c.setDataFromJSON(creteria);
		QueryMemployee dao = new QueryMemployee();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseMemployee.ALL_CAPTIONS);
			for(BaseMemployee b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


