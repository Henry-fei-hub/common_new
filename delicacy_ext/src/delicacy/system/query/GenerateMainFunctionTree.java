package delicacy.system.query;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import delicacy.common.BaseTreeNode;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseMainFunction;
import delicacy.system.dao.MainFunction;

public class GenerateMainFunctionTree implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        List<BaseTreeNode> roots = generateTree();
        return BaseTreeNode.toJSON(roots);
    }

    public static List<BaseTreeNode> generateTree() throws Exception {
        MainFunction dao = new MainFunction();
        dao.unsetSelectFlags();
        dao.setSelectFunctionId(true);
        dao.setSelectFunctionName(true);
        dao.setSelectParentId(true);
        dao.setSelectExecuteName(true);
        List<BaseMainFunction> main_functions = dao.conditionalLoad("function_type = 1 order by function_id");
        Map<Integer, List<BaseMainFunction>> data = generateParentNodes(main_functions);
        List<BaseTreeNode> roots = generateRoot(data, 0);
        for (BaseTreeNode n : roots) {
            generateNode(n, data);
        }
        return roots;
    }

    public static Map<Integer, List<BaseMainFunction>> generateParentNodes(List<BaseMainFunction> ll) {
        Map<Integer, List<BaseMainFunction>> parentNodes = new HashMap<>();
        for (BaseMainFunction l : ll) {
            Integer parentId = l.getParentId();
            List<BaseMainFunction> children = parentNodes.get(parentId);
            if (children == null) {
                children = new ArrayList<>();
                parentNodes.put(parentId, children);
            }
            children.add(l);
        }
        return parentNodes;
    }

    public static List<BaseTreeNode> generateRoot(Map<Integer, List<BaseMainFunction>> main_functions, Integer parentId) {
        List<BaseTreeNode> roots = new ArrayList<>();
        for (BaseMainFunction bd : main_functions.get(parentId)) {
            BaseTreeNode n = new BaseTreeNode();
            n.setId(bd.getFunctionId());
            n.setTitle(bd.getFunctionName());
            n.setAttr(bd);
            roots.add(n);
        }
        return roots;
    }

    public static void generateNode(BaseTreeNode parent, Map<Integer, List<BaseMainFunction>> main_functions) {
        List<BaseTreeNode> children = new ArrayList<>();
        List<BaseMainFunction> childList = main_functions.get(parent.getId());
        if (childList == null || childList.isEmpty()) {
            return;
        }
        for (BaseMainFunction bd : childList) {
            BaseTreeNode n = new BaseTreeNode();
            n.setId(bd.getFunctionId());
            n.setTitle(bd.getFunctionName());
            n.setAttr(bd);
            children.add(n);
        }
        if (!children.isEmpty()) {
            parent.setChildren(children);
            for (BaseTreeNode n : children) {
                generateNode(n, main_functions);
            }
        }
    }

}
