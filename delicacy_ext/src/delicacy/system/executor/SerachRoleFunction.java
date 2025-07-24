/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delicacy.system.executor;

import delicacy.common.BaseCollection;
import delicacy.common.BaseHelpUtils;
import delicacy.common.GenericProcessor;
import delicacy.json.BasicHandler;
import delicacy.json.JSON;
import delicacy.system.bean.BaseFunction;
import delicacy.system.dao.Function;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class SerachRoleFunction implements GenericProcessor {

    @Override
    public String execute(String creteria, HttpServletRequest request) throws Exception {

        @SuppressWarnings("rawtypes")
        JSON parser = new JSON(new StringReader(creteria));
        System.out.println(parser);
        @SuppressWarnings("unchecked")
        Map<String, Object> param = (Map<String, Object>) parser.parse(new BasicHandler());
        Function dao = new Function();
        dao.setConditionEnabled("=", Boolean.TRUE);
        if (!BaseHelpUtils.isNullOrEmpty(param.get("functionName"))) {
            dao.setConditionFunctionName("like", "%" + BaseHelpUtils.getString(param.get("functionName")) + "%");
        }
        if (!BaseHelpUtils.isNullOrEmpty(param.get("functionCode"))) {
            dao.setConditionFunctionCode("like", "%" + BaseHelpUtils.getString(param.get("functionCode")) + "%");
        }
        List<BaseFunction> list = dao.conditionalLoad();
        if (BaseHelpUtils.isNullOrEmpty(list)) {
            return null;
        }
        BaseCollection<BaseFunction> bc = new BaseCollection<>();
        if (BaseHelpUtils.isNullOrEmpty(param.get("functionName")) && BaseHelpUtils.isNullOrEmpty(param.get("functionCode"))) {
            bc.setCollections(list);
            return bc.toJSON();
        }

        Set<BaseFunction> returnSet = list.stream().collect(Collectors.toSet());
        //一次性查询出所有功能权限，避免循环查询浪费IO资源
        dao.clear();
        dao.setConditionEnabled("=", Boolean.TRUE);
        List<BaseFunction> allList = dao.conditionalLoad();

        //遍历所有子功能节点
        Set<BaseFunction> allChildSet = new HashSet<>();
        Set<Integer> functionIds = list.stream().map(BaseFunction::getFunctionId).collect(Collectors.toSet());
        Set<BaseFunction> currChildSet = allList.stream().filter(p -> functionIds.contains(p.getParentId())).collect(Collectors.toSet());

        while (currChildSet != null && currChildSet.size() > 0) {
            allChildSet.addAll(currChildSet.stream().collect(Collectors.toSet()));
            Set<Integer> currFunctionIds = currChildSet.stream().map(BaseFunction::getFunctionId).collect(Collectors.toSet());
            currChildSet = allList.stream().filter(p -> currFunctionIds.contains(p.getParentId())).collect(Collectors.toSet());
        }
        returnSet.addAll(allChildSet);

        //遍历所有父功能节点
        Set<BaseFunction> allParentSet = new HashSet<>();
        Set<Integer> parentIds = list.stream().map(BaseFunction::getParentId).collect(Collectors.toSet());
        Set<BaseFunction> currParentSet = allList.stream().filter(p -> parentIds.contains(p.getFunctionId())).collect(Collectors.toSet());

        while (currParentSet != null && currParentSet.size() > 0) {
            allParentSet.addAll(currParentSet.stream().collect(Collectors.toSet()));
            Set<Integer> currParentIds = currParentSet.stream().map(BaseFunction::getParentId).collect(Collectors.toSet());
            currParentSet = allList.stream().filter(p -> currParentIds.contains(p.getFunctionId())).collect(Collectors.toSet());
        }
        returnSet.addAll(allParentSet);

        bc.setCollections(new ArrayList<>(returnSet));
        return bc.toJSON();
    }

}
