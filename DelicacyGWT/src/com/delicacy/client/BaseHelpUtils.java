package com.delicacy.client;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.DSResponse;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author guanxgun
 */
public class BaseHelpUtils {
	
	/**
	 * 获取请求响应定义的userdata数据集
	 * @param dsResponse
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getResponseUserData(DSResponse dsResponse) {
		Map<String,Object> map = null;
		if(!BaseHelpUtils.isNullOrEmpty(dsResponse.getAttribute("userData")) && dsResponse.getAttribute("userData").length() > 0){
			map = dsResponse.getAttributeAsMap("userData");
		}
        return map;
    }



		public static int getIntValue(Map data, String name) {
		return getIntValue(data.get(name));
	}

	public static String getStringValue(Map data, String name) {
		return getString(data.get(name));
	}

	public static double getDoubleValue(Object val) {
		if (isNullOrEmpty(val)) {
			return 0.0;
		}
		if (val instanceof Number) {
			return ((Number) val).doubleValue();
		}
		if (val instanceof Date) {
			return new Double(((Date) val).getTime());
		}
		try {
			return Double.parseDouble((String) val);
		} catch (Exception ex) {
			return 0.0;
		}
	}

	public static BigDecimal getBigDecimalValue(Object val) {
		if (isNullOrEmpty(val)) {
			return BigDecimal.ZERO;
		}
		if (val instanceof Number) {
			return BigDecimal.valueOf(((Number) val).doubleValue());
		}
		if (val instanceof Date) {
			return BigDecimal.valueOf(((Date) val).getTime());
		}
		try {
			return new BigDecimal((String) val);
		} catch (Exception ex) {
			return BigDecimal.ZERO;
		}
	}

	//获取小数点位数截掉
    public static BigDecimal format(Object val,int num){
    	if (isNullOrEmpty(val)) {
            return BigDecimal.ZERO;
        }
    	if (val instanceof Number) {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_DOWN);
        }
    	try {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_DOWN);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }
    
    //获取小数点位数四舍五入
    public static BigDecimal getDecimal(Object val,int num){
    	if (isNullOrEmpty(val)) {
            return BigDecimal.ZERO;
        }
    	if (val instanceof Number) {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_HALF_UP);
        }
    	try {
            return new BigDecimal(val.toString()).setScale(num, BigDecimal.ROUND_HALF_UP);
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }

	public static int getIntValue(Object val) {
		if (isNullOrEmpty(val)) {
			return 0;
		}
		if (val instanceof Number) {
			return ((Number) val).intValue();
		}
		if (val instanceof Date) {
			return (int) ((Date) val).getTime();
		}
		try {
			return Integer.parseInt((String) val);
		} catch (Exception ex) {
			return 0;
		}
	}

	public static int getIntFromDoubleStr(Object val) {
		if (isNullOrEmpty(val)) {
			return 0;
		}
		String value = (String) val;
		if (value.contains(".")) {
			String strNum = value.substring(0, value.indexOf("."));
			return Integer.valueOf(strNum);
		} else {
			return Integer.valueOf(value);
		}
	}

	public static long getLongValue(Object val) {
		if (isNullOrEmpty(val)) {
			return 0;
		}
		if (val instanceof Number) {
			return ((Number) val).longValue();
		}
		if (val instanceof Date) {
			return ((Date) val).getTime();
		}
		try {
			return Long.parseLong((String) val);
		} catch (Exception ex) {
			return 0L;
		}
	}

	public static boolean getBoolean(Object val) {
		if (val == null) {
			return false;
		}
		if (val instanceof Boolean) {
			return ((Boolean) val);
		}
		if (val instanceof Number) {
			return ((Number) val).intValue() != 0;
		}
		if (val instanceof String) {
			String s = (String) val;
			if (s.equalsIgnoreCase("on")) {
				return true;
			}
			if (s.equalsIgnoreCase("y")) {
				return true;
			}
			if (s.equalsIgnoreCase("yes")) {
				return true;
			}
			if (s.equalsIgnoreCase("true")) {
				return true;
			}
			return false;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Object val) {
		if (val == null) {
			return true;
		}
		if (val instanceof Collection) {
			return ((Collection) val).isEmpty();
		}
		String s = val.toString().trim();
		return s.length() == 0;
	}

	public static String getRemoveBlankString(Object val) {
		if (isNullOrEmpty(val)) {
			return "";
		}
		return val.toString().trim();
	}

	public static String getString(Object val) {
		if (isNullOrEmpty(val)) {
			return "";
		}
		return val.toString();
	}

	public static boolean isNullOrEmpty(String val) {
		if (val == null) {
			return true;
		}
		val = val.trim();
		return val.length() == 0;
	}

	public static boolean isNullOrZero(Object val) {
		if (val == null) {
			return true;
		}
		return getLongValue(val) == 0;
	}

	public static String formatSearchString(String k, char sp) {
		if (k == null) {
			return k;
		}
		StringBuilder sb = new StringBuilder();
		boolean startSpace = false;
		boolean begin = false;
		for (int i = 0; i < k.length(); i++) {
			char ch = k.charAt(i);
			if ((ch == ' ' || ch == '\t' || ch == '\n' || ch == '\f')) {
				startSpace = true;
				continue;
			}
			if (startSpace && begin) {
				sb.append(sp);
			}
			sb.append(ch);
			begin = true;
			startSpace = false;
		}
		return sb.toString();
	}

	// 去掉字符串前后多余的空格，以及字之间多余的空格
	public static String trim(String k) {
		return formatSearchString(k, ' ');
	}

	/**
	 * 表明四舍五入，保留两位小数
	 * 
	 * @param o
	 * @return
	 */
	public static double get2Double(Object o) {

		double temp = getDoubleValue(o);
		BigDecimal b = new BigDecimal(temp);
		return BaseHelpUtils.getDoubleValue(b.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 根据后台返回的数据集返回JsonArray对象
	 * @param dsResponse
	 * @return
	 */
	public static JSONArray getDataJsonArrayFromResponse(DSResponse dsResponse){
		String respContent = dsResponse.getAttribute("respContent");
		if(isNullOrEmpty(respContent)){
			return null;
		}
		String respContentJson = JsonUtils.escapeJsonForEval(respContent);
		JSONValue json = JSONParser.parseLenient(respContentJson);
		JSONObject jsonObj = json.isObject();
		JSONObject resultSetObj = jsonObj.get("ResultSet").isObject();
		if (isNullOrEmpty(resultSetObj)){
			return null;
		}
		JSONArray resultArrs = resultSetObj.get("Result").isArray();
		return resultArrs;
	}
	
	/**
	 * 根据后台返回的数据集返回JsonArray对象
	 * @param dsResponse
	 * @return
	 */
	public static Integer getTotalFromResponse(DSResponse dsResponse){
		String respContent = dsResponse.getAttribute("respContent");
		if(isNullOrEmpty(respContent)){
			return null;
		}
		String respContentJson = JsonUtils.escapeJsonForEval(respContent);
		JSONValue json = JSONParser.parseLenient(respContentJson);
		JSONObject jsonObj = json.isObject();
		JSONObject resultSetObj = jsonObj.get("ResultSet").isObject();
		if (isNullOrEmpty(resultSetObj)){
			return null;
		}
		Integer total = BaseHelpUtils.getIntValue(resultSetObj.get("totalResultsAvailable").toString());
		return total;
	}

	public static void main(String[] args) {
		System.out.println(get2Double(1.5));

		System.out.println("================");
		System.out.println(get2Double(1.244));
	}
}
