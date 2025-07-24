package delicacy.system.query;

import org.apache.log4j.Logger;
import delicacy.common.KeyValuePair;
import delicacy.common.AbstractQuery;
import delicacy.common.GenericBase;
import delicacy.common.BaseCollection;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseSemployeeRoleInfo;
import delicacy.system.bean.ConditionSemployeeRoleInfo;

public class QuerySemployeeRoleInfo extends AbstractQuery<BaseSemployeeRoleInfo, ConditionSemployeeRoleInfo>
{

	private static final Logger __logger = Logger.getLogger(QuerySemployeeRoleInfo.class);

	public QuerySemployeeRoleInfo() throws java.sql.SQLException 
	{
		setParameterNumber(2);
		setConnection(ThreadConnection.getConnection());
		setOrderByFields("r.role_id");
	}

	@Override
	public BaseCollection<BaseSemployeeRoleInfo> executeQuery( KeyValuePair[] replacements, ConditionSemployeeRoleInfo condition ) throws java.sql.SQLException {
		return executeQuery(replacements, 
				condition.getApplicationId(), 
				condition.getRoleName()
			);
	}

	@Override
	public BaseSemployeeRoleInfo generateBase(Object[] __data) throws java.sql.SQLException {
		BaseSemployeeRoleInfo __base = new BaseSemployeeRoleInfo();
		int count = 0;
		Object val;
		if((val = __data[count++]) != null) __base.setRoleId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setRoleName(GenericBase.__getString(val));
		if((val = __data[count++]) != null) __base.setApplicationId(GenericBase.__getInt(val));
		if((val = __data[count++]) != null) __base.setRoleType(GenericBase.__getInt(val));
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
		if(args[0] != null) __statement.setInt(count++, GenericBase.__getInt(args[0]));
		if(args[1] != null) __statement.setString(count++, GenericBase.__getString(args[1]));
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

	private final static String __SQLText = "select r.role_id, r.role_name, r.application_id, r.role_type from roles r where r.application_id = ? and r.role_name like ? and r.enabled = true" ;
	private final static String RESULTSETFIELDLIST = "role_id,role_name,application_id,role_type";
	private final static String[] fieldNames = { "application_id", "role_name"};
}
