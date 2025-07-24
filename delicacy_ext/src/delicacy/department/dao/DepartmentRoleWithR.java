package delicacy.department.dao;

import java.util.ArrayList;
import java.util.List;

import delicacy.department.bean.BaseDepartmentRoleWithR;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.RoleFunction;

public class DepartmentRoleWithR extends DepartmentRole
{


	public DepartmentRoleWithR() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseRoleFunction> getDetailRoleFunction() {
		return this.__detail_rolefunction;
	}

	public void setDetailRoleFunction( List<BaseRoleFunction> value ) {
		this.__detail_rolefunction = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// instantiate  DAO
		RoleFunction rolefunction = new RoleFunction();
		// load the original detail data.
		rolefunction.setConditionDepartmentId("=", getDepartmentId());
		rolefunction.setConditionRoleId("=", getRoleId());
		List<BaseRoleFunction> originalrolefunction = rolefunction.conditionalLoad();
		// if current detail is not empty.
		if(getDetailRoleFunction() != null) {
			// need to delete data
			List<BaseRoleFunction> needToDeleteRoleFunction = new ArrayList<>();
			// need to add data
			List<BaseRoleFunction> needToAddRoleFunction = new ArrayList<>();
			// find that records need to be modified.
			for(BaseRoleFunction __rolefunction : getDetailRoleFunction() ) {
				// set that related fields.
				__rolefunction.setDepartmentId(getDepartmentId());
				__rolefunction.setRoleId(getRoleId());
				// if found then set found flag.
				boolean foundRoleFunction = false;
				// search the original detail list.
				for(BaseRoleFunction __originalrolefunction : originalrolefunction) {
					// compare the key.
					if(__rolefunction.compareTo(__originalrolefunction) == 0) {
						// found the original record.
						foundRoleFunction = true;
						//update the record
						// clear DAO's data
						rolefunction.clearCurrentData();
						// set original bean data into DAO
						rolefunction.setDataFromBase(__originalrolefunction);
						// clear DAO data modification flags, just like the data reloaded from DB.
						rolefunction.clearModifiedFlags();
						// set the current data into DAO
						rolefunction.setDataFromBase(__rolefunction);
						// execute update 
						rolefunction.update();
						break;
					}
				}
				// if the data not found in the original list, then add to the need-to-add list.
				if(!foundRoleFunction) needToAddRoleFunction.add(__rolefunction);
			}
			// find the record that in the original list but not in the current list.
			for(BaseRoleFunction __originalrolefunction : originalrolefunction) {
				boolean foundRoleFunction = false;
				for(BaseRoleFunction __rolefunction : getDetailRoleFunction() ) {
					if(__rolefunction.compareTo(__originalrolefunction) == 0) {
						foundRoleFunction = true;
						break;
				}
				}
				if(!foundRoleFunction) needToDeleteRoleFunction.add(__originalrolefunction);
			}
			// process need-to-add list. insert into DB.
			for(BaseRoleFunction __rolefunction : needToAddRoleFunction ) {
				rolefunction.clearCurrentData();
				rolefunction.setDataFromBase(__rolefunction);
				rolefunction.save();
				__rolefunction.setRoleFunctionId(rolefunction.getRoleFunctionId());
			}
			// process need-to-delete list.
			for(BaseRoleFunction __rolefunction : needToDeleteRoleFunction ) {
				deleteFromRoleFunction(__rolefunction, rolefunction);
			}
		} else {
			// if current list is empty. then delete all the original details.
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
