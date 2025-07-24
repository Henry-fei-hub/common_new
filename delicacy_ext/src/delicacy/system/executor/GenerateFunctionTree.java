package delicacy.system.executor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseTreeNode;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseMainFunction;
import delicacy.system.dao.MainFunction;
import delicacy.system.handler.MainFunctionHandler;
import java.util.HashMap;
import java.util.Map;

public class GenerateFunctionTree implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        MainFunction dao = new MainFunction();
        BaseMainFunction bean = new BaseMainFunction();
        bean.setDataFromJSON(creteria);
        MainFunctionHandler.setConditions(bean, dao);
        List<BaseMainFunction> main_functions = dao.conditionalLoad();
        Map<Integer, List<BaseMainFunction>> data = generateParentNodes(main_functions);
        List<BaseTreeNode> roots = generateRoot(data, bean.getFunctionId() == null ? 0 : bean.getFunctionId());
        for (BaseTreeNode n : roots) {
            generateNode(n, data);
        }
        return BaseTreeNode.toJSON(roots);
    }

    private Map<Integer, List<BaseMainFunction>> generateParentNodes(List<BaseMainFunction> ll) {
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

    private List<BaseTreeNode> generateRoot(Map<Integer, List<BaseMainFunction>> main_functions, Integer parentId) {
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

    private void generateNode(BaseTreeNode parent, Map<Integer, List<BaseMainFunction>> main_functions) {
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
