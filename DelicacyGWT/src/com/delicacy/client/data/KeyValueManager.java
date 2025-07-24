package com.delicacy.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.delicacy.client.BaseHelpUtils;
import com.delicacy.client.MapUtils;
import com.delicacy.client.domain.data.BDomainValue;
import com.delicacy.client.domain.data.CDomainValue;
import com.delicacy.client.websocket.SyncHttpRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeNode;

public class KeyValueManager {

    private final static String CONST_KEYCOLUMN = "keyColumn";
    private final static String CONST_VALUECOLUMN = "valueColumn";
    private final static String CONST_TABLECOLUMN = "tableName";
    private final static String CONST_SELECTID = "selectId";
    private final static String CONST_CONDITION_COLUMN = "conditionColumn";
    private final static String CONST_QUERY_OPERATION = "NQ_DomainValue";
    private final static String CONST_ADD_OPERATION = "EP_AddDomainValue";
    private final static long TIMEOUT_TIME = 600000L;
    private static String __DOMAINVALUE = "DomainValue_DELICACY";
    private static final String LASTTIMELOADDATA = "LASTTIMELOADDATA";
    public static Map<String, String> NONSTANDARDVALUES = new HashMap<>();
    public static List<DSCallback> CALLBACKS = new ArrayList<>();

    public static LinkedHashMap generateHashMapFromRecord(Record[] data) {
        LinkedHashMap res = new LinkedHashMap();
        if (data == null || data.length == 0) {
            return res;
        }
        for (Record r : data) {
            if (r.getAttribute(CONST_KEYCOLUMN) == null) {
                continue;
            }
            res.put(r.getAttribute(CONST_KEYCOLUMN), r.getAttribute(CONST_VALUECOLUMN));
        }
        return res;
    }

    public static void loadValueMap(final String selectId, final DataSourceField listGridField) {
        long selectTime = getSelectTime(selectId);
        if (selectTime != -1 && System.currentTimeMillis() - selectTime < TIMEOUT_TIME) {
            listGridField.setValueMap(getValueMap(selectId));
            return;
        }
        Map criteria = new HashMap();
        criteria.put(CONST_SELECTID, selectId);
        DBDataSource.callOperation(CONST_QUERY_OPERATION, criteria, new DSCallback() {
            @Override
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                putDataIntoKeyValues(response.getData());
                listGridField.setValueMap(getValueMap(selectId));
            }
        });
    }
    
    public static void loadValueMap(final String selectId, final DataSourceField listGridField, boolean cache) {
        if (cache) {
            long selectTime = getSelectTime(selectId);
            if (selectTime != -1 && System.currentTimeMillis() - selectTime < TIMEOUT_TIME) {
                listGridField.setValueMap(getValueMap(selectId));
                return;
            }
        }
        Map criteria = new HashMap();
        criteria.put(CONST_SELECTID, selectId);
        DBDataSource.callOperation(CONST_QUERY_OPERATION, criteria, new DSCallback() {
            @Override
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                putDataIntoKeyValues(response.getData());
                listGridField.setValueMap(getValueMap(selectId));
            }
        });
    }

    public static void loadValueMap(final String selectId, final ListGridField listGridField) {
        loadValueMap(selectId, listGridField, true);
    }

    public static void loadValueMap(final String selectId, final ListGridField listGridField, boolean cache) {
        if (cache) {
            long selectTime = getSelectTime(selectId);
            if (selectTime != -1 && System.currentTimeMillis() - selectTime < TIMEOUT_TIME) {
                listGridField.setValueMap(getValueMap(selectId));
                return;
            }
        }
        Map criteria = new HashMap();
        criteria.put(CONST_SELECTID, selectId);
        DBDataSource.callOperation(CONST_QUERY_OPERATION, criteria, new DSCallback() {
            @Override
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                putDataIntoKeyValues(response.getData());
                listGridField.setValueMap(getValueMap(selectId));
            }
        });
    }
    
    public static String getDirectValueFromServer(final String selectId, String val){
        String res = getValue(selectId, val);
        if(res != null) return res;
        SyncHttpRequest req = new SyncHttpRequest();
        Map criteria = new HashMap();
        criteria.put(CONST_SELECTID, selectId);
        String requestData = DBDataSource.generateParameterString(CONST_QUERY_OPERATION, "", MapUtils.toJSON(criteria));
        String resp = req.getData(requestData);
        try {
            DSResponse dsr = DBDataSource.processHttpResponse(resp);
            putDataIntoKeyValues(dsr.getData());
            return getValue(selectId, val);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void loadValueMap(final String selectId, final FormItem selectItem) {
        loadValueMap(selectId, selectItem, true);
    }

    public static void loadValueMap(final String selectId, final FormItem selectItem, boolean cache) {
        if (cache) {
            long selectTime = getSelectTime(selectId);
            if (selectTime != -1 && System.currentTimeMillis() - selectTime < TIMEOUT_TIME) {
                selectItem.setValueMap(getValueMap(selectId));
                return;
            }
        }
        Map criteria = new HashMap();
        criteria.put(CONST_SELECTID, selectId);
        DBDataSource.callOperation(CONST_QUERY_OPERATION, criteria, new DSCallback() {
            @Override
            public void execute(DSResponse response, Object rawData, DSRequest request) {
                putDataIntoKeyValues(response.getData());
                selectItem.setValueMap(getValueMap(selectId));
            }
        });

    }
    
    public static void loadTree(final String selectId, final IPickTreeItem treeItem) {
    	loadTree(selectId, treeItem, true);
    }
    
    public static void loadTree(final String selectId, final IPickTreeItem treeItem, boolean cache) {
    	SC.debugger();
    	if (cache) {
    		long selectTime = getSelectTime(selectId);
    		if (selectTime != -1 && System.currentTimeMillis() - selectTime < TIMEOUT_TIME) {
    			treeItem.setValueTree(getTree(selectId));
    			return;
    		}
    	}
    	Map criteria = new HashMap();
    	criteria.put(CONST_SELECTID, selectId);
    	DBDataSource.callOperation(CONST_QUERY_OPERATION, criteria, new DSCallback() {
    		@Override
    		public void execute(DSResponse response, Object rawData, DSRequest request) {
    			putDataIntoKeyValues(response.getData());
    			treeItem.setValueTree(getTree(selectId));
    		}
    	});
    }

    public static void load() {
        load(false);
    }

	public static void loadUnStartup() {
		try {
			WebWorker ww = new WebWorker(DBDataSource.getBaseURL() + "js/delicacy/usclientworker.js");
			ww.addListener(new WebWorkerListener() {

				@Override
				public void onMessage(String msg) {
					JSONValue result = JSONParser.parseLenient(msg);
					JSONObject jso = result.isObject();
					if(null != jso.get("pc")) {
						processUnStartupDomainValues(jso.get("pc").toString());
					}
				}
			});
			ww.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void load(boolean isCache) {
		if (CALLBACKS == null) {
			CALLBACKS = new ArrayList<>();
		}
		if (NONSTANDARDVALUES == null) {
			NONSTANDARDVALUES = new HashMap<>();
		}
		if (NONSTANDARDVALUES.isEmpty()) {
			NONSTANDARDVALUES.put("processExecutors", "Msystemprocesssor");
		}
		if (STORAGE != null) {
			String originalData = STORAGE.getItem(__DOMAINVALUE);
			if (originalData != null && originalData.startsWith("{")) {
				processDomainValues(originalData);
			}
		}
		if (STORAGE != null && isCache) {
			String lastLoadedTime = STORAGE.getItem(LASTTIMELOADDATA);
			if (lastLoadedTime != null) {
				if (System.currentTimeMillis() - Long.parseLong(lastLoadedTime) < ClientUtil.DAYTIME) {
					loadUnStartup();
					return;
				}
			}
		}
		CDomainValue cdv = new CDomainValue();
		cdv.setLoadOnStartup(true);
		cdv.setCurrentPage(0);
		DBDataSource.callOperation(CONST_QUERY_OPERATION, cdv.toJSON(), new Callback<String, Throwable>() {
			@Override
			public void onFailure(Throwable reason) {
				__LOGGER.severe(reason.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				if (result == null || result.trim().length() <= 0) {
					SC.say("Load value map failure .......");
					return;
				}
				try {
					if (STORAGE != null) {
						STORAGE.setItem(LASTTIMELOADDATA, String.valueOf(System.currentTimeMillis()));
						String originalData = STORAGE.getItem(__DOMAINVALUE);
						if (result.equals(originalData)) {
							for (DSCallback cb : CALLBACKS) {
								cb.execute(null, result, null);
							}
							loadNonstandardValues();
							loadUnStartup();
							return;
						}
						processDomainValues(result);
						STORAGE.removeItem(__DOMAINVALUE);
						STORAGE.setItem(__DOMAINVALUE, result);
					} else {
						processDomainValues(result);
					}
					for (DSCallback cb : CALLBACKS) {
						cb.execute(null, result, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				loadNonstandardValues();
				loadUnStartup();
			}
		});
	}

    public static void loadNonstandardValues() {
        for (String s : NONSTANDARDVALUES.keySet()) {
            getValueMapFromQuery(s, NONSTANDARDVALUES.get(s));
        }
    }

    public static void addDomainValue(final String selectID, final String newVal, final FormItem item) {
        int ret = 0;
        if (newVal == null || newVal.trim().length() == 0) {
            return;
        }
        if ((ret = isKeyOrValueExists(selectID, newVal)) > 0) {
            if (ret == 2) {
                item.setValue(getKey(selectID, newVal));
            }
            return;
        }
        Map params = new HashMap();
        params.put(CONST_SELECTID, selectID);
        params.put(CONST_VALUECOLUMN, newVal);
        DBDataSource.callOperation(CONST_ADD_OPERATION, params, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    putDataIntoKeyValues(dsResponse.getData());
                    item.setValueMap(getValueMap(selectID));
                    item.setValue(getKey(selectID, newVal));
                }
            }
        });
    }

    public static void setValueMapFromQuery(String queryName, final FormItem item) {
        setValueMapFromQuery(queryName, "{}", null, null, item);
    }

    public static void setValueMapFromQuery(String queryName, Map params, final FormItem item) {
        setValueMapFromQuery(queryName, MapUtils.toJSON(params), null, null, item);
    }

    public static void setValueMapFromQuery(String queryName, String queryCondition, final FormItem item) {
        setValueMapFromQuery(queryName, queryCondition, null, null, item);
    }

	public static void setValueMapFromQuery(String queryName, String queryCondition, final String idName,
											final String valName, final FormItem item) {
		DBDataSource.callOperation(queryName, queryCondition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					String idFieldName = idName == null ? "idd" : idName;
					String valFieldName = valName == null ? "val" : valName;
					LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
					if (dsResponse.getData() != null && dsResponse.getData().length > 0) {
						for (Record r : dsResponse.getData()) {
							lhm.put(r.getAttribute(idFieldName), r.getAttribute(valFieldName));
						}
					}
					item.setValueMap(lhm);
				}
			}
		});
	}

    public static void getValueMapFromQuery(final String selectName, String queryName) {
        getValueMapFromQuery(selectName, queryName, "{}", null, null);
    }

    public static void getValueMapFromQuery(final String selectName, String queryName, String queryCondition) {
        getValueMapFromQuery(selectName, queryName, queryCondition, null, null);
    }

	public static void getValueMapFromQuery(final String selectName, String queryName, String queryCondition,
											final String idName, final String valName) {
		if (!queryName.startsWith("NQ_")) {
			queryName = "NQ_" + queryName;
		}
		if (queryCondition == null || queryCondition.isEmpty()) {
			queryCondition = "{}";
		}
		DBDataSource.callOperation(queryName, queryCondition, new DSCallback() {
			@Override
			public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest) {
				if (dsResponse.getStatus() >= 0) {
					String idFieldName = idName == null ? "idd" : idName;
					String valFieldName = valName == null ? "val" : valName;
					LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
					if (dsResponse.getData() != null && dsResponse.getData().length > 0) {
						for (Record r : dsResponse.getData()) {
							lhm.put(r.getAttribute(idFieldName), r.getAttribute(valFieldName));
						}
					}
					KeyValueTime kvt = KEYVALUES.get(selectName);
					if (kvt == null) {
						kvt = new KeyValueTime();
						kvt.setValues(lhm);
						kvt.setCurrentTime(System.currentTimeMillis());
						KEYVALUES.put(selectName, kvt);
					} else {
						kvt.setValues(lhm);
					}
				}
			}
		});
	}

    public static void load(String selectId, final ListBox listBox) {
        CDomainValue cdv = new CDomainValue();
        cdv.setSelectId(selectId);
        cdv.setCurrentPage(0);
        DBDataSource.callOperation(CONST_QUERY_OPERATION, cdv.toJSON(), new Callback<String, Throwable>() {
            @Override
            public void onFailure(Throwable reason) {
                __LOGGER.severe(reason.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                List<KeyValue> lkv = getKeyValueListFromJSON(result);
                listBox.clear();
                for (KeyValue kv : lkv) {
                    listBox.addItem(kv.getValue(), kv.getKey());
                }
            }
        });
    }

    private static List<KeyValue> getKeyValueListFromJSON(String json) {
        List<KeyValue> res = new ArrayList<>();
        BCollection<BDomainValue> bc = new BCollection<>();
        bc.setDataFromJSON(BDomainValue.newInstance(), json);
        for (BDomainValue bd : bc.getData()) {
            if (bd.getKeyColumn() == null) {
                continue;
            }
            KeyValue kv = new KeyValue(bd.getKeyColumn(), bd.getValueColumn(), bd.getConditionColumn());
            res.add(kv);
        }
        return res;
    }

    private static void processDomainValues(String json) {
        BCollection<BDomainValue> bc = new BCollection<>();
        bc.setDataFromJSON(BDomainValue.newInstance(), json);
        KEYVALUES.clear();
        for (BDomainValue bd : bc.getData()) {
            if (bd.getKeyColumn() == null) {
                continue;
            }
            KeyValue kv = new KeyValue(bd.getKeyColumn(), bd.getValueColumn(), bd.getConditionColumn());
            KeyValueTime kvt = KEYVALUES.get(bd.getTableName());
            if (kvt == null) {
                kvt = new KeyValueTime();
                LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
                kvt.setValues(kvs);
                kvt.setCurrentTime(System.currentTimeMillis());
                kvs.put(bd.getKeyColumn(), bd.getValueColumn());
                KEYVALUES.put(bd.getTableName(), kvt);
            } else {
                kvt.getValues().put(bd.getKeyColumn(), bd.getValueColumn());
            }
            if (bd.getConditionColumn() != null && bd.getConditionColumn().trim().length() > 0) {
                String keyName = bd.getTableName() + "_" + bd.getConditionColumn();
                kvt = KEYVALUES.get(keyName);
                if (kvt == null) {
                    kvt = new KeyValueTime();
                    LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
                    kvt.setValues(kvs);
                    kvt.setCurrentTime(System.currentTimeMillis());
                    kvs.put(bd.getKeyColumn(), bd.getValueColumn());
                    KEYVALUES.put(keyName, kvt);
                } else {
                    kvt.getValues().put(bd.getKeyColumn(), bd.getValueColumn());
                }
            }
        }
        __LOGGER.info(getLogMessage("parse json success, total: ", KEYVALUES.size()));
    }

	private static void processUnStartupDomainValues(String json) {
		BCollection<BDomainValue> bc = new BCollection<>();
		bc.setDataFromJSON(BDomainValue.newInstance(), json);
		for (BDomainValue bd : bc.getData()) {
			if (bd.getKeyColumn() == null) {
				continue;
			}
			KeyValue kv = new KeyValue(bd.getKeyColumn(), bd.getValueColumn(), bd.getConditionColumn());
			KeyValueTime kvt = KEYVALUES.get(bd.getTableName());
			if (kvt == null) {
				kvt = new KeyValueTime();
				LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
				kvt.setValues(kvs);
				kvt.setCurrentTime(System.currentTimeMillis());
				kvs.put(bd.getKeyColumn(), bd.getValueColumn());
				KEYVALUES.put(bd.getTableName(), kvt);
			} else {
				kvt.getValues().put(bd.getKeyColumn(), bd.getValueColumn());
			}
			if (bd.getConditionColumn() != null && bd.getConditionColumn().trim().length() > 0) {
				String keyName = bd.getTableName() + "_" + bd.getConditionColumn();
				kvt = KEYVALUES.get(keyName);
				if (kvt == null) {
					kvt = new KeyValueTime();
					LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
					kvt.setValues(kvs);
					kvt.setCurrentTime(System.currentTimeMillis());
					kvs.put(bd.getKeyColumn(), bd.getValueColumn());
					KEYVALUES.put(keyName, kvt);
				} else {
					kvt.getValues().put(bd.getKeyColumn(), bd.getValueColumn());
				}
			}
		}
	}

	public static int getKeyIndex(String id, String val) {
		KeyValueTime kvs = KEYVALUES.get(id);
		if (kvs == null) {
			return -1;
		}
		int count = 0;
		for (String kv : kvs.getValues().keySet()) {
			if (kv.equals(val)) {
				return count;
			}
			count++;
		}
		return -1;
	}

    public static String getIndexKey(String id, int index) {
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            return null;
        }
        int count = 0;
        for (String kv : kvs.getValues().keySet()) {
            if (count == index) {
                return kv;
            }
            count++;
        }
        return null;
    }

    public static String getKey(String id, String val) {
        String res = "";
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            return res;
        }
        for (String kv : kvs.getValues().keySet()) {
            if (kvs.getValues().get(kv).equals(val)) {
                return kv;
            }
        }
        return res;
    }

    public static String getValue(String id, String val) {
        if (val == null) {
            return null;
        }
        String res = null;
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            return res;
        }
        return kvs.getValues().get(val);
    }

    public static List<String> getList(String id) {
        List<String> res = new ArrayList<>();
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            return res;
        }
        res.addAll(kvs.getValues().values());
        return res;
    }

    public static int isKeyOrValueExists(String id, String val) {
        if (val == null) {
            return 0;
        }
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            return 0;
        }
        if (kvs.getValues().containsKey(val)) {
            return 1;
        }
        if (kvs.getValues().values().contains(val)) {
            return 2;
        }
        return 0;
    }

    public static long getSelectTime(String id) {
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            return -1;
        }
        return kvs.getCurrentTime();
    }

    public static KeyValueTime putDataIntoKeyValues(Record[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        String selectID = data[0].getAttribute(CONST_TABLECOLUMN);
        if (KEYVALUES.get(selectID) != null) {
            KEYVALUES.remove(selectID);
        }
//        KeyValueTime kvt = new KeyValueTime();
//        kvt.setCurrentTime(System.currentTimeMillis());
//        LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
//        kvt.setValues(kvs);
//        for (Record r : data) {
//            if (r.getAttribute(CONST_KEYCOLUMN) == null) {
//                continue;
//            }
//            kvs.put(r.getAttribute(CONST_KEYCOLUMN), r.getAttribute(CONST_VALUECOLUMN));
//        }
//        KEYVALUES.put(selectID, kvt);
//        return kvt;
        for(Record r : data) {
        	KeyValueTime kvt = KEYVALUES.get(selectID);
        	if (kvt == null) {
    			kvt = new KeyValueTime();
    			LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
    			kvt.setValues(kvs);
    			kvt.setCurrentTime(System.currentTimeMillis());
    			kvs.put(r.getAttribute(CONST_KEYCOLUMN), r.getAttribute(CONST_VALUECOLUMN));
    			KEYVALUES.put(selectID, kvt);
    		} else {
    			kvt.getValues().put(r.getAttribute(CONST_KEYCOLUMN), r.getAttribute(CONST_VALUECOLUMN));
    		}
        	String conditionColumn = r.getAttribute(CONST_CONDITION_COLUMN);
        	if (null != conditionColumn && conditionColumn.trim().length() > 0) {
    			String keyName = selectID + "_" + conditionColumn;
    			kvt = KEYVALUES.get(keyName);
    			if (kvt == null) {
    				kvt = new KeyValueTime();
    				LinkedHashMap<String, String> kvs = new LinkedHashMap<>();
    				kvt.setValues(kvs);
    				kvt.setCurrentTime(System.currentTimeMillis());
    				kvs.put(r.getAttribute(CONST_KEYCOLUMN), r.getAttribute(CONST_VALUECOLUMN));
    				KEYVALUES.put(keyName, kvt);
    			} else {
    				kvt.getValues().put(r.getAttribute(CONST_KEYCOLUMN), r.getAttribute(CONST_VALUECOLUMN));
    			}
    		}
        }
        return KEYVALUES.get(selectID);
    }

    public static LinkedHashMap<String, String> getValueMap(String id) {
        return getValueMap(id, false);
    }

    public static LinkedHashMap<String, String> getValueMap(String id, boolean showId) {
        LinkedHashMap<String, String> res = new LinkedHashMap<>();
        KeyValueTime kvs = KEYVALUES.get(id);
        if (kvs == null) {
            __LOGGER.severe(getLogMessage("could not find select id : ", id));
            return res;
        }
        // __LOGGER.info(kvs.getValues().toString());
        return kvs.getValues();
    }

    public static LinkedHashMap<String, String> getValueMap(String id, String con) {
        return getValueMap(id, con, false);
    }

    public static LinkedHashMap<String, String> getValueMap(String id, String con, boolean showID) {
        LinkedHashMap<String, String> res = new LinkedHashMap<>();
        if (con == null || con.trim().length() == 0) {
            return res;
        }
        String keyName = id + "_" + con;
        KeyValueTime kvs = KEYVALUES.get(keyName);
        if (kvs == null) {
            return res;
        }
        return kvs.getValues();
    }

    public static String getLogMessage(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object val : args) {
            sb.append(val);
        }
        return sb.toString();
    }

    public static Tree getTreeGridData(String keyName) {
        return getTreeGridData(keyName, "0");
    }

    public static Tree getTreeGridData(String keyName, String rootValue) {
        Tree tree = new Tree();
        tree.setModelType(TreeModelType.PARENT);
        tree.setRootValue(rootValue);
        tree.setNameProperty("name");
        tree.setIdField("id");
        tree.setParentIdField("parentId");
        tree.setOpenProperty("isOpen");

        LinkedHashMap<String, String> vals = getValueMap(keyName, rootValue);
        if (ClientUtil.isNullOrEmpty(vals)) {
            return tree;
        }
        TreeNode[] nodes = new TreeNode[vals.size()];
        int count = 0;
        for (String id : vals.keySet()) {
            nodes[count] = new TreeNode();
            nodes[count].setAttribute("id", id);
            nodes[count].setAttribute("parentId", rootValue);
            nodes[count].setAttribute("name", vals.get(id));
            nodes[count].setAttribute("isOpen", true);
            generateTreeNode(nodes[count], keyName, id);
            count++;
        }
        tree.setData(nodes);
        return tree;
    }

    public static void generateTreeNode(TreeNode node, String keyName, String parent) {
        LinkedHashMap<String, String> vals = getValueMap(keyName, parent);
        if (ClientUtil.isNullOrEmpty(vals)) {
            return;
        }
        TreeNode[] children = new TreeNode[vals.size()];
        int count = 0;
        for (String id : vals.keySet()) {
            children[count] = new TreeNode();
            children[count].setAttribute("id", id);
            children[count].setAttribute("parentId", parent);
            children[count].setAttribute("name", vals.get(id));
            children[count].setAttribute("isOpen", true);
            generateTreeNode(children[count], keyName, id);
            count++;
        }
        node.setChildren(children);
    }

    public static TreeGrid getTreeGrid(String keyName) {
        final TreeGrid treeGrid = new TreeGrid();
        treeGrid.setWidth(200);
        treeGrid.setHeight(240);
        treeGrid.setNodeIcon("icons/16/person.png");
        treeGrid.setFolderIcon("icons/16/person.png");
        treeGrid.setShowOpenIcons(false);
        treeGrid.setShowDropIcons(false);
        treeGrid.setClosedIconSuffix("");
        treeGrid.setData(getTreeGridData(keyName));
        treeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        treeGrid.setShowSelectedStyle(false);
        treeGrid.setShowPartialSelection(true);
        treeGrid.setCascadeSelection(false);// 选择父级时选择子级，选择子级时选择父级
        return treeGrid;
    }

    public static Tree getTree(String keyName) {
        return getTree(keyName, "0");
    }

    public static Tree getTree(String keyName, String rootValue) {
        Tree tree = new Tree();
        TreeNode rootNode = new TreeNode(rootValue);
        rootNode.setID(rootValue);
        rootNode.setAttribute("treeId", rootValue);
        generateTree(rootNode, keyName, rootValue);
        tree.setRoot(rootNode);
        return tree;
    }

    public static void generateTree(TreeNode node, String keyName, String parent) {
        LinkedHashMap<String, String> vals = getValueMap(keyName, parent);
        TreeNode[] nodes = new TreeNode[vals.size()];
        int idx = 0;
        for (String id : vals.keySet()) {
            nodes[idx] = new TreeNode(id);
            nodes[idx].setID(id);
            nodes[idx].setTitle(vals.get(id));
            nodes[idx].setAttribute("treeId", id);
            generateTree(nodes[idx], keyName, id);
            idx++;
        }
        node.setChildren(nodes);
    }

    private static final Logger __LOGGER = Logger.getLogger("");
    private final static Map<String, KeyValueTime> KEYVALUES = new LinkedHashMap<>();
    private final static Storage STORAGE = Storage.getLocalStorageIfSupported();// 客户本地资源

    /**
     * @return the __DOMAINVALUE
     */
    public static String getDOMAINVALUE() {
        return __DOMAINVALUE;
    }

    /**
     * @param aDOMAINVALUE the __DOMAINVALUE to set
     */
    public static void setDOMAINVALUE(String aDOMAINVALUE) {
        __DOMAINVALUE = aDOMAINVALUE;
    }

    public static void loadMainProjects(final DataSourceField listGridField) {
        Map<String, Object> map = new HashMap<>();
        map.put("optType", "getAllMainProjects");
        DBDataSource.callOperation("EP_MarketingProcessor", map, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    Map responseUserData = BaseHelpUtils.getResponseUserData(dsResponse);
                    listGridField.setValueMap(responseUserData);
                }
            }
        });
    }

    public static void loadMainProjects(final FormItem selectItem) {
        Map<String, Object> map = new HashMap<>();
        map.put("optType", "getAllMainProjects");
        DBDataSource.callOperation("EP_MarketingProcessor", map, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    Map responseUserData = BaseHelpUtils.getResponseUserData(dsResponse);
                    selectItem.setValueMap(responseUserData);
                }
            }
        });
    }

    public static void loadMainProjects(final ListGridField selectItem) {
        Map<String, Object> map = new HashMap<>();
        map.put("optType", "getAllMainProjects");
        DBDataSource.callOperation("EP_MarketingProcessor", map, new DSCallback() {
            @Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                    Map responseUserData = BaseHelpUtils.getResponseUserData(dsResponse);
                    selectItem.setValueMap(responseUserData);
                }
            }
        });
    }
    
    /**
     * 加载自定义下拉数据
     * @param selectItem
     * @param selectId
     * @param map
     */
    public static void loadCustomSelect(final FormItem selectItem,String selectId,Map<String, Object> map) {
    	if(BaseHelpUtils.isNullOrEmpty(map)) {
    		map = new HashMap<>();
    	}
        map.put("optType", selectId);
        DBDataSource.callOperation("EP_OnCommonCustomSelectProcess", map, new DSCallback() {
            @SuppressWarnings("unchecked")
			@Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                	Map<String,Object> responseUserData = BaseHelpUtils.getResponseUserData(dsResponse);
                	selectItem.setValueMap(responseUserData);
                }
            }
        });
    }
    
    /**
     * 加载自定义下拉数据
     * @param listGridField
     * @param selectId
     * @param map
     */
    public static void loadCustomSelect(final DataSourceField listGridField,String selectId,Map<String, Object> map) {
    	if(BaseHelpUtils.isNullOrEmpty(map)) {
    		map = new HashMap<>();
    	}
        map.put("optType", selectId);
        DBDataSource.callOperation("EP_OnCommonCustomSelectProcess", map, new DSCallback() {
            @SuppressWarnings("unchecked")
			@Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                	Map<String,Object> responseUserData = BaseHelpUtils.getResponseUserData(dsResponse);
                	listGridField.setValueMap(responseUserData);
                }
            }
        });
    }
    
    /**
     * 加载自定义下拉数据
     * @param listGridField
     * @param selectId
     * @param map
     */
    public static void loadCustomSelect(final ListGridField listGridField,final String selectId,Map<String, Object> map) {
    	if(BaseHelpUtils.isNullOrEmpty(map)) {
    		map = new HashMap<>();
    	}
        map.put("optType", selectId);
        DBDataSource.callOperation("EP_OnCommonCustomSelectProcess", map, new DSCallback() {
            @SuppressWarnings("unchecked")
			@Override
            public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                if (dsResponse.getStatus() >= 0) {
                	Map<String,Object> responseUserData = BaseHelpUtils.getResponseUserData(dsResponse);
                	listGridField.setValueMap(responseUserData);
                }
            }
        });
    }
    

    public static void main(String[] args) {
        LinkedHashMap ss = getValueMap("project_types");
        System.out.println("ss==" + ss.toString());
    }
}
