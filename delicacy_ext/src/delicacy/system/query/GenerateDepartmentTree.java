package delicacy.system.query;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseTreeNode;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseDepartment;
import delicacy.system.dao.Department;

public class GenerateDepartmentTree implements GenericProcessor {

    public static void main(String[] args) {
        try {
            List<BaseTreeNode> roots = generateTree();
            BaseTreeNode res = BaseTreeNode.getNodeById(roots, 2);
            res.setChecked(true);
            Map<String, Object> params = new HashMap<>();
            params.put("processId", 10);
            params.put("processDepartmentId", 2);
            res.setUserData(params);
            System.out.printf("%1$s\n", res.toJSON());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        List<BaseTreeNode> roots = generateTree();
        return BaseTreeNode.toJSON(roots);
    }

    public static List<BaseTreeNode> generateTree() throws Exception {
        Department dao = new Department();
        dao.unsetSelectFlags();
        dao.setSelectDepartmentId(true);
        dao.setSelectDepartmentName(true);
        dao.setSelectParentId(true);
        List<BaseDepartment> departments = dao.conditionalLoad("order by department_id");
        Map<Integer, List<BaseDepartment>> data = generateParentNodes(departments);
        List<BaseTreeNode> roots = generateRoot(data, 0);
        for (BaseTreeNode n : roots) {
            generateNode(n, data);
        }
        return roots;
    }

    public static Map<Integer, List<BaseDepartment>> generateParentNodes(List<BaseDepartment> ll) {
        Map<Integer, List<BaseDepartment>> parentNodes = new HashMap<>();
        for (BaseDepartment l : ll) {
            Integer parentId = l.getParentId();
            List<BaseDepartment> children = parentNodes.get(parentId);
            if (children == null) {
                children = new ArrayList<>();
                parentNodes.put(parentId, children);
            }
            children.add(l);
        }
        return parentNodes;
    }

    public static List<BaseTreeNode> generateRoot(Map<Integer, List<BaseDepartment>> departments, Integer parentId) {
        List<BaseTreeNode> roots = new ArrayList<>();
        for (BaseDepartment bd : departments.get(parentId)) {
            BaseTreeNode n = new BaseTreeNode();
            n.setId(bd.getDepartmentId());
            n.setTitle(bd.getDepartmentName());
            n.setAttr(bd);
            roots.add(n);
        }
        return roots;
    }

    public static void generateNode(BaseTreeNode parent, Map<Integer, List<BaseDepartment>> departments) {
        List<BaseTreeNode> children = new ArrayList<>();
        List<BaseDepartment> childList = departments.get(parent.getId());
        if (childList == null || childList.isEmpty()) {
            return;
        }
        for (BaseDepartment bd : childList) {
            BaseTreeNode n = new BaseTreeNode();
            n.setId(bd.getDepartmentId());
            n.setTitle(bd.getDepartmentName());
            n.setAttr(bd);
            children.add(n);
        }
        if (!children.isEmpty()) {
            parent.setChildren(children);
            for (BaseTreeNode n : children) {
                generateNode(n, departments);
            }
        }
    }

}
