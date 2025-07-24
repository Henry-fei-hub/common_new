package delicacy.system.executor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseTreeNode;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseDepartment;
import delicacy.system.dao.Department;
import java.util.Objects;

public class GenerateDepartmentTree implements GenericProcessor {

	@Override
	public String execute(String creteria, HttpServletRequest request) throws Exception {
		Department dao = new Department();
		BaseDepartment bean = new BaseDepartment();
		bean.setDataFromJSON(creteria);
		List<BaseDepartment> departments = dao.conditionalLoad();
		List<BaseTreeNode> roots = generateRoot(departments, bean.getDepartmentId() == null ? 0 : bean.getDepartmentId());
		for(BaseTreeNode n : roots){
			generateNode(n, departments);
		}
		return BaseTreeNode.toJSON(roots);
	}
	
	private List<BaseTreeNode> generateRoot(List<BaseDepartment> departments, Integer parentId){
		List<BaseTreeNode> roots = new ArrayList<>();
		for(BaseDepartment bd : departments){
			if(Objects.equals(bd.getParentId(), parentId)){
				BaseTreeNode n = new BaseTreeNode();
				n.setId(bd.getDepartmentId());
				n.setTitle(bd.getDepartmentName());
				n.setAttr(bd);
				roots.add(n);
			}
		}
		return roots;
	}
	
	private void generateNode(BaseTreeNode parent, List<BaseDepartment> departments){
		List<BaseTreeNode> children = new ArrayList<>();
		for(BaseDepartment bd : departments){
			if(Objects.equals(bd.getParentId(), parent.getId())){
				BaseTreeNode node = new BaseTreeNode();
				node.setId(bd.getDepartmentId());
				node.setTitle(bd.getDepartmentName());
				node.setAttr(bd);
				children.add(node);
			}
		}
		if(!children.isEmpty()){
			parent.setChildren(children);
			for(BaseTreeNode n : children){
				generateNode(n, departments);
			}
		}
	}

}
