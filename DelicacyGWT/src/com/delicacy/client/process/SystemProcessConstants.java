package com.delicacy.client.process;

/**
 *
 * @author Peter
 */
public final class SystemProcessConstants {

    // 该流程处理引擎主要处理需要人为参与的各种审批,业务处理各个环节中需要人相互协作的业务类型
    // 流程处理过程中,不应出现单独的后台处理单元,本系统认为这些处理应该是包含在每个流程节点的业务处理程序中
    // 所以本流程引擎不是一个标准的BPM引擎,而是主要管理人与人之间业务协作的
    // 本流程引擎通过每个流程类型中的执行程序名称实现和具体业务处理单元的松散耦合关系
    // 每个业务处理单元都是GenericWorkflowProcessor接口的实现者,每个业务处理单元可以实现在流程处理的过程中对业务数据的新建
    // 修改,审核,发布等操作
    
    // 新建的流程
    public final static int PROCESS_STATUS_NEW_CREATED = 0;
    // 流程正在工作中
    public final static int PROCESS_STATUS_WORKING = 1;
    // 流程被取消
    public final static int PROCESS_STATUS_CANCELLED = 2;
    // 流程完成
    public final static int PROCESS_STATUS_COMPLETED = 3;
    // 流程暂停，一般发生在无法准确地找到下一个执行流程的人，只能靠认为干预
    // 比如将任务放入任务池，由符合条件的人去认领，或者由主管去分配
    public final static int PROCESS_STATUS_PAUSE = 4;
    // 流程被驳回
    public final static int PROCESS_STATUS_REJECT = 5;

    // 流程活动节点的ID，0-代表开始，999-代表结束
    public final static int PROCESS_FIRST_ACTIVITY = 0;
    public final static int PROCESS_LAST_ACTIVITY = 999;

    // 流程活动状态，0-代表新建，还没有开始处理
    public final static int ACTIVITY_STATUS_NEW_CREATED = 0;
    // 该活动节点当前处在活动状态
    public final static int ACTIVITY_STATUS_ACTIVE = 1;
    // 该活动节点已经被处理过，并通过，流程进入下一环节
    public final static int ACTIVITY_STATUS_PASS = 2;
    // 该流程已被处理，并被拒绝，流程结束
    public final static int ACTIVITY_STATUS_REJECT = 3;
    // 该活动已被处理，并被处理人拒绝，该流程将返回给流程提交人，流程
    // 要么取消该流程，要么重新修改流程，重新开始流程
    public final static int ACTIVITY_STATUS_RETURN_FIRST = 4;
    // 该活动已被处理，被处理人要求返回上一步
    public final static int ACTIVITY_STATUS_RETURN_LAST = 5;
    // 该活动已被处理，被处理人要求把审批权转移给其他人
    public final static int ACTIVITY_STATUS_REASSIGN = 6;
    // 该活动已被放入任务池，因为我们发现有多人符合条件来处理该事项
    public final static int ACTIVITY_STATUS_POOLED = 7;
    
    // 任务池状态 - 新建
    public final static int POOLED_TASK_STATUS_NEW = 0;
    // 任务池状态 - 当前活动
    public final static int POOLED_TASK_STATUS_ACTIVE = 1;
    // 任务池状态 - 已处理
    public final static int POOLED_TASK_STATUS_DONE = 2;
    
    // 知会状态 0 - 新建
    public final static int ATTENTION_STATUS_NEW = 0;
    // 知会状态 1 - 活动，知会人可以看到
    public final static int ATTENTION_STATUS_ACTIVE = 1;
    // 知会状态 2 - 已阅，知会人已经知道了
    public final static int ATTENTION_STATUS_DONE = 2;
    
    // 流程申请/创建人/上一个活动节点的上级来处理
    public final static int EXECUTOR_TYPE_SUPER = 0;
    // 指定某个部门的某个角色来做
    public final static int EXECUTOR_TYPE_DEPARTMENT_ROLE = 1;
    // 不指定部门,但指定角色,由上一个活动节点处理人所在部门推断而来
    // 如果是由系统自动创建的流程,开始处理节点肯定是系统,但下一个节点必须要指定角色,如果担任该角色的人员超过1人,则自动将任务放入任务池
    public final static int EXECUTOR_TYPE_ROLE = 2;
    // 指定某个具体的人来做
    public final static int EXECUTOR_TYPE_EMPLOYEE = 3;

    // 如果最终查出能够做这个事情的人不止一个,系统会自动将该项任务放入任务池,由符合条件的该角色的人去认领,如果在指定的时间内无人认领,则由该角色的上级角色
    // 去分配该任务
    
    // 流程节点类型 - 0 开始
    public final static int ACTIVITY_TYPE_START = 0;
    // 流程节点类型 - 1 结束
    public final static int ACTIVITY_TYPE_END = 1;
    // 流程节点类型 - 2 审核
    public final static int ACTIVITY_TYPE_APPROVAL = 2;
    // 流程节点类型 - 3 处理
    public final static int ACTIVITY_TYPE_PROCESS = 3;
    // 流程节点类型 - 4 知会
    public final static int ACTIVITY_TYPE_ATTENTION = 4;
    //任务池类型： 0 并行
    public final static int POOL_TYPE_0 = 0;
    //任务池类型： 1 串行
    public final static int POOL_TYPE_1 = 1;
}
