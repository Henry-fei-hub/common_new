package delicacy.system.dao;

import java.util.*;
import delicacy.system.bean.BaseDepartmentRoleWithR;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.RoleFunction;

public class DepartmentRoleWithR extends delicacy.system.dao.DepartmentRole
{


	public DepartmentRoleWithR() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseRoleFunction> getDetailRoleFunction() {
		return this.__detail_rolefunction;
	}

	public void setDetailRoleFunction( List<BaseRoleFunction> value ) {
		this.__detail_rolefunction = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// 实例化  DAO
		RoleFunction rolefunction = new RoleFunction();
		// 装入数据库中原来明细数据
		rolefunction.setConditionDepartmentId("=", getDepartmentId());
		rolefunction.setConditionRoleId("=", getRoleId());
		List<BaseRoleFunction> originalrolefunction = rolefunction.conditionalLoad();
		// 如果当前明细不为空
		if(getDetailRoleFunction() != null) {
			// 需要删除的明细数据
			List<BaseRoleFunction> needToDeleteRoleFunction = new ArrayList<>();
			// 需要增加的明细数据
			List<BaseRoleFunction> needToAddRoleFunction = new ArrayList<>();
			// 找到在原明细中存在的数据并修改该数据，如果在原数据中没有则加到需要添加的列表中
			for(BaseRoleFunction __rolefunction : getDetailRoleFunction() ) {
				// 赋值关联字段
				__rolefunction.setDepartmentId(getDepartmentId());
				__rolefunction.setRoleId(getRoleId());
				// 设置找到标志
				boolean foundRoleFunction = false;
				// 在原明细中找
				for(BaseRoleFunction __originalrolefunction : originalrolefunction) {
					// 如果两个对象主键相同
					if(Objects.equals(__rolefunction.getFunctionId(), __originalrolefunction.getFunctionId())) {
						// 设置找到标志
						foundRoleFunction = true;
						// 修改该条数据
						// 清空DAO中当前数据
						rolefunction.clearCurrentData();
						// 把原明细数据装入DAO
						rolefunction.setDataFromBase(__originalrolefunction);
						// 清空DAO数据修改标志, 就像数据刚从数据库中装入一样
						rolefunction.clearModifiedFlags();
						// 把新的数据装入
						rolefunction.setDataFromBase(__rolefunction);
						// 执行修改
						rolefunction.update();
						break;
					}
				}
				// 如果没找到，则加入需要增加的列表中
				if(!foundRoleFunction) needToAddRoleFunction.add(__rolefunction);
			}
			// 找到那些在原明细中有，但当前明细中没有的条目
			for(BaseRoleFunction __originalrolefunction : originalrolefunction) {
				boolean foundRoleFunction = false;
				for(BaseRoleFunction __rolefunction : getDetailRoleFunction() ) {
					if(Objects.equals(__rolefunction.getFunctionId(), __originalrolefunction.getFunctionId())) {
						foundRoleFunction = true;
						break;
				}
				}
				if(!foundRoleFunction) needToDeleteRoleFunction.add(__originalrolefunction);
			}
			// 处理需要增加的明细数据
			for(BaseRoleFunction __rolefunction : needToAddRoleFunction ) {
				rolefunction.setPrimaryKeyFromBase(__rolefunction);
				// 如果该条数据主键为空，则插入数据, 注意这种处理方式只对子表主键为自增的有效
				// 如果子表的主键不是自增类型，则需要考虑如何生成主键, 并修改下面的代码
				if(rolefunction.isPrimaryKeyNull()) { rolefunction.clearCurrentData(); rolefunction.setDataFromBase(__rolefunction); rolefunction.save(); }
				// 否则从数据中查询该数据，找到就修改，否则插入
				else { if(rolefunction.load()) { rolefunction.setDataFromBase(__rolefunction); rolefunction.update(); } else { rolefunction.clearCurrentData(); rolefunction.setDataFromBase(__rolefunction); rolefunction.save(); } }
			}
			// 处理需要删除的明细数据
			for(BaseRoleFunction __rolefunction : needToDeleteRoleFunction ) {
				deleteFromRoleFunction(__rolefunction, rolefunction);
			}
		} else {
			// 如果当前明细为空，说明所有原有的明细都需要删除
			for(BaseRoleFunction __rolefunction : originalrolefunction ) {
				deleteFromRoleFunction(__rolefunction, rolefunction);
			}
		}
	}

	private void deleteFromRoleFunction(BaseRoleFunction bean, RoleFunction dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setDepartmentId(null);
		dao.setRoleId(null);
		if(__detail_delete_rolefunction) dao.delete(); else dao.update();
	}

	// 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
	private void deleteDetailTables()  throws java.sql.SQLException {
		RoleFunction rolefunction = new RoleFunction();
		rolefunction.setConditionDepartmentId("=", getDepartmentId());
		rolefunction.setConditionRoleId("=", getRoleId());
		rolefunction.conditionalDelete();
	}

	// 从数据中按主键查询数据，可以包含明细数据
	public boolean load(boolean loadDetail) throws java.sql.SQLException {
		if(!super.load()) return false;
		if(loadDetail){
			RoleFunction rolefunction = new RoleFunction();
			rolefunction.setConditionDepartmentId("=", getDepartmentId());
			rolefunction.setConditionRoleId("=", getRoleId());
			setDetailRoleFunction(rolefunction.conditionalLoad());
		}
		return true;
	}

	public List<BaseDepartmentRoleWithR> conditionalLoadExt(String addtional) throws java.sql.SQLException {
		List<BaseDepartmentRoleWithR> result = BaseDepartmentRoleWithR.getBeanList(conditionalLoad(addtional));
		return result;
	}

	public BaseDepartmentRoleWithR generateBaseExt() {
		BaseDepartmentRoleWithR ____result = new BaseDepartmentRoleWithR();
		setDataToBase(____result);
		return ____result;
	}

	public void setDataFromBase(BaseDepartmentRoleWithR __base) {
		super.setDataFromBase(__base);
		setDetailRoleFunction(__base.getDetailRoleFunction());
	}

	public void setDataToBase(BaseDepartmentRoleWithR __base) {
		super.setDataToBase(__base);
		__base.setDetailRoleFunction(getDetailRoleFunction());
	}

	@Override
	public int save() throws java.sql.SQLException {

		if(super.save() == 0) return 0;
		deleteAndSaveDetailTables();
		return 1;
	}

	@Override
	public int update() throws java.sql.SQLException {

		super.update();
		deleteAndSaveDetailTables();
		return 1;
	}

	@Override
	public int conditionalUpdate() throws java.sql.SQLException {

		super.conditionalUpdate();
		deleteAndSaveDetailTables();
		return 1;
	}

	@Override
	public int delete() throws java.sql.SQLException {
		super.delete();
		deleteDetailTables();
		return 1;
	}

	@Override
	public int conditionalDelete() throws java.sql.SQLException {
		super.conditionalDelete();
		deleteDetailTables();
		return 1;
	}

	public boolean isDeleteDetailRoleFunction() {
		return this.__detail_delete_rolefunction;
	}

	public void setDeleteDetailRoleFunction( boolean value ) {
		this.__detail_delete_rolefunction = value;
	}

	protected boolean __detail_delete_rolefunction = true; 
	protected List<BaseRoleFunction> __detail_rolefunction ; 
}
