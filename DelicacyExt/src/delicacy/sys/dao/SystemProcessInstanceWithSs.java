package delicacy.sys.dao;

import java.util.ArrayList;
import java.util.List;

import delicacy.sys.bean.BaseSystemProcessAttention;
import delicacy.sys.bean.BaseSystemProcessInstanceActivity;
import delicacy.sys.bean.BaseSystemProcessInstanceWithSs;


public class SystemProcessInstanceWithSs extends SystemProcessInstance
{


	public SystemProcessInstanceWithSs() throws java.sql.SQLException { initColumnNames(); }

	public List<BaseSystemProcessInstanceActivity> getDetailSystemProcessInstanceActivity() {
		return this.__detail_systemprocessinstanceactivity;
	}

	public void setDetailSystemProcessInstanceActivity( List<BaseSystemProcessInstanceActivity> value ) {
		this.__detail_systemprocessinstanceactivity = value;
	}

	public List<BaseSystemProcessAttention> getDetailSystemProcessAttention() {
		return this.__detail_systemprocessattention;
	}

	public void setDetailSystemProcessAttention( List<BaseSystemProcessAttention> value ) {
		this.__detail_systemprocessattention = value;
	}

	private void deleteAndSaveDetailTables() throws java.sql.SQLException {
		// 实例化  DAO
		SystemProcessInstanceActivity systemprocessinstanceactivity = new SystemProcessInstanceActivity();
		// 装入数据库中原来明细数据
		systemprocessinstanceactivity.setConditionProcessInstanceId("=", getProcessInstanceId());
		List<BaseSystemProcessInstanceActivity> originalsystemprocessinstanceactivity = systemprocessinstanceactivity.conditionalLoad(null);
		// 如果当前明细不为空
		if(getDetailSystemProcessInstanceActivity() != null) {
			// 需要删除的明细数据
			List<BaseSystemProcessInstanceActivity> needToDeleteSystemProcessInstanceActivity = new ArrayList<>();
			// 需要增加的明细数据
			List<BaseSystemProcessInstanceActivity> needToAddSystemProcessInstanceActivity = new ArrayList<>();
			// 找到在原明细中存在的数据并修改该数据，如果在原数据中没有则加到需要添加的列表中
			for(BaseSystemProcessInstanceActivity __systemprocessinstanceactivity : getDetailSystemProcessInstanceActivity() ) {
				// 赋值关联字段
				__systemprocessinstanceactivity.setProcessInstanceId( getProcessInstanceId());
				// 设置找到标志
				boolean foundSystemProcessInstanceActivity = false;
				// 在原明细中找
				for(BaseSystemProcessInstanceActivity __originalsystemprocessinstanceactivity : originalsystemprocessinstanceactivity) {
					// 如果两个对象主键相同
					if(__systemprocessinstanceactivity.compareTo(__originalsystemprocessinstanceactivity) == 0) {
						// 设置找到标志
						foundSystemProcessInstanceActivity = true;
						// 修改该条数据
						// 清空DAO中当前数据
						systemprocessinstanceactivity.clearCurrentData();
						// 把原明细数据装入DAO
						systemprocessinstanceactivity.setDataFromBase(__originalsystemprocessinstanceactivity);
						// 清空DAO数据修改标志, 就像数据刚从数据库中装入一样
						systemprocessinstanceactivity.clearModifiedFlags();
						// 把新的数据装入
						systemprocessinstanceactivity.setDataFromBase(__systemprocessinstanceactivity);
						// 执行修改
						systemprocessinstanceactivity.update();
						break;
					}
				}
				// 如果没找到，则加入需要增加的列表中
				if(!foundSystemProcessInstanceActivity) needToAddSystemProcessInstanceActivity.add(__systemprocessinstanceactivity);
			}
			// 找到那些在原明细中有，但当前明细中没有的条目
			for(BaseSystemProcessInstanceActivity __originalsystemprocessinstanceactivity : originalsystemprocessinstanceactivity) {
				boolean foundSystemProcessInstanceActivity = false;
				for(BaseSystemProcessInstanceActivity __systemprocessinstanceactivity : getDetailSystemProcessInstanceActivity() ) {
					if(__systemprocessinstanceactivity.compareTo(__originalsystemprocessinstanceactivity) == 0) {
						foundSystemProcessInstanceActivity = true;
						break;
				}
				}
				if(!foundSystemProcessInstanceActivity) needToDeleteSystemProcessInstanceActivity.add(__originalsystemprocessinstanceactivity);
			}
			// 处理需要增加的明细数据
			for(BaseSystemProcessInstanceActivity __systemprocessinstanceactivity : needToAddSystemProcessInstanceActivity ) {
				systemprocessinstanceactivity.setPrimaryKeyFromBase(__systemprocessinstanceactivity);
				// 如果该条数据主键为空，则插入数据, 注意这种处理方式只对子表主键为自增的有效
				// 如果子表的主键不是自增类型，则需要考虑如何生成主键, 并修改下面的代码
				if(systemprocessinstanceactivity.isPrimaryKeyNull()) { systemprocessinstanceactivity.clearCurrentData(); systemprocessinstanceactivity.setDataFromBase(__systemprocessinstanceactivity); systemprocessinstanceactivity.save(); }
				// 否则从数据中查询该数据，找到就修改，否则插入
				else { if(systemprocessinstanceactivity.load()) { systemprocessinstanceactivity.setDataFromBase(__systemprocessinstanceactivity); systemprocessinstanceactivity.update(); } else { systemprocessinstanceactivity.clearCurrentData(); systemprocessinstanceactivity.setDataFromBase(__systemprocessinstanceactivity); systemprocessinstanceactivity.save(); } }
			}
			// 处理需要删除的明细数据
			for(BaseSystemProcessInstanceActivity __systemprocessinstanceactivity : needToDeleteSystemProcessInstanceActivity ) {
				deleteFromSystemProcessInstanceActivity(__systemprocessinstanceactivity, systemprocessinstanceactivity);
			}
		} else {
			// 如果当前明细为空，说明所有原有的明细都需要删除
			for(BaseSystemProcessInstanceActivity __systemprocessinstanceactivity : originalsystemprocessinstanceactivity ) {
				deleteFromSystemProcessInstanceActivity(__systemprocessinstanceactivity, systemprocessinstanceactivity);
			}
		}
		// 实例化  DAO
		SystemProcessAttention systemprocessattention = new SystemProcessAttention();
		// 装入数据库中原来明细数据
		systemprocessattention.setConditionProcessInstanceId("=", getProcessInstanceId());
		List<BaseSystemProcessAttention> originalsystemprocessattention = systemprocessattention.conditionalLoad(null);
		// 如果当前明细不为空
		if(getDetailSystemProcessAttention() != null) {
			// 需要删除的明细数据
			List<BaseSystemProcessAttention> needToDeleteSystemProcessAttention = new ArrayList<>();
			// 需要增加的明细数据
			List<BaseSystemProcessAttention> needToAddSystemProcessAttention = new ArrayList<>();
			// 找到在原明细中存在的数据并修改该数据，如果在原数据中没有则加到需要添加的列表中
			for(BaseSystemProcessAttention __systemprocessattention : getDetailSystemProcessAttention() ) {
				// 赋值关联字段
				__systemprocessattention.setProcessInstanceId( getProcessInstanceId());
				// 设置找到标志
				boolean foundSystemProcessAttention = false;
				// 在原明细中找
				for(BaseSystemProcessAttention __originalsystemprocessattention : originalsystemprocessattention) {
					// 如果两个对象主键相同
					if(__systemprocessattention.compareTo(__originalsystemprocessattention) == 0) {
						// 设置找到标志
						foundSystemProcessAttention = true;
						// 修改该条数据
						// 清空DAO中当前数据
						systemprocessattention.clearCurrentData();
						// 把原明细数据装入DAO
						systemprocessattention.setDataFromBase(__originalsystemprocessattention);
						// 清空DAO数据修改标志, 就像数据刚从数据库中装入一样
						systemprocessattention.clearModifiedFlags();
						// 把新的数据装入
						systemprocessattention.setDataFromBase(__systemprocessattention);
						// 执行修改
						systemprocessattention.update();
						break;
					}
				}
				// 如果没找到，则加入需要增加的列表中
				if(!foundSystemProcessAttention) needToAddSystemProcessAttention.add(__systemprocessattention);
			}
			// 找到那些在原明细中有，但当前明细中没有的条目
			for(BaseSystemProcessAttention __originalsystemprocessattention : originalsystemprocessattention) {
				boolean foundSystemProcessAttention = false;
				for(BaseSystemProcessAttention __systemprocessattention : getDetailSystemProcessAttention() ) {
					if(__systemprocessattention.compareTo(__originalsystemprocessattention) == 0) {
						foundSystemProcessAttention = true;
						break;
				}
				}
				if(!foundSystemProcessAttention) needToDeleteSystemProcessAttention.add(__originalsystemprocessattention);
			}
			// 处理需要增加的明细数据
			for(BaseSystemProcessAttention __systemprocessattention : needToAddSystemProcessAttention ) {
				systemprocessattention.setPrimaryKeyFromBase(__systemprocessattention);
				// 如果该条数据主键为空，则插入数据, 注意这种处理方式只对子表主键为自增的有效
				// 如果子表的主键不是自增类型，则需要考虑如何生成主键, 并修改下面的代码
				if(systemprocessattention.isPrimaryKeyNull()) { systemprocessattention.clearCurrentData(); systemprocessattention.setDataFromBase(__systemprocessattention); systemprocessattention.save(); }
				// 否则从数据中查询该数据，找到就修改，否则插入
				else { if(systemprocessattention.load()) { systemprocessattention.setDataFromBase(__systemprocessattention); systemprocessattention.update(); } else { systemprocessattention.clearCurrentData(); systemprocessattention.setDataFromBase(__systemprocessattention); systemprocessattention.save(); } }
			}
			// 处理需要删除的明细数据
			for(BaseSystemProcessAttention __systemprocessattention : needToDeleteSystemProcessAttention ) {
				deleteFromSystemProcessAttention(__systemprocessattention, systemprocessattention);
			}
		} else {
			// 如果当前明细为空，说明所有原有的明细都需要删除
			for(BaseSystemProcessAttention __systemprocessattention : originalsystemprocessattention ) {
				deleteFromSystemProcessAttention(__systemprocessattention, systemprocessattention);
			}
		}
	}

	private void deleteFromSystemProcessInstanceActivity(BaseSystemProcessInstanceActivity bean, SystemProcessInstanceActivity dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setProcessInstanceId(null);
		if(__detail_delete_systemprocessinstanceactivity) dao.delete(); else dao.update();
	}

	private void deleteFromSystemProcessAttention(BaseSystemProcessAttention bean, SystemProcessAttention dao) throws java.sql.SQLException {
		dao.setDataFromBase(bean);
		dao.clearModifiedFlags();
		dao.setProcessInstanceId(null);
		if(__detail_delete_systemprocessattention) dao.delete(); else dao.update();
	}

	// 删除子表方法, 一般不建议使用，最好是修改关联关系，脱离关系就行
	private void deleteDetailTables()  throws java.sql.SQLException {
		SystemProcessInstanceActivity systemprocessinstanceactivity = new SystemProcessInstanceActivity();
		systemprocessinstanceactivity.setConditionProcessInstanceId("=", getProcessInstanceId());
		systemprocessinstanceactivity.conditionalDelete();
		SystemProcessAttention systemprocessattention = new SystemProcessAttention();
		systemprocessattention.setConditionProcessInstanceId("=", getProcessInstanceId());
		systemprocessattention.conditionalDelete();
	}

	// 从数据中按主键查询数据，可以包含明细数据
	public boolean load(boolean loadDetail) throws java.sql.SQLException {
		if(!super.load()) return false;
		if(loadDetail){
			SystemProcessInstanceActivity systemprocessinstanceactivity = new SystemProcessInstanceActivity();
			systemprocessinstanceactivity.setConditionProcessInstanceId("=", getProcessInstanceId());
			setDetailSystemProcessInstanceActivity(systemprocessinstanceactivity.conditionalLoad());
			SystemProcessAttention systemprocessattention = new SystemProcessAttention();
			systemprocessattention.setConditionProcessInstanceId("=", getProcessInstanceId());
			setDetailSystemProcessAttention(systemprocessattention.conditionalLoad());
		}
		return true;
	}

	public List<BaseSystemProcessInstanceWithSs> conditionalLoadExt(String addtional) throws java.sql.SQLException {
		List<BaseSystemProcessInstanceWithSs> result = BaseSystemProcessInstanceWithSs.getBeanList(conditionalLoad(addtional));
		return result;
	}

	public BaseSystemProcessInstanceWithSs generateBaseExt() {
		BaseSystemProcessInstanceWithSs ____result = new BaseSystemProcessInstanceWithSs();
		setDataToBase(____result);
		return ____result;
	}

	public void setDataFromBase(BaseSystemProcessInstanceWithSs __base) {
		super.setDataFromBase(__base);
		setDetailSystemProcessInstanceActivity(__base.getDetailSystemProcessInstanceActivity());
		setDetailSystemProcessAttention(__base.getDetailSystemProcessAttention());
	}

	public void setDataToBase(BaseSystemProcessInstanceWithSs __base) {
		super.setDataToBase(__base);
		__base.setDetailSystemProcessInstanceActivity(getDetailSystemProcessInstanceActivity());
		__base.setDetailSystemProcessAttention(getDetailSystemProcessAttention());
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

	public boolean isDeleteDetailSystemProcessInstanceActivity() {
		return this.__detail_delete_systemprocessinstanceactivity;
	}

	public void setDeleteDetailSystemProcessInstanceActivity( boolean value ) {
		this.__detail_delete_systemprocessinstanceactivity = value;
	}

	protected boolean __detail_delete_systemprocessinstanceactivity = false; 
	protected List<BaseSystemProcessInstanceActivity> __detail_systemprocessinstanceactivity ; 
	public boolean isDeleteDetailSystemProcessAttention() {
		return this.__detail_delete_systemprocessattention;
	}

	public void setDeleteDetailSystemProcessAttention( boolean value ) {
		this.__detail_delete_systemprocessattention = value;
	}

	protected boolean __detail_delete_systemprocessattention = true; 
	protected List<BaseSystemProcessAttention> __detail_systemprocessattention ; 
}
