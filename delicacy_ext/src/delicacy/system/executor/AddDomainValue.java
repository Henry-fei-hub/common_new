package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.GenericProcessor;
import delicacy.connection.ThreadConnection;
import delicacy.system.bean.BaseDomainValue;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import delicacy.system.bean.BaseColumnDomain;
import delicacy.system.bean.BaseSystemDictionary;
import delicacy.system.dao.ColumnDomain;
import delicacy.system.dao.SystemDictionary;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class AddDomainValue implements GenericProcessor {

	private static final Logger __logger = Logger.getLogger(DomainValueHandler.class);

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		String res = null;
		BaseColumnDomain bcd = new BaseColumnDomain();
		bcd.setDataFromJSON(creteria);
		ColumnDomain dao = new ColumnDomain();
		dao.setPrimaryKeyFromBase(bcd);
		if (!dao.load()) {
			throw new SQLException("Could not found the select ID");
		}
		if (!dao.getLoadOnStartup()) {
			throw new SQLException("不支持这种类型的自动添加");
		}
		if (dao.getTableName().equals("system_dictionary")) {
			SystemDictionary sdao = new SystemDictionary();
			List<BaseSystemDictionary> lsd = sdao.conditionalLoad(dao.getAdditionalCondition());
			if (lsd.isEmpty()) {
				throw new SQLException(String.format("Could not found the select ID [%1$s] [%2$s] in SystemDictionary",
						bcd.getSelectId(), bcd.getAdditionalCondition()
				));
			}
			int maxID = -1;
			for (BaseSystemDictionary bsd : lsd) {
				if (bsd.getDicTypeValueId() > maxID) {
					maxID = bsd.getDicTypeValueId();
				}
			}
			maxID++;
			sdao.setDataFromBase(lsd.get(0));
			sdao.setDicTypeValueId(maxID);
			sdao.setDicTypeValueName(bcd.getValueColumn());
			sdao.save();
			BaseSystemDictionary nsd = sdao.generateBase();
			List<BaseColumnDomain> rcd = new ArrayList<>();
			for (BaseSystemDictionary bsd : lsd) {
				BaseColumnDomain cd = new BaseColumnDomain();
				cd.setSelectId(bcd.getSelectId());
				cd.setTableName(bcd.getSelectId());
				cd.setKeyColumn(String.valueOf(bsd.getDicTypeValueId()));
				cd.setValueColumn(bsd.getDicTypeValueName());
				rcd.add(cd);
			}
			BaseColumnDomain cd = new BaseColumnDomain();
			cd.setTableName(bcd.getSelectId());
			cd.setKeyColumn(String.valueOf(nsd.getDicTypeValueId()));
			cd.setValueColumn(nsd.getDicTypeValueName());
			rcd.add(cd);
			BaseCollection<BaseColumnDomain> cols = new BaseCollection<>();
			cols.setCollections(rcd);
			res = cols.toJSON(null);
		} else {
			BaseCollection<BaseDomainValue> cols = new BaseCollection<>();
			cols.setCollections(getDomainValue(dao.generateBase(), bcd));
			res = cols.toJSON(null);
		}
		return res;
	}

	public static List<BaseDomainValue> getDomainValue(BaseColumnDomain bcd, BaseColumnDomain ncd) throws Exception {
		java.util.List<BaseDomainValue> values;
		StringBuilder sb;
		String sqlString;
		try (java.sql.Statement stmt = ThreadConnection.getConnection().getConnection().createStatement()) {
			values = new java.util.ArrayList<>();
			boolean hasCondition = bcd.getConditionColumn() != null && bcd.getConditionColumn().trim().length() > 0;
			boolean hasAdditional = bcd.getAdditionalCondition() != null && bcd.getAdditionalCondition().trim().length() > 0;
			sb = new StringBuilder();
			sb.append("select ");
			sb.append(bcd.getKeyColumn());
			sb.append(",");
			sb.append(bcd.getValueColumn());
			if (hasCondition) {
				sb.append(",");
				sb.append(bcd.getConditionColumn());
			}
			sb.append(" from ");
			sb.append(bcd.getTableName());
			if (hasAdditional) {
				sb.append(" where ");
				sb.append(bcd.getAdditionalCondition());
			}
			sqlString = sb.toString();
			__logger.info(sqlString);
			try (java.sql.ResultSet rs = stmt.executeQuery(sqlString)) {
				while (rs.next()) {
					BaseDomainValue __bean = new BaseDomainValue();
					__bean.setTableName(bcd.getSelectId());
					__bean.setKeyColumn(rs.getString(1));
					__bean.setValueColumn(rs.getString(2));
					if (hasCondition) {
						__bean.setConditionColumn(rs.getString(3));
					}
					values.add(__bean);
				}
			}
		}
		sb.setLength(0);
		sb.append("insert into ");
		sb.append(bcd.getTableName());
		sb.append(" ( ");
		sb.append(bcd.getValueColumn());
		sb.append(" ) values ( ? )");
		sqlString = sb.toString();
		__logger.info(sqlString);
		int maxID = 0;
		try (PreparedStatement ps = ThreadConnection.getConnection().getConnection().prepareStatement(sqlString, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, ncd.getValueColumn());
			ps.executeUpdate();
			try (java.sql.ResultSet results = ps.getGeneratedKeys()) {
				while (results.next()) {
					maxID = results.getInt(1);
				}
			}
		}
		BaseDomainValue __bean = new BaseDomainValue();
		__bean.setTableName(bcd.getSelectId());
		__bean.setKeyColumn(String.valueOf(maxID));
		__bean.setValueColumn(ncd.getValueColumn());
		values.add(__bean);
		return values;
	}

}
