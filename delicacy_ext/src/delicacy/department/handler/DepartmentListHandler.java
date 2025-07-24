package delicacy.department.handler;

import java.io.File;
import java.io.PrintStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import delicacy.common.BaseCollection;
import delicacy.common.GenericBase;
import delicacy.common.GenericDownloadProcessor;
import delicacy.common.GenericQuery;
import delicacy.common.KeyValuePair;
import delicacy.department.bean.BaseDepartmentList;
import delicacy.department.bean.ConditionDepartmentList;
import delicacy.department.query.QueryDepartmentList;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.SystemProcessDepartment;

public class DepartmentListHandler implements GenericQuery,  GenericDownloadProcessor {

	private static final Logger __logger = Logger.getLogger(DepartmentListHandler.class);

	public static BaseCollection<BaseDepartmentList> executeQueryDepartmentList(ConditionDepartmentList c, KeyValuePair[] replacements ) throws Exception {
		QueryDepartmentList dao = new QueryDepartmentList();
		dao.setCurrentPage(c.getCurrentPage());
		dao.setPageLines(c.getPageLines());
		BaseCollection<BaseDepartmentList> result = dao.executeQuery( c.getKeyValues(), c) ;
		if(c.isIncludeDetail() && result.getRecordNumber() > 0){
			SystemProcessDepartment daoSystemProcessDepartment = new SystemProcessDepartment();
			DepartmentRole daoDepartmentRole = new DepartmentRole();
			for(BaseDepartmentList bean : result.getCollections()){
				daoSystemProcessDepartment.setConditionDepartmentId("=", bean.getDepartmentId());
				bean.setDetailSystemProcessDepartment(daoSystemProcessDepartment.conditionalLoad(null));
				daoDepartmentRole.setConditionDepartmentId("=", bean.getDepartmentId());
				bean.setDetailDepartmentRole(daoDepartmentRole.conditionalLoad(null));
			}
		}
		return result;
	}

	@Override
	public String find(String creteria) throws Exception {
		ConditionDepartmentList c = new ConditionDepartmentList();
		c.setDataFromJSON(creteria);
		BaseCollection<BaseDepartmentList> result = executeQueryDepartmentList(c, c.getKeyValues());
		return result.toJSON(null);
	}

	@Override
	public void execute(String creteria, File downFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseCollection<BaseDepartmentList> result;
		ConditionDepartmentList c = new ConditionDepartmentList();
		c.setDataFromJSON(creteria);
		QueryDepartmentList dao = new QueryDepartmentList();
		dao.setCurrentPage(1);
		dao.setPageLines(1);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		if(result.getTotalLines() > GenericBase.MAX_EXPORT_LINES){
			throw new SQLException(String.format("Too many data to export : %1$d", result.getTotalLines()));
		}
		dao.setCurrentPage(0);
		result = dao.executeQuery( c.getKeyValues(), c) ;
		try(PrintStream ps = new PrintStream(downFile, "GBK")){
			ps.println(BaseDepartmentList.ALL_CAPTIONS);
			for(BaseDepartmentList b : result.getCollections()){
				ps.println(b.toCSVString());
			}
		}
	}

	@Override
	public String getDownloadFileExtension() {
		return "csv";
	}

}


