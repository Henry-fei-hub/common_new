package delicacy.system.handler;

import org.apache.log4j.Logger;
import delicacy.system.bean.BaseSemployeeRoleInfo;
import delicacy.system.bean.ConditionSemployeeRoleInfo;
import delicacy.system.query.QuerySemployeeRoleInfo;
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

public class SemployeeRoleInfoHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(SemployeeRoleInfoHandler.class);

	public static BaseCollection<BaseSemployeeRoleInfo> executeQuerySemployeeRoleInfo(ConditionSemployeeRoleInfo c, KeyValuePair[] replacements ) throws Exception {
		QuerySemployeeRoleInfo dao = new QuerySemployeeRoleInfo();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseSemployeeRoleInfo> result = dao.executeQuery( c.getKeyValues(), c) ;
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionSemployeeRoleInfo c = new ConditionSemployeeRoleInfo();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseSemployeeRoleInfo> result = executeQuerySemployeeRoleInfo(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseSemployeeRoleInfo> result;
		ConditionSemployeeRoleInfo c = new ConditionSemployeeRoleInfo();
		c.setDataFromJSON(creteria);
		QuerySemployeeRoleInfo dao = new QuerySemployeeRoleInfo();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseSemployeeRoleInfo.ALL_CAPTIONS);
			for(BaseSemployeeRoleInfo b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


