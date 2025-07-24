package delicacy.department.dao;

import delicacy.department.bean.BaseDepartmentWithSd;
import delicacy.system.bean.BaseDepartmentRole;
import delicacy.system.bean.BaseSubjectDepartment;
import delicacy.system.bean.BaseSystemProcessDepartment;
import delicacy.system.dao.Department;
import delicacy.system.dao.DepartmentRole;
import delicacy.system.dao.SubjectDepartment;
import delicacy.system.dao.SystemProcessDepartment;

import java.util.ArrayList;
import java.util.List;

public class DepartmentWithSd extends Department
{


	public DepartmentWithSd() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseSystemProcessDepartment> getDetailSystemProcessDepartment() {
		return this.__detail_systemprocessdepartment;
	}

	public void setDetailSystemProcessDepartment( List<BaseSystemProcessDepartment> value ) {
		this.__detail_systemprocessdepartment = value;
	}

	public List<BaseDepartmentRole> getDetailDepartmentRole() {
		return this.__detail_departmentrole;
	}

	public void setDetailDepartmentRole( List<BaseDepartmentRole> value ) {
		this.__detail_departmentrole = value;
	}

	public List<BaseSubjectDepartment> getDetailSubjectDepartment() {
		return this.__detail_subjectdepartment;
	}

	public void setDetailSubjectDepartment( List<BaseSubjectDepartment> value ) {
		this.__detail_subjectdepartment = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// instantiate  DAO
		SystemProcessDepartment systemprocessdepartment = new SystemProcessDepartment();
		// load the original detail data.
		systemprocessdepartment.setConditionDepartmentId("=", getDepartmentId());
		List<BaseSystemProcessDepartment> originalsystemprocessdepartment = systemprocessdepartment.conditionalLoad();
		// if current detail is not empty.
		if(getDetailSystemProcessDepartment() != null) {
			// need to delete data
			List<BaseSystemProcessDepartment> needToDeleteSystemProcessDepartment = new ArrayList<>();
			// need to add data
			List<BaseSystemProcessDepartment> needToAddSystemProcessDepartment = new ArrayList<>();
			// find that records need to be modified.
			for(BaseSystemProcessDepartment __systemprocessdepartment : getDetailSystemProcessDepartment() ) {
				// set that related fields.
				__systemprocessdepartment.setDepartmentId(getDepartmentId());
				// if found then set found flag.
				boolean foundSystemProcessDepartment = false;
				// search the original detail list.
				for(BaseSystemProcessDepartment __originalsystemprocessdepartment : originalsystemprocessdepartment) {
					// compare the key.
					if(__systemprocessdepartment.compareTo(__originalsystemprocessdepartment) == 0) {
						// found the original record.
						foundSystemProcessDepartment = true;
						//update the record
						// clear DAO's data
						systemprocessdepartment.clearCurrentData();
						// set original bean data into DAO
						systemprocessdepartment.setDataFromBase(__originalsystemprocessdepartment);
						// clear DAO data modification flags, just like the data reloaded from DB.
						systemprocessdepartment.clearModifiedFlags();
						// set the current data into DAO
						systemprocessdepartment.setDataFromBase(__systemprocessdepartment);
						// execute update
						systemprocessdepartment.update();
						break;
					}
				}
				// if the data not found in the original list, then add to the need-to-add list.
				if(!foundSystemProcessDepartment) needToAddSystemProcessDepartment.add(__systemprocessdepartment);
			}
			// find the record that in the original list but not in the current list.
			for(BaseSystemProcessDepartment __originalsystemprocessdepartment : originalsystemprocessdepartment) {
				boolean foundSystemProcessDepartment = false;
				for(BaseSystemProcessDepartment __systemprocessdepartment : getDetailSystemProcessDepartment() ) {
					if(__systemprocessdepartment.compareTo(__originalsystemprocessdepartment) == 0) {
						foundSystemProcessDepartment = true;
						break;
				}
				}
				if(!foundSystemProcessDepartment) needToDeleteSystemProcessDepartment.add(__originalsystemprocessdepartment);
			}
			// process need-to-delete list.
			for(BaseSystemProcessDepartment __systemprocessdepartment : needToDeleteSystemProcessDepartment ) {
				deleteFromSystemProcessDepartment(__systemprocessdepartment, systemprocessdepartment);
			}
			// process need-to-add list. insert into DB.
			for(BaseSystemProcessDepartment __systemprocessdepartment : needToAddSystemProcessDepartment ) {
				systemprocessdepartment.clearCurrentData();
				systemprocessdepartment.setDataFromBase(__systemprocessdepartment);
				systemprocessdepartment.save();
				__systemprocessdepartment.setProcessDepartmentId(systemprocessdepartment.getProcessDepartmentId());
			}
		} else {
			// if current list is empty. then delete all the original details.
			for(BaseSystemProcessDepartment __systemprocessdepartment : originalsystemprocessdepartment ) {
				deleteFromSystemProcessDepartment(__systemprocessdepartment, systemprocessdepartment);
			}
		}
		// instantiate 部门角色 DAO
		DepartmentRole departmentrole = new DepartmentRole();
		// load the original detail data.
		departmentrole.setConditionDepartmentId("=", getDepartmentId());
		List<BaseDepartmentRole> originaldepartmentrole = departmentrole.conditionalLoad();
		// if current detail is not empty.
		if(getDetailDepartmentRole() != null) {
			// need to delete data
			List<BaseDepartmentRole> needToDeleteDepartmentRole = new ArrayList<>();
			// need to add data
			List<BaseDepartmentRole> needToAddDepartmentRole = new ArrayList<>();
			// find that records need to be modified.
			for(BaseDepartmentRole __departmentrole : getDetailDepartmentRole() ) {
				// set that related fields.
				__departmentrole.setDepartmentId(getDepartmentId());
				// if found then set found flag.
				boolean foundDepartmentRole = false;
				// search the original detail list.
				for(BaseDepartmentRole __originaldepartmentrole : originaldepartmentrole) {
					// compare the key.
					if(__departmentrole.compareTo(__originaldepartmentrole) == 0) {
						// found the original record.
						foundDepartmentRole = true;
						//update the record
						// clear DAO's data
						departmentrole.clearCurrentData();
						// set original bean data into DAO
						departmentrole.setDataFromBase(__originaldepartmentrole);
						// clear DAO data modification flags, just like the data reloaded from DB.
						departmentrole.clearModifiedFlags();
						// set the current data into DAO
						departmentrole.setDataFromBase(__departmentrole);
						// execute update
						departmentrole.update();
						break;
					}
				}
				// if the data not found in the original list, then add to the need-to-add list.
				if(!foundDepartmentRole) needToAddDepartmentRole.add(__departmentrole);
			}
			// find the record that in the original list but not in the current list.
			for(BaseDepartmentRole __originaldepartmentrole : originaldepartmentrole) {
				boolean foundDepartmentRole = false;
				for(BaseDepartmentRole __departmentrole : getDetailDepartmentRole() ) {
					if(__departmentrole.compareTo(__originaldepartmentrole) == 0) {
						foundDepartmentRole = true;
						break;
				}
				}
				if(!foundDepartmentRole) needToDeleteDepartmentRole.add(__originaldepartmentrole);
			}
			// process need-to-delete list.
			for(BaseDepartmentRole __departmentrole : needToDeleteDepartmentRole ) {
				deleteFromDepartmentRole(__departmentrole, departmentrole);
			}
			// process need-to-add list. insert into DB.
			for(BaseDepartmentRole __departmentrole : needToAddDepartmentRole ) {
				departmentrole.clearCurrentData();
				departmentrole.setDataFromBase(__departmentrole);
				departmentrole.save();
				__departmentrole.setDepartmentRoleId(departmentrole.getDepartmentRoleId());
			}
		} else {
			// if current list is empty. then delete all the original details.
			for(BaseDepartmentRole __departmentrole : originaldepartmentrole ) {
				deleteFromDepartmentRole(__departmentrole, departmentrole);
			}
		}
		// instantiate  DAO
		SubjectDepartment subjectdepartment = new SubjectDepartment();
		// load the original detail data.
		subjectdepartment.setConditionDepartmentId("=", getDepartmentId());
		List<BaseSubjectDepartment> originalsubjectdepartment = subjectdepartment.conditionalLoad();
		// if current detail is not empty.
		if(getDetailSubjectDepartment() != null) {
			// need to delete data
			List<BaseSubjectDepartment> needToDeleteSubjectDepartment = new ArrayList<>();
			// need to add data
			List<BaseSubjectDepartment> needToAddSubjectDepartment = new ArrayList<>();
			// find that records need to be modified.
			for(BaseSubjectDepartment __subjectdepartment : getDetailSubjectDepartment() ) {
				// set that related fields.
				__subjectdepartment.setDepartmentId(getDepartmentId());
				// if found then set found flag.
				boolean foundSubjectDepartment = false;
				// search the original detail list.
				for(BaseSubjectDepartment __originalsubjectdepartment : originalsubjectdepartment) {
					// compare the key.
					if(__subjectdepartment.compareTo(__originalsubjectdepartment) == 0) {
						// found the original record.
						foundSubjectDepartment = true;
						//update the record
						// clear DAO's data
						subjectdepartment.clearCurrentData();
						// set original bean data into DAO
						subjectdepartment.setDataFromBase(__originalsubjectdepartment);
						// clear DAO data modification flags, just like the data reloaded from DB.
						subjectdepartment.clearModifiedFlags();
						// set the current data into DAO
						subjectdepartment.setDataFromBase(__subjectdepartment);
						// execute update
						subjectdepartment.update();
						break;
					}
				}
				// if the data not found in the original list, then add to the need-to-add list.
				if(!foundSubjectDepartment) needToAddSubjectDepartment.add(__subjectdepartment);
			}
			// find the record that in the original list but not in the current list.
			for(BaseSubjectDepartment __originalsubjectdepartment : originalsubjectdepartment) {
				boolean foundSubjectDepartment = false;
				for(BaseSubjectDepartment __subjectdepartment : getDetailSubjectDepartment() ) {
					if(__subjectdepartment.compareTo(__originalsubjectdepartment) == 0) {
						foundSubjectDepartment = true;
						break;
				}
				}
				if(!foundSubjectDepartment) needToDeleteSubjectDepartment.add(__originalsubjectdepartment);
			}
			// process need-to-delete list.
			for(BaseSubjectDepartment __subjectdepartment : needToDeleteSubjectDepartment ) {
				deleteFromSubjectDepartment(__subjectdepartment, subjectdepartment);
			}
			// process need-to-add list. insert into DB.
			for(BaseSubjectDepartment __subjectdepartment : needToAddSubjectDepartment ) {
				subjectdepartment.clearCurrentData();
				subjectdepartment.setDataFromBase(__subjectdepartment);
				subjectdepartment.save();
				__subjectdepartment.setSubjectDepartmentId(subjectdepartment.getSubjectDepartmentId());
			}
		} else {
			// if current list is empty. then delete all the original details.
			for(BaseSubjectDepartment __subjectdepartment : originalsubjectdepartment ) {
				deleteFromSubjectDepartment(__subjectdepartment, subjectdepartment);
			}
		}
	}

	private void deleteFromSystemProcessDepartment(BaseSystemProcessDepartment bean, SystemProcessDepartment dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setDepartmentId(null);
		if(__detail_delete_systemprocessdepartment) dao.delete(); else dao.update();
	}

	private void deleteFromDepartmentRole(BaseDepartmentRole bean, DepartmentRole dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setDepartmentId(null);
		if(__detail_delete_departmentrole) dao.delete(); else dao.update();
	}

	private void deleteFromSubjectDepartment(BaseSubjectDepartment bean, SubjectDepartment dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setDepartmentId(null);
		if(__detail_delete_subjectdepartment) dao.delete(); else dao.update();
	}

	// 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
	private void deleteDetailTables()  throws java.sql.SQLException {
		SystemProcessDepartment systemprocessdepartment = new SystemProcessDepartment();
		systemprocessdepartment.setConditionDepartmentId("=", getDepartmentId());
		systemprocessdepartment.conditionalDelete();
		DepartmentRole departmentrole = new DepartmentRole();
		departmentrole.setConditionDepartmentId("=", getDepartmentId());
		departmentrole.conditionalDelete();
		SubjectDepartment subjectdepartment = new SubjectDepartment();
		subjectdepartment.setConditionDepartmentId("=", getDepartmentId());
		subjectdepartment.conditionalDelete();
	}

	// 从数据中按主键查询数据，可以包含明细数据
	public boolean load(boolean loadDetail) throws java.sql.SQLException {
		if(!super.load()) return false;
		if(loadDetail){
			SystemProcessDepartment systemprocessdepartment = new SystemProcessDepartment();
			systemprocessdepartment.setConditionDepartmentId("=", getDepartmentId());
			setDetailSystemProcessDepartment(systemprocessdepartment.conditionalLoad());
			DepartmentRole departmentrole = new DepartmentRole();
			departmentrole.setConditionDepartmentId("=", getDepartmentId());
			setDetailDepartmentRole(departmentrole.conditionalLoad());
			SubjectDepartment subjectdepartment = new SubjectDepartment();
			subjectdepartment.setConditionDepartmentId("=", getDepartmentId());
			setDetailSubjectDepartment(subjectdepartment.conditionalLoad());
		}
		return true;
	}

	public List<BaseDepartmentWithSd> conditionalLoadExt(String addtional) throws java.sql.SQLException {
		List<BaseDepartmentWithSd> result = BaseDepartmentWithSd.getBeanList(conditionalLoad(addtional));
		return result;
	}

	public BaseDepartmentWithSd generateBaseExt() {
		BaseDepartmentWithSd ____result = new BaseDepartmentWithSd();
		setDataToBase(____result);
		return ____result;
	}

	public void setDataFromBase(BaseDepartmentWithSd __base) {
		super.setDataFromBase(__base);
		setDetailSystemProcessDepartment(__base.getDetailSystemProcessDepartment());
		setDetailDepartmentRole(__base.getDetailDepartmentRole());
		setDetailSubjectDepartment(__base.getDetailSubjectDepartment());
	}

	public void setDataToBase(BaseDepartmentWithSd __base) {
		super.setDataToBase(__base);
		__base.setDetailSystemProcessDepartment(getDetailSystemProcessDepartment());
		__base.setDetailDepartmentRole(getDetailDepartmentRole());
		__base.setDetailSubjectDepartment(getDetailSubjectDepartment());
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

	public boolean isDeleteDetailSystemProcessDepartment() {
		return this.__detail_delete_systemprocessdepartment;
	}

	public void setDeleteDetailSystemProcessDepartment( boolean value ) {
		this.__detail_delete_systemprocessdepartment = value;
	}

	protected boolean __detail_delete_systemprocessdepartment = true;
	protected List<BaseSystemProcessDepartment> __detail_systemprocessdepartment ;
	public boolean isDeleteDetailDepartmentRole() {
		return this.__detail_delete_departmentrole;
	}

	public void setDeleteDetailDepartmentRole( boolean value ) {
		this.__detail_delete_departmentrole = value;
	}

	protected boolean __detail_delete_departmentrole = true;
	protected List<BaseDepartmentRole> __detail_departmentrole ;
	public boolean isDeleteDetailSubjectDepartment() {
		return this.__detail_delete_subjectdepartment;
	}

	public void setDeleteDetailSubjectDepartment( boolean value ) {
		this.__detail_delete_subjectdepartment = value;
	}

	protected boolean __detail_delete_subjectdepartment = true;
	protected List<BaseSubjectDepartment> __detail_subjectdepartment ;
}
