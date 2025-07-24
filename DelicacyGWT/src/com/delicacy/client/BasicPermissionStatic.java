package com.delicacy.client;

/**
 *
 * @author cl
 */
public class BasicPermissionStatic {
	// 基础数据管理平台
	public static final String BASIC_DATA_MANAGEMENT = "SM-00000-000";

	// 角色管理
	public static final String ROLE_MANAGEMENT = "SM-01000-000";
	// 角色新建按钮
	public static final String ROLE_ADD_BUTTON = "SB-01000-001";
	// 角色修改按钮
	public static final String ROLE_UPDATE_BUTTON = "SB-01000-002";
	// 角色删除按钮
	public static final String ROLE_DELETE_BUTTON = "SB-01000-003";

	// 部门管理
	public static final String DEPARTMENT_MANAGEMENT = "SM-02000-000";
	// 部门新建按钮
	public static final String DEPRATMENT_ADD_BUTTON = "SB-02000-001";
	// 部门新建子项按钮
	public static final String DEPRATMENT_ADD_SON_BUTTON = "SB-02000-002";
	// 部门删除按钮
	public static final String DEPRATMENT_DELETE_BUTTON = "SB-02000-003";
	// 部门修改按钮
	public static final String DEPRATMENT_UPDATE_BUTTON = "SB-02000-004";
	// 部门成员转移
	public static final String DEPRATMENT_CHANGE_BUTTON = "SB-02000-005";

	//下拉值管理
	public static final String DICTIONART_MANAGEMENT = "SD-02000-005";
		//公用下拉管理
		public static final String DICTIONARY_MANAGEMENT_UNIVERSAL = "SD-02000-006";
		//产品类型
		public static final String DICTIONARY_MANAGEMENT_PRODUCT_TYPE = "SD-02000-007";
		//服务类容
		public static final String DICTIONARY_MANAGEMENT_SERVICE_CATEGORY = "SD-02000-008";

	// 职员管理
	public static final String EMPLOYEE_MANAGEMENT = "SM-03000-000";
	// 职员新建按钮
	public static final String EMPLOYEE_ADD_BUTTON = "SB-03000-001";
	// 职员修改按钮
	public static final String EMPLOYEE_UPDATE_BUTTON = "SB-03000-002";
	// 职员删除按钮
	public static final String EMPLOYEE_DELETE_BUTTON = "SB-03000-003";
	// 职员密码重置按钮
	public static final String EMPLOYEE_RESET_PWD_BUTTON = "SB-03000-004";
	// 职员角色设置按钮
	public static final String EMPLOYEE_ROLE_SET_BUTTON = "SB-03000-005";
	// 职员导出数据按钮
	public static final String EMPLOYEE_EXPORT_BUTTON = "SB-03000-006";
	// 职员离职
	public static final String EMPLOYEE_RESIGNATION_BUTTON = "SB-03000-007";
	// 部门权限编辑
	public static final String DEPARTMENT_FUNCTION_BUTTION = "SB-03000-008";
	// 职员管理查看所有数据权限
	public static final String EMPLOYEE_CHECK_ALL_DATA = "SD-03000-001";
	//职员管理查看所属公司的数据的权限
	public static final String EMPLOYEE_CHECK_OWN_COMPANY_DATA = "SD-03000-006";
	// 职员管理查看自己管理部门下的数据权限
	public static final String EMPLOYEE_CHECK_MYSELF_DATA = "SD-03000-002";
	// 职员管理查看职员全部详细资料权限
	public static final String EMPLOYEE_CHECK_ALL_DETAIL_DATA = "SD-03000-003";
	// 职员管理查看职员角色权限
	public static final String EMPLOYEE_CHECK_ROLE_DATA = "SD-03000-004";
	// 职员管理查看职员功能权限
	public static final String EMPLOYEE_CHECK_FUNCTION_DATA = "SD-03000-005";
	//职员管理导出收入证明
	public static final String EXPORT_PROOf_OF_INCOME = "SD-03000-009";
	//账号关闭
	public static final String EMPLOYEE_ACCOUNT_CLOSE = "SB-03000-009";

	// 权限管理
	public static final String PERMISSION_MANAGEMENT = "SM-04000-000";
	// 查看权限
	public static final String PERMISSION_CHECk_MANAGEMENT = "SD-04000-001";
	// 操作权限
	public static final String PERMISSION_OPERATE_MANAGEMENT = "SD-04000-002";

	// 一级菜单
	// 系统管理
	public static final String SYSTEM_MANAGEMENT = "SM-10000-000";
		// 功能管理
		public static final String FUNCTIONS_MANAGEMENT = "SM-11000-000";
			// 功能管理新增按钮
			public static final String FUNCTIONS_ADD_BUTTON = "SB-11000-001";
			// 功能管理新增子项按钮
			public static final String FUNCTIONS_ADD_SON_BUTTON = "SB-11000-002";
			// 功能管理修改按钮
			public static final String FUNCTIONS_UPDATE_BUTTON = "SB-11000-003";
			// 功能管理删除按钮
			public static final String FUNCTIONS_DELETE_BUTTON = "SB-11000-004";
		// 菜单功能管理
		public static final String MOBILE_FUNCTIONS_MANAGEMENT = "SM-12000-000";
		//系统初始化
		public static final String SYSTEM_INITIALIZATION = "SM-13000-000";
		//系统异常信息
		public static final String EXCEPTION_INFO = "SM-14000-000";
		//数据初始化
		public static final String DATA_INITIALIZATION = "SM-15000-000";
	//初始化数据库的按钮权限
	public static final String DATA_INITIALIZATION_BUTTON = "SB-15001-000";

	// 流程管理
	public static final String WORKFLOW_MANAGEMENT = "SM-20000-000";
	// 流程管理
	public static final String WORKFLOW_TYPE_MANAGEMENT = "SM-21000-000";
	//	流程定义
	public static final String WORKFLOW_DEFINITION = "SM-22000-000";
		//流程定义的新建权限
		public static final String WORKFLOW_DEFINITION_NEW ="SM-22000-001";
		//流程定义的修改权限
		public static final String WORKFLOW_DEFINITION_CHANGE ="SM-22000-002";
		//流程定义的流程测试权限
		public static final String WORKFLOW_DEFINITION_PROCESSOR_TEST ="SM-22000-003";
	//	流程测试结果
	public static final String WORKFLOW_TEST_RESULT = "SM-23000-000";
	//	流程节点人员修改
	public static final String WORKFLOW_UPDATE_ACTIVITY_EMPLOYEE = "SM-24000-000";
	//首页管理
	public static final String HOME_MANAGER = "SM-25000-000";
	//公司新闻
	public static final String COMPANY_NEWS = "SM-25001-000";
	//规章制度
	public static final String RULES = "SM-25002-000";
	//通知公告
	public static final String ANNOUNCEMENT = "SM-25003-000";

}
