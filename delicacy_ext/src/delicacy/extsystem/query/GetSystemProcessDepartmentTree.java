package delicacy.extsystem.query;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseTreeNode;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseSystemProcessDepartment;
import delicacy.system.dao.SystemProcessDepartment;
import delicacy.system.handler.SystemProcessDepartmentHandler;

public class GetSystemProcessDepartmentTree implements GenericProcessor {

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		List<BaseTreeNode> roots = generateTree(creteria);
		return BaseTreeNode.toJSON(roots);
	}

	public static List<BaseTreeNode> generateTree(String creteria) throws Exception {
		BaseSystemProcessDepartment bean = new BaseSystemProcessDepartment();
		bean.setDataFromJSON(creteria);
		SystemProcessDepartment dao = new SystemProcessDepartment();
		SystemProcessDepartmentHandler.setConditions(bean, dao);
		List<BaseSystemProcessDepartment> ll = dao.conditionalLoad();
		List<BaseTreeNode> roots = GenerateSourceDepartmentTree.generateTree();
		for (BaseSystemProcessDepartment l : ll) {
			BaseTreeNode node = BaseTreeNode.getNodeById(roots, l.getDepartmentId());
			if (node == null)
				continue;
			node.setChecked(true);
			node.setUserData(l.toMap());
		}
		return roots;
	}

}
