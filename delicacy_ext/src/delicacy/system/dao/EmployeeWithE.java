package delicacy.system.dao;

import java.util.*;
import delicacy.system.bean.BaseEmployeeWithE;
import delicacy.system.bean.BaseEmployeeRole;

public class EmployeeWithE extends delicacy.system.dao.Employee
{


	public EmployeeWithE() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseEmployeeRole> getDetailEmployeeRole() {
		return this.__detail_employeerole;
	}

	public void setDetailEmployeeRole( List<BaseEmployeeRole> value ) {
		this.__detail_employeerole = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// 实例化 员工角色 DAO
		EmployeeRole employeerole = new EmployeeRole();
		// 删除数据库中原来明细数据
		employeerole.setConditionEmployeeId("=", getEmployeeId());
		employeerole.conditionalDelete();
		if(getDetailEmployeeRole() != null) {
			for(BaseEmployeeRole __employeerole : getDetailEmployeeRole() ) {
				// 赋值关联字段
				__employeerole.setEmployeeId(getEmployeeId());
			}
			// 保存子表数据
			employeerole.save(getDetailEmployeeRole());
		}
	}

	private void deleteFromEmployeeRole(BaseEmployeeRole bean, EmployeeRole dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setEmployeeId(null);
		if(__detail_delete_employeerole) dao.delete(); else dao.update();
	}

	// 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
	private void deleteDetailTables()  throws java.sql.SQLException {
		EmployeeRole employeerole = new EmployeeRole();
		employeerole.setConditionEmployeeId("=", getEmployeeId());
		employeerole.conditionalDelete();
	}

	// 从数据中按主键查询数据，可以包含明细数据
	public boolean load(boolean loadDetail) throws java.sql.SQLException {
		if(!super.load()) return false;
		if(loadDetail){
			EmployeeRole employeerole = new EmployeeRole();
			employeerole.setConditionEmployeeId("=", getEmployeeId());
			setDetailEmployeeRole(employeerole.conditionalLoad(null));
		}
		return true;
	}

	public List<BaseEmployeeWithE> conditionalLoadExt(String addtional) throws java.sql.SQLException {
		List<BaseEmployeeWithE> result = BaseEmployeeWithE.getBeanList(conditionalLoad(addtional));
		if(result.size() > 0) {
			EmployeeRole employeerole = new EmployeeRole();
			int varCount = 0;
			Object[] convars = new Object[result.size()];
			for(BaseEmployeeWithE b : result) convars[varCount++] = b.getEmployeeId();
			employeerole.addCondition(BaseEmployeeRole.CS_EMPLOYEE_ID, "IN", convars);
			List<BaseEmployeeRole> employeeroles = employeerole.conditionalLoad(null);
			for(BaseEmployeeWithE ii : result){ ii.setDetailEmployeeRole(new ArrayList<BaseEmployeeRole>()); }
			for(BaseEmployeeRole mm : employeeroles){
				for(BaseEmployeeWithE nn : result){
					if(mm.getEmployeeId().equals(nn.getEmployeeId())) { nn.getDetailEmployeeRole().add(mm); break; } 
				}
			}
		}
		return result;
	}

	public BaseEmployeeWithE generateBaseExt() {
		BaseEmployeeWithE ____result = new BaseEmployeeWithE();
		setDataToBase(____result);
		return ____result;
	}

	public void setDataFromBase(BaseEmployeeWithE __base) {
		super.setDataFromBase(__base);
		setDetailEmployeeRole(__base.getDetailEmployeeRole());
	}

	public void setDataToBase(BaseEmployeeWithE __base) {
		super.setDataToBase(__base);
		__base.setDetailEmployeeRole(getDetailEmployeeRole());
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

	public boolean isDeleteDetailEmployeeRole() {
		return this.__detail_delete_employeerole;
	}

	public void setDeleteDetailEmployeeRole( boolean value ) {
		this.__detail_delete_employeerole = value;
	}

	protected boolean __detail_delete_employeerole = false; 
	protected List<BaseEmployeeRole> __detail_employeerole ; 
}
