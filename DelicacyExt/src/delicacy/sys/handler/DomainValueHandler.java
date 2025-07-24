package delicacy.sys.handler;

import java.io.StringReader;
import java.util.Map;

import org.apache.log4j.Logger;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericQuery;
import delicacy.common.KeyValuePair;
import delicacy.connection.ThreadConnection;
import delicacy.date.util.TimeSpanCalculator;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseDomainValue;
import delicacy.system.bean.ConditionDomainValue;
import delicacy.system.query.QueryDomainValue;

public class DomainValueHandler implements GenericQuery {

    private static final Logger __logger = Logger.getLogger(DomainValueHandler.class);

    public static BaseCollection<BaseDomainValue> executeQueryDomainValue(ConditionDomainValue condition, KeyValuePair[] replacements) {
        __logger.info(String.format("Current Page No. [%1$d]", condition.getCurrentPage()));
        try {
            TimeSpanCalculator tsc = new TimeSpanCalculator();
            tsc.recordTime();
            BaseCollection<BaseDomainValue> res = executeDomainValue(condition, replacements,null);
            tsc.recordTime();
            __logger.info(String.format("Execute Query DomainValue time used : [%1$d]", tsc.getLastTime()));
            return res;
        } catch (Exception ex) {
            __logger.info(ex);
            return null;
        }
    }

    @Override
    public String find(String condition) throws Exception {
    	@SuppressWarnings("rawtypes")
		JSON parser = new JSON(new StringReader(condition));
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) parser.parse(new BasicHandler());
		//获取组织代码
		int organizationId = BaseHelpUtils.getIntValue(params.get("organizationId"));
        ConditionDomainValue c = new ConditionDomainValue();
        c.setDataFromJSON(condition);
        BaseCollection<BaseDomainValue> res = executeDomainValue(c, null,organizationId);
        return res.toJSON(null);
    }

    public static BaseCollection<BaseDomainValue> executeDomainValue(ConditionDomainValue condition, KeyValuePair[] replacements,Integer organizationId) throws Exception {
        QueryDomainValue dao = new QueryDomainValue();
        dao.setCurrentPage(0);
        dao.setPageLines(0);
        BaseCollection<BaseDomainValue> res = dao.executeQuery(replacements, condition);
        if (res == null || res.getCollections() == null) {
            return null;
        }
        java.util.List<BaseDomainValue> values;
        try (java.sql.Statement stmt = ThreadConnection.getConnection().getConnection().createStatement();) {
            values = new java.util.ArrayList<>();
            for (BaseDomainValue bean : res.getCollections()) {
                boolean hasCondition = bean.getConditionColumn() != null && bean.getConditionColumn().trim().length() > 0;
                boolean hasAdditional = bean.getAdditionalCondition() != null && bean.getAdditionalCondition().trim().length() > 0;
                boolean isBasicData = bean.getIsBasicData();
                StringBuilder sb = new StringBuilder();
                sb.append("select ");
                sb.append(bean.getKeyColumn());
                sb.append(",");
                sb.append(bean.getValueColumn());
                if (hasCondition) {
                    sb.append(",");
                    sb.append(bean.getConditionColumn());
                }
                sb.append(" from ");
                sb.append(bean.getTableName());
                if (hasAdditional) {
                    sb.append(" where ");
                    sb.append(bean.getAdditionalCondition());
                    if(!isBasicData){
                		sb.append(" and (organization_id = ").append(organizationId).append(" or organization_id is null) ");
                	}
                }else{
                	if(!isBasicData){
                		sb.append(" where (organization_id = ").append(organizationId).append(" or organization_id is null) ");
                	}
                }
                String sqlString = sb.toString();
                __logger.info(sqlString);
                try (java.sql.ResultSet rs = stmt.executeQuery(sqlString)) {
                	while (rs.next()) {
                		BaseDomainValue __bean = new BaseDomainValue();
                		__bean.setTableName(bean.getSelectId());
                		__bean.setKeyColumn(rs.getString(1));
                		__bean.setValueColumn(rs.getString(2));
                		if (hasCondition) {
                			__bean.setConditionColumn(rs.getString(3));
                		}
                		values.add(__bean);
                	}
                }
            }
        }
        res.setCollections(values);
        return res;
    }

}
