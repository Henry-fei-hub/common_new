package delicacy.department.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.department.bean.BaseDepartmentList;
import delicacy.department.bean.ConditionDepartmentList;

public class QueryDepartmentList extends AbstractQuery<BaseDepartmentList, ConditionDepartmentList>
{

	private static final Logger __logger = Logger.getLogger(QueryDepartmentList.class);

	public QueryDepartmentList() throws java.sql.SQLException 
	{
		setParameterNumber(3);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("d.department_id");
	}

	@Override
	public BaseCollection<BaseDepartmentList> executeQuery( KeyValuePair[] replacements, ConditionDepartmentList condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getManagerName(), 
				condition.getManagerId(), 
				condition.getDepartmentName()
			);
	}

	@Override
	public BaseDepartmentList generateBase(Object[] __data) throws java.sql.SQLException {
		BaseDepartmentList __base = new BaseDepartmentList();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setDepartmentName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setAbbreviation(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setManagerId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setManagerName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setParentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setEnabled(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setOriginalId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setPlateId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setIsHeadcount(GenericBase.__getBoolean(val));
		if((val = __data[count++]) != null) __base.setDepartmentType(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setWeixinDepartmentId(GenericBase.__getLong(val));
		if((val = __data[count++]) != null) __base.setEmailDepartmentId(GenericBase.__getLong(val));
		if((val = __data[count++]) != null) __base.setEcmcDepartmentId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setDeleteFlag(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setIsEnable(GenericBase.__getBoolean(val));
		return __base;
	}

	@Override
	public int setStatementParameters(int count, java.lang.Object ... args) throws java.sql.SQLException {

		int index = 0;
		for(int ii = 0; ii < args.length; ii++){
			if(args[ii] == null) continue;
			index++;
			__logger.info(String.format("%1$s = [%2$s]", fieldNames[ii], args[ii]));
		}
		if( index > 0 ) __logger.info("=================================================");
		if(args[0] != null) __statement.setString(count++, GenericBase.__getString(args[0]));
		if(args[1] != null) __statement.setInt(count++, GenericBase.__getInt(args[1]));
		if(args[2] != null) __statement.setString(count++, GenericBase.__getString(args[2]));
		return count;
	}

	@Override
	public String getSQLText() {
		__logger.info(__SQLText);
		return __SQLText;
	}

	@Override
	public String getFieldList(){
		return RESULTSETFIELDLIST;
	}

	private final static String __SQLText = "select d.department_id, d.department_name, d.abbreviation, d.manager_id, d.manager_name, d.parent_id, d.enabled, d.original_id, d.plate_id, d.is_headcount, d.department_type, d.weixin_department_id, d.email_department_id, d.ecmc_department_id, d.delete_flag, d.is_enable from departments d where d.manager_name like ? and d.manager_id = ? and d.department_name like ?" ;
	private final static String RESULTSETFIELDLIST = "department_id,department_name,abbreviation,manager_id,manager_name,parent_id,enabled,original_id,plate_id,is_headcount,department_type,weixin_department_id,email_department_id,ecmc_department_id,delete_flag,is_enable";
	private final static String[] fieldNames = { "manager_name", "manager_id", "department_name"};
}
