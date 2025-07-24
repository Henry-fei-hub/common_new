package delicacy.system.executor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import delicacy.common.BaseTreeNode;
import delicacy.common.GenericProcessor;
import delicacy.system.bean.BaseMobileFunction;
import delicacy.system.dao.MobileFunction;
import java.util.Objects;

public class GetFunctionTree implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {
        MobileFunction dao = new MobileFunction();
        BaseMobileFunction bean = new BaseMobileFunction();
        bean.setDataFromJSON(creteria);
        dao.setConditionFunctionType("=", 1);
        List<BaseMobileFunction> functions = dao.conditionalLoad("order by function_code");

        List<BaseTreeNode> roots = generateRoot(functions, bean.getFunctionId() == null ? 0 : bean.getFunctionId());
        for (BaseTreeNode n : roots) {
            generateNode(n, functions);
        }
        return BaseTreeNode.toJSON(roots);
    }

    private List<BaseTreeNode> generateRoot(List<BaseMobileFunction> functions, Integer parentId) {
        List<BaseTreeNode> roots = new ArrayList<>();
        for (BaseMobileFunction bd : functions) {
            if (Objects.equals(bd.getParentId(), parentId)) {
                BaseTreeNode n = new BaseTreeNode();
                n.setId(bd.getFunctionId());
                n.setTitle(bd.getFunctionName());
                n.setAttr(bd);
                roots.add(n);
            }
        }
        return roots;
    }

    private void generateNode(BaseTreeNode parent, List<BaseMobileFunction> functions) {
        List<BaseTreeNode> children = new ArrayList<>();
        for (BaseMobileFunction bd : functions) {
            if (Objects.equals(bd.getParentId(),parent.getId())) {
                BaseTreeNode node = new BaseTreeNode();
                node.setId(bd.getFunctionId());
                node.setTitle(bd.getFunctionName());
                node.setAttr(bd);
                children.add(node);
            }
        }
        if (!children.isEmpty()) {
            parent.setChildren(children);
            for (BaseTreeNode n : children) {
                generateNode(n, functions);
            }
        }
    }

}
