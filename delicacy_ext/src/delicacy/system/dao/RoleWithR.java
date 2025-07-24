package delicacy.system.dao;

import java.util.*;
import delicacy.system.bean.BaseRoleWithR;
import delicacy.system.bean.BaseRoleFunction;
import delicacy.system.dao.RoleFunction;

public class RoleWithR extends delicacy.system.dao.Role
{


	public RoleWithR() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseRoleFunction> getDetailRoleFunction() {
		return this.__detail_rolefunction;
	}

	public void setDetailRoleFunction( List<BaseRoleFunction> value ) {
		this.__detail_rolefunction = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// 实例化  DAO
		RoleFunction rolefunction = new RoleFunction();
		// 删除数据库中原来明细数据
		rolefunction.setConditionRoleId("=", getRoleId());
		rolefunction.conditionalDelete();
		if(getDetailRoleFunction() != null) {
			for(BaseRoleFunction __rolefunction : getDetailRoleFunction() ) {
				// 赋值关联字段
				__rolefunction.setRoleId(getRoleId());
			}
			// 保存子表数据
			rolefunction.save(getDetailRoleFunction());
		}
	}

	private void deleteFromRoleFunction(BaseRoleFunction bean, RoleFunction dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setRoleId(null);
		if(__detail_delete_rolefunction) dao.delete(); else dao.update();
	}

	// 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
	private void deleteDetailTables()  throws java.sql.SQLException {
		RoleFunction rolefunction = new RoleFunction();
		rolefunction.setConditionRoleId("=", getRoleId());
		rolefunction.conditionalDelete();
	}

	// 从数据中按主键查询数据，可以包含明细数据
	public boolean load(boolean loadDetail) throws java.sql.SQLException {
		if(!super.load()) return false;
		if(loadDetail){
			RoleFunction rolefunction = new RoleFunction();
			rolefunction.setConditionRoleId("=", getRoleId());
			setDetailRoleFunction(rolefunction.conditionalLoad(null));
		}
		return true;
	}

	public List<BaseRoleWithR> conditionalLoadExt(String addtional) throws java.sql.SQLException {
		List<BaseRoleWithR> result = BaseRoleWithR.getBeanList(conditionalLoad(addtional));
		if(result.size() > 0) {
			RoleFunction rolefunction = new RoleFunction();
			int varCount = 0;
			Object[] convars = new Object[result.size()];
			for(BaseRoleWithR b : result) convars[varCount++] = b.getRoleId();
			rolefunction.addCondition(BaseRoleFunction.CS_ROLE_ID, "IN", convars);
			List<BaseRoleFunction> rolefunctions = rolefunction.conditionalLoad(null);
			for(BaseRoleWithR ii : result){ ii.setDetailRoleFunction(new ArrayList<BaseRoleFunction>()); }
			for(BaseRoleFunction mm : rolefunctions){
				for(BaseRoleWithR nn : result){
					if(mm.getRoleId().equals(nn.getRoleId())) { nn.getDetailRoleFunction().add(mm); break; } 
				}
			}
		}
		return result;
	}

	public BaseRoleWithR generateBaseExt() {
		BaseRoleWithR ____result = new BaseRoleWithR();
		setDataToBase(____result);
		return ____result;
	}

	public void setDataFromBase(BaseRoleWithR __base) {
		super.setDataFromBase(__base);
		setDetailRoleFunction(__base.getDetailRoleFunction());
	}

	public void setDataToBase(BaseRoleWithR __base) {
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
		if(getRoleId() != null) deleteDetailTables();
		return 1;
	}

	public boolean isDeleteDetailRoleFunction() {
		return this.__detail_delete_rolefunction;
	}

	public void setDeleteDetailRoleFunction( boolean value ) {
		this.__detail_delete_rolefunction = value;
	}

	protected boolean __detail_delete_rolefunction = false; 
	protected List<BaseRoleFunction> __detail_rolefunction ; 
}
