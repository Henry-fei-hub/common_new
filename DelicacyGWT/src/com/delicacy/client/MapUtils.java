package com.delicacy.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Hidden;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.drawing.DrawItem;
import com.smartgwt.client.widgets.grid.ListGrid;

public class MapUtils {

	private static final Logger __logger = Logger.getLogger("");

	public static Map getConditionFromMap(Map values, Map originalMap) {
		if (values.containsKey("value")) {
			originalMap.put(values.get("fieldName"), values.get("value"));
		}
		for (Object o : values.keySet()) {
			Object v = values.get(o);
			if (v instanceof Map) {
				getConditionFromMap((Map) v, originalMap);
			}
			if (v instanceof List) {
				for (Object o1 : (List) v) {
					getConditionFromMap((Map) o1, originalMap);
				}
			}
			if (v instanceof Map[]) {
				for (Object o1 : (Map[]) v) {
					getConditionFromMap((Map) o1, originalMap);
				}
			}
		}
		return originalMap;
	}

	public static String toJSON(Map data) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		boolean comma = false;
		boolean isString;
		for (Object key : data.keySet()) {
			if (isSmartInternalKeys(key)) {
				continue;
			}
			isString = false;
			Object val = data.get(key);
			if (isNullOrEmpty(val)) {
				continue;
			}
			if (comma) {
				sb.append(",");
			} else {
				comma = true;
			}
			sb.append("\"");
			sb.append(key.toString());
			sb.append("\":");
			if (val instanceof Map) {
				sb.append(toJSON((Map) val));
			} else if (val instanceof Object[]) {
				sb.append("[");
				Object[] valarray = (Object[]) val;
				for (int i = 0; i < valarray.length; i++) {
					if (i > 0) {
						sb.append(", ");
					}
					if (valarray[i] instanceof Map) {
						sb.append(toJSON((Map) valarray[i]));
					} else if (valarray[i] instanceof Record) {
						sb.append(toJSON(((Record) valarray[i]).toMap()));
					} else {
						appendPrimitives(sb, valarray[i]);
					}
				}
				sb.append("]");
			} else if (val instanceof List) {
				List ll = (List) val;
				sb.append("[");
				int count = 0;
				for (Object v : ll) {
					if (count > 0) {
						sb.append(", ");
					}
					if (v instanceof Map) {
						sb.append(toJSON((Map) v));
					} else if (v instanceof Record) {
						sb.append(toJSON(((Record) v).toMap()));
					} else {
						appendPrimitives(sb, v);
					}
					count++;
				}
				sb.append("]");
			} else if (val instanceof Record) {
				sb.append(toJSON(((Record) val).toMap()));
			} else {
				appendPrimitives(sb, val);
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public static void appendPrimitives(StringBuilder sb, Object val) {
		boolean isString = false;
		if (val instanceof String) {
			isString = true;
		}
		if (isString) {
			sb.append(JsonUtils.escapeValue((String) val));
		} else if (val instanceof Date) {
			sb.append(((Date) val).getTime());
		} else {
			sb.append(val);
		}
	}

	public static boolean isSmartInternalKeys(Object key) {
		String skey = key.toString();
		if (skey.equals("__ref")) {
			return true;
		}
		if (skey.equals("__module")) {
			return true;
		}
		return skey.startsWith("_selection_");
	}

	public static boolean isNullOrEmpty(Object val) {
		if (val == null) {
			return true;
		}
		return val.toString().isEmpty();
	}

	public static Hidden generateFormDataForUpload(Map r) {
		return generateFormDataForUpload(toJSON(r));
	}

	public static Hidden generateFormDataForUpload(Record r) {
		return generateFormDataForUpload(toJSON(r.toMap()));
	}

	public static Hidden generateFormDataForUpload(String r) {
		Hidden delicacyContent = new Hidden("__delicacyContent");
		delicacyContent.setValue(URL.encodeQueryString(r));
		return delicacyContent;
	}

	public static void copyDataFromDrawItemToRecord(DataSource ds, DrawItem src, Record dst) {
		DataSourceField[] fields = ds.getFields();
		for (DataSourceField field : fields) {
			switch (field.getType()) {
			case DATETIME:
			case DATE:
				Date d = src.getAttributeAsDate(field.getName());
				if (d != null) {
					dst.setAttribute(field.getName(), d);
				}
				break;
			case BOOLEAN:
				Boolean b = src.getAttributeAsBoolean(field.getName());
				if (b != null) {
					dst.setAttribute(field.getName(), b);
				}
				break;
			case INTEGER:
				Integer i = src.getAttributeAsInt(field.getName());
				if (i != null) {
					dst.setAttribute(field.getName(), i);
				}
				break;
			case FLOAT:
				Double dd = src.getAttributeAsDouble(field.getName());
				if (dd != null) {
					dst.setAttribute(field.getName(), dd);
				}
				break;
			default:
				String s = src.getAttributeAsString(field.getName());
				if (s != null) {
					dst.setAttribute(field.getName(), s);
				}
				break;
			}
		}
	}

	public static void copyDataFromRecordToDrawItem(DataSource ds, Record src, DrawItem dst) {
		DataSourceField[] fields = ds.getFields();
		for (DataSourceField field : fields) {
			switch (field.getType()) {
			case DATETIME:
			case DATE:
				Date d = src.getAttributeAsDate(field.getName());
				if (d != null) {
					dst.setAttribute(field.getName(), d, true);
				}
				break;
			case BOOLEAN:
				Boolean b = src.getAttributeAsBoolean(field.getName());
				if (b != null) {
					dst.setAttribute(field.getName(), b, true);
				}
				break;
			case INTEGER:
				Integer i = src.getAttributeAsInt(field.getName());
				if (i != null) {
					dst.setAttribute(field.getName(), i, true);
				}
				break;
			case FLOAT:
				Double dd = src.getAttributeAsDouble(field.getName());
				if (dd != null) {
					dst.setAttribute(field.getName(), dd, true);
				}
				break;
			default:
				String s = src.getAttributeAsString(field.getName());
				if (s != null) {
					dst.setAttribute(field.getName(), s, true);
				}
				break;
			}
		}
	}

	public static Map convertRecordToMap(DataSource ds, Record data) {
		Map res = new HashMap();
		DataSourceField[] fields = ds.getFields();
		for (DataSourceField field : fields) {
			Object val = data.getAttributeAsObject(field.getName());
			if (isNullOrEmpty(val)) {
				continue;
			}
			switch (field.getType()) {
			case DATETIME:
			case DATE:
				res.put(field.getName(), data.getAttributeAsDate(field.getName()));
				break;
			case BOOLEAN:
				res.put(field.getName(), data.getAttributeAsBoolean(field.getName()));
				break;
			case INTEGER:
				res.put(field.getName(), data.getAttributeAsInt(field.getName()));
				break;
			case FLOAT:
				res.put(field.getName(), data.getAttributeAsDouble(field.getName()));
				break;
			default:
				res.put(field.getName(), val);
				break;
			}
		}
		return res;
	}

	public static Map convertListGridRecordToMap(ListGrid lg, Record data) {
		return data.toMap();
//		Map res = new HashMap();
//		ListGridField[] fields = lg.getFields();
//		for (ListGridField field : fields) {
//			Object val = data.getAttributeAsObject(field.getName());
//			if (isNullOrEmpty(val)) {
//				continue;
//			}
//			if (field.getType() == null) {
//				res.put(field.getName(), val);
//				continue;
//			}
//			switch (field.getType()) {
//			case DATETIME:
//			case DATE:
//				res.put(field.getName(), data.getAttributeAsDate(field.getName()));
//				break;
//			case BOOLEAN:
//				res.put(field.getName(), data.getAttributeAsBoolean(field.getName()));
//				break;
//			case INTEGER:
//				res.put(field.getName(), data.getAttributeAsInt(field.getName()));
//				break;
//			case FLOAT:
//				res.put(field.getName(), data.getAttributeAsDouble(field.getName()));
//				break;
//			default:
//				res.put(field.getName(), val);
//				break;
//			}
//		}
//		return res;
	}

	public static void convertRecordToMap(DataSource ds, Record[] data, Map res, String keyName) {
		List<Map> ll = new ArrayList<>();
		for (Record data1 : data) {
			ll.add(convertRecordToMap(ds, data1));
		}
		res.put(keyName, ll);
		// __logger.info(res.toString());
	}

	public static void convertRecordToMap(ListGrid lg, Record[] data, Map res, String keyName) {
		List<Map> ll = new ArrayList<>();
		for (Record data1 : data) {
			ll.add(convertListGridRecordToMap(lg, data1));
		}
		res.put(keyName, ll);
	}

	/**
	 * 通过键值 对Map进行排序 默认升序
	 *
	 * @param map
	 * @param orderBy
	 * @return
	 */
	public static Map sortMapByKey(Map map, String orderBy) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap;
		if (null != orderBy && orderBy.toLowerCase().equals("desc")) {
			sortMap = new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String obj1, String obj2) {
					// 降序排序
					return obj2.compareTo(obj1);
				}
			});
			sortMap.putAll(map);

		} else {
			sortMap = new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String obj1, String obj2) {
					// 升序排序
					return obj1.compareTo(obj2);
				}
			});
			sortMap.putAll(map);
		}
		return sortMap;
	}

	public static boolean isDataContains(DataClass d, String name) {
		String[] names = d.getAttributes();
		if (names == null) {
			return false;
		}
		for (String n : names) {
			if (name.equals(n)) {
				return true;
			}
		}
		return false;
	}

	public static int getValueAsInt(DataClass d, String name) {
		if (!isDataContains(d, name)) {
			return 0;
		}
		return d.getAttributeAsInt(name);
	}

	public static double getValueAsDouble(DataClass d, String name) {
		if (!isDataContains(d, name)) {
			return 0.0;
		}
		return d.getAttributeAsDouble(name);
	}

	public static boolean getValueAsBoolean(DataClass d, String name) {
		if (!isDataContains(d, name)) {
			return false;
		}
		return d.getAttributeAsBoolean(name);
	}
}
