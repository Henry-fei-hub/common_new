package com.delicacy.client.data;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public abstract class GenericBase {

    private static final Logger __logger = Logger.getLogger("");

    public java.lang.String toJSON() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(toJSONString());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return toJSON();
    }

    public void setDataFromJSONString(String json) {
        JSONValue result = JSONParser.parseLenient(json);
        JSONObject jso = result.isObject();
        setDataFromJSON(jso);
    }

    public abstract java.lang.String toJSONString();

    public abstract void setDataFromJSON(JSONObject values);

    public static void appendName(StringBuilder sb, String name) {
        sb.append("\"");
        sb.append(name);
        sb.append("\"");
    }

    public static String __wrapNumber(int count, String name, Number val) {
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
        appendName(sb, name);
        sb.append(":");
        sb.append(val);
        return sb.toString();
    }

    public static String __wrapString(int count, String name, String val) {
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
        appendName(sb, name);
        sb.append(":");
        sb.append("\"");
        if (__containsSpecificChars(val)) {
            sb.append(JsonUtils.escapeValue(val));
        } else {
            sb.append(val);
        }
        sb.append("\"");
        return sb.toString();
    }

    public static boolean __containsSpecificChars(String val) {
        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            if (c == '\n' || c == '"') {
                return true;
            }
        }
        return false;
    }

    public static String __wrapDate(int count, String name, Date val) {
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
        appendName(sb, name);
        sb.append(":");
        sb.append(val.getTime());
        return sb.toString();
    }

    public static String __wrapObject(int count, String name, GenericBase gb) {
        if (gb == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
        appendName(sb, name);
        sb.append(":");
        sb.append(gb.toJSON());
        return sb.toString();
    }

    public static String __wrapBoolean(int count, String name, Boolean val) {
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
        appendName(sb, name);
        sb.append(":");
        sb.append(val);
        return sb.toString();
    }

	public static String __wrapSet(int count, String name, Set<String> st){
		if (st == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
		appendName(sb, name);
        sb.append(":");
        sb.append("[");
		int idx = 0;
		for (String b : st) {
			if (idx > 0) {
				sb.append(",");
			}
			sb.append(b);
			idx++;
		}
        sb.append("]");
        return sb.toString();
	}
	
    public static String __wrapList(int count, String name, List<? extends GenericBase> ll) {
        if (ll == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(",");
        }
        appendName(sb, name);
        sb.append(":");
        sb.append("[");
        int idx = 0;
        for (GenericBase b : ll) {
            if (idx > 0) {
                sb.append(",");
            }
            sb.append(b.toJSON());
            idx++;
        }
        sb.append("]");
        return sb.toString();
    }

    public static List __getList(JSONArray val, BaseFactory factory) {
        List res = new ArrayList();
        if (val == null) {
            return res;
        }
        for (int i = 0; i < val.size(); i++) {
            GenericBase gb = factory.make();
            gb.setDataFromJSON((JSONObject) val.get(i));
            res.add(gb);
        }
        return res;
    }
	
	public static Set<String> __getSet(JSONArray val) {
		Set<String> res = new HashSet<>();
		if (val == null) {
            return res;
        }
		for (int i = 0; i < val.size(); i++) {
			String s = val.get(i).isString().stringValue();
			res.add(s);
		}
		return res;
	}

    public static String __getString(JSONValue val) {
        JSONNumber numberValue;
        JSONString stringValue;
        JSONBoolean booleanValue;
        if ((stringValue = val.isString()) != null) {
            return stringValue.stringValue();
        } else if ((numberValue = val.isNumber()) != null) {
            return String.valueOf(numberValue.doubleValue());
        } else if ((booleanValue = val.isBoolean()) != null) {
            return String.valueOf(booleanValue.booleanValue());
        }
        return null;
    }

    public static Date __getDate(JSONValue val) {
        JSONString stringValue;
        String sval = null;
        if ((stringValue = val.isString()) != null) {
            sval = stringValue.stringValue();
            if (sval.startsWith("__DATE__")) {
                try {
                    long lval = Long.parseLong(sval.substring(8));
                    return new Date(lval);
                } catch (NumberFormatException ex) {
                    __logger.severe(ex.getMessage());
                }
            }
        }
        return null;
    }

    public static Integer __getInt(JSONValue val) {
        JSONNumber numberValue;
        JSONString stringValue;
        if ((stringValue = val.isString()) != null) {
            return Integer.parseInt(stringValue.stringValue());
        } else if ((numberValue = val.isNumber()) != null) {
            return (int) numberValue.doubleValue();
        }
        return null;
    }

    public static Long __getLong(JSONValue val) {
        JSONNumber numberValue;
        JSONString stringValue;
        if ((stringValue = val.isString()) != null) {
            return Long.parseLong(stringValue.stringValue());
        } else if ((numberValue = val.isNumber()) != null) {
            return (long) numberValue.doubleValue();
        }
        return null;
    }

    public static Double __getDouble(JSONValue val) {
        JSONNumber numberValue;
        JSONString stringValue;
        if ((stringValue = val.isString()) != null) {
            return Double.parseDouble(stringValue.stringValue());
        } else if ((numberValue = val.isNumber()) != null) {
            return numberValue.doubleValue();
        }
        return null;
    }

    public static Boolean __getBoolean(JSONValue val) {
        JSONString stringValue;
        JSONBoolean booleanValue;
        if ((stringValue = val.isString()) != null) {
            return Boolean.parseBoolean(stringValue.stringValue());
        } else if ((booleanValue = val.isBoolean()) != null) {
            return booleanValue.booleanValue();
        }
        return null;
    }

}
