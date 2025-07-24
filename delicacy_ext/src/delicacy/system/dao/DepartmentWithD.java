package delicacy.system.dao;

import java.util.*;
import delicacy.system.bean.BaseDepartmentWithD;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.dao.DepartmentRole;

public class DepartmentWithD extends delicacy.system.dao.Department
{


	public DepartmentWithD() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseDepartmentRole> getDetailDepartmentRole() {
		return this.__detail_departmentrole;
	}

	public void setDetailDepartmentRole( List<BaseDepartmentRole> value ) {
		this.__detail_departmentrole = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// 实例化 部门角色 DAO
		DepartmentRole departmentrole = new DepartmentRole();
		// 装入数据库中原来明细数据
		departmentrole.setConditionDepartmentId("=", getDepartmentId());
		List<BaseDepartmentRole> originaldepartmentrole = departmentrole.conditionalLoad();
		// 如果当前明细不为空
		if(getDetailDepartmentRole() != null) {
			// 需要删除的明细数据
			List<BaseDepartmentRole> needToDeleteDepartmentRole = new ArrayList<>();
			// 需要增加的明细数据
			List<BaseDepartmentRole> needToAddDepartmentRole = new ArrayList<>();
			// 找到在原明细中存在的数据并修改该数据，如果在原数据中没有则加到需要添加的列表中
			for(BaseDepartmentRole __departmentrole : getDetailDepartmentRole() ) {
				// 赋值关联字段
				__departmentrole.setDepartmentId(getDepartmentId());
				// 设置找到标志
				boolean foundDepartmentRole = false;
				// 在原明细中找
				for(BaseDepartmentRole __originaldepartmentrole : originaldepartmentrole) {
					// 如果两个对象主键相同
					if(Objects.equals(__departmentrole.getRoleId(), __originaldepartmentrole.getRoleId())) {
						// 设置找到标志
						foundDepartmentRole = true;
						// 修改该条数据
						// 清空DAO中当前数据
						departmentrole.clearCurrentData();
						// 把原明细数据装入DAO
						departmentrole.setDataFromBase(__originaldepartmentrole);
						// 清空DAO数据修改标志, 就像数据刚从数据库中装入一样
						departmentrole.clearModifiedFlags();
						// 把新的数据装入
						departmentrole.setDataFromBase(__departmentrole);
						// 执行修改
						departmentrole.update();
						break;
					}
				}
				// 如果没找到，则加入需要增加的列表中
				if(!foundDepartmentRole) needToAddDepartmentRole.add(__departmentrole);
			}
			// 找到那些在原明细中有，但当前明细中没有的条目
			for(BaseDepartmentRole __originaldepartmentrole : originaldepartmentrole) {
				boolean foundDepartmentRole = false;
				for(BaseDepartmentRole __departmentrole : getDetailDepartmentRole() ) {
					if(Objects.equals(__departmentrole.getRoleId(), __originaldepartmentrole.getRoleId())) {
						foundDepartmentRole = true;
						break;
				}
				}
				if(!foundDepartmentRole) needToDeleteDepartmentRole.add(__originaldepartmentrole);
			}
			// 处理需要增加的明细数据
			for(BaseDepartmentRole __departmentrole : needToAddDepartmentRole ) {
				departmentrole.setPrimaryKeyFromBase(__departmentrole);
				// 如果该条数据主键为空，则插入数据, 注意这种处理方式只对子表主键为自增的有效
				// 如果子表的主键不是自增类型，则需要考虑如何生成主键, 并修改下面的代码
				if(departmentrole.isPrimaryKeyNull()) { departmentrole.clearCurrentData(); departmentrole.setDataFromBase(__departmentrole); departmentrole.save(); }
				// 否则从数据中查询该数据，找到就修改，否则插入
				else { if(departmentrole.load()) { departmentrole.setDataFromBase(__departmentrole); departmentrole.update(); } else { departmentrole.clearCurrentData(); departmentrole.setDataFromBase(__departmentrole); departmentrole.save(); } }
			}
			// 处理需要删除的明细数据
			for(BaseDepartmentRole __departmentrole : needToDeleteDepartmentRole ) {
				deleteFromDepartmentRole(__departmentrole, departmentrole);
			}
		} else {
			// 如果当前明细为空，说明所有原有的明细都需要删除
			for(BaseDepartmentRole __departmentrole : originaldepartmentrole ) {
				deleteFromDepartmentRole(__departmentrole, departmentrole);
			}
		}
	}

	private void deleteFromDepartmentRole(BaseDepartmentRole bean, DepartmentRole dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setDepartmentId(null);
		if(__detail_delete_departmentrole) dao.delete(); else dao.update();
	}

	// 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
	private void deleteDetailTables()  throws java.sql.SQLException {
		DepartmentRole departmentrole = new DepartmentRole();
		departmentrole.setConditionDepartmentId("=", getDepartmentId());
		departmentrole.conditionalDelete();
	}

	// 从数据中按主键查询数据，可以包含明细数据
	public boolean load(boolean loadDetail) throws java.sql.SQLException {
		if(!super.load()) return false;
		if(loadDetail){
			DepartmentRole departmentrole = new DepartmentRole();
			departmentrole.setConditionDepartmentId("=", getDepartmentId());
			setDetailDepartmentRole(departmentrole.conditionalLoad());
		}
		return true;
	}

	public List<BaseDepartmentWithD> conditionalLoadExt(String addtional) throws java.sql.SQLException {
		List<BaseDepartmentWithD> result = BaseDepartmentWithD.getBeanList(conditionalLoad(addtional));
		return result;
	}

	public BaseDepartmentWithD generateBaseExt() {
		BaseDepartmentWithD ____result = new BaseDepartmentWithD();
		setDataToBase(____result);
		return ____result;
	}

	public void setDataFromBase(BaseDepartmentWithD __base) {
		super.setDataFromBase(__base);
		setDetailDepartmentRole(__base.getDetailDepartmentRole());
	}

	public void setDataToBase(BaseDepartmentWithD __base) {
		super.setDataToBase(__base);
		__base.setDetailDepartmentRole(getDetailDepartmentRole());
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

	public boolean isDeleteDetailDepartmentRole() {
		return this.__detail_delete_departmentrole;
	}

	public void setDeleteDetailDepartmentRole( boolean value ) {
		this.__detail_delete_departmentrole = value;
	}

	protected boolean __detail_delete_departmentrole = true; 
	protected List<BaseDepartmentRole> __detail_departmentrole ; 
}
