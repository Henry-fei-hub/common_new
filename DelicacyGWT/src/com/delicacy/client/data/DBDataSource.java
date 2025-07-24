package com.delicacy.client.data;

import com.delicacy.client.MapUtils;
import com.delicacy.client.websocket.Base64Utils;
import com.delicacy.client.websocket.Websocket;
import com.delicacy.client.websocket.WebsocketListener;
import com.delicacy.client.zip.GZIPException;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.*;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.*;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDataSource extends DataSource {

    private static final Logger __logger = Logger.getLogger("");
    public final static String ACTION_MODE_QUERY = "NQ_";
    public final static String ACTION_MODE_TABLE = "ST_";
    public final static String ACTION_MODE_CUSTOM = "EP_";
    public final static String PUBLIC_KEY = "19770922";
    public final static String PRIVATE_KEY = "19691001";
    public static int LOCALPORT = 0;
    public static String LOCALADDRESS = "127.0.0.1";
    public static String CALLBACKNAME = "__delicacy_callback";

    public DBDataSource() {
        this.setDataProtocol(DSProtocol.CLIENTCUSTOM);
        this.setDataFormat(DSDataFormat.JSON);
        this.setClientOnly(false);
        this.setDataURL(getBaseDataURL().toString());
    }

    @Override
    protected Object transformRequest(DSRequest rt) {
        if (getActionName() == null || getActionName().length() == 0) {
            return null;
        }
        setRequest(rt);
        StringBuilder sb = new StringBuilder();
        sb.append("action=");
        sb.append(getActionMode());
        sb.append(getActionName());
        Criteria condition = getRequest().getCriteria();
        String dataToSend = "";
        Map values = condition.getValues();
        if (values.containsValue("AdvancedCriteria")) {
            Map vals = MapUtils.getConditionFromMap(values, new HashMap());
            dataToSend = MapUtils.toJSON(vals);
        } else {
            dataToSend = MapUtils.toJSON(values);
        }
        switch (getRequest().getOperationType()) {
            case FETCH:
                if (ACTION_MODE_TABLE.equals(getActionMode())) {

                    sb.append("&subaction=find");
                    String currentPage = condition.getAttribute("currentPage");
                    String pageLines = condition.getAttribute("pageLines");
                    String additional = condition.getAttribute("additionalCondition");
                    if (currentPage != null) {
                        sb.append("&currentPage=").append(currentPage);
                    }
                    if (pageLines != null) {
                        sb.append("&pageSize=").append(pageLines);
                    }
                    if (additional != null) {
                        sb.append("&addtionalCondition=").append(URL.encodeQueryString(additional));
                    }
                }
                break;
            case ADD:
                if (ACTION_MODE_TABLE.equals(getActionMode())) {
                    sb.append("&subaction=save");
                }
                break;
            case UPDATE:
                if (ACTION_MODE_TABLE.equals(getActionMode())) {
                    sb.append("&subaction=update");
                }
                break;
            case REMOVE:
                if (ACTION_MODE_TABLE.equals(getActionMode())) {
                    sb.append("&subaction=delete");
                }
                break;
            case CUSTOM:
                break;
        }
        sb.append("&content=").append(URL.encodeQueryString(dataToSend));
        try {
            final RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, getServerURL());
            rb.sendRequest(sb.toString(), new RequestCallback() {
                @Override
                public void onResponseReceived(Request req, Response resp) {
                    try {
                        DSResponse res = processHttpResponse(resp.getText());
                        processResponse(getRequest().getRequestId(), res);
                    } catch (Exception ex) {
                        __logger.severe(ex.getMessage());
                        __logger.severe(resp.getText());
                    }
                }

                @Override
                public void onError(Request req, Throwable exception) {
                    __logger.severe(exception.getMessage());
                    DSResponse res = new DSResponse();
                    res.setStatus(DSResponse.STATUS_FAILURE);
                    Map errMap = new HashMap();
                    errMap.put("error", exception.getMessage());
                    res.setErrors(errMap);
                    res.setAttribute("clientContext", getRequest().getAttribute("clientContext"));
                    processResponse(getRequest().getRequestId(), res);
                }
            });
        } catch (RequestException ex) {
            __logger.severe(ex.getMessage());
            DSResponse res = new DSResponse();
            res.setStatus(-1);
            Map errMap = new HashMap();
            errMap.put("error", ex.getMessage());
            res.setErrors(errMap);
            res.setAttribute("clientContext", getRequest().getAttribute("clientContext"));
            processResponse(getRequest().getRequestId(), res);
        }
        return dataToSend;
    }

    public static void downloadFile(String operationName, SearchForm sf) {
        Criteria record = sf.getValuesAsCriteria();
        downloadFile(operationName, record);
    }

    public static void downloadFile(String operationName, Criteria params) {
        Map pp = params.getValues();
        downloadFile(operationName, pp);
    }

    public static void downloadFile(String operationName, Map params) {
        StringBuilder sb = new StringBuilder();
        sb.append(getServerURL(operationName));
        sb.append("&content=");
        sb.append(URL.encodeQueryString(MapUtils.toJSON(params)));
        Window.open(sb.toString(), null, null);
    }

    public static void callLocalOperation(String operationName, Map params, final DSCallback callback) {
        String dataToSend = MapUtils.toJSON(params);
        callOperation(operationName, "", dataToSend, null, true, callback);
    }

    public static void callOperation(String operationName, Map params, boolean isLocal, final DSCallback callback) {
        String dataToSend = MapUtils.toJSON(params);
        callOperation(operationName, "", dataToSend, null, isLocal, callback);
    }

    public static void callOperation(String operationName, Map params, final DSCallback callback) {
        callOperation(operationName, "", params, null, callback);
    }

    public static void callOperation(String operationName, String action, Map params, boolean isLocal,
            final DSCallback callback) {
        String dataToSend = MapUtils.toJSON(params);
        callOperation(operationName, action, dataToSend, null, isLocal, callback);
    }

    public static void callOperation(String operationName, String action, Map params, final DSCallback callback) {
        String dataToSend = MapUtils.toJSON(params);
        callOperation(operationName, action, dataToSend, null, false, callback);
    }

    public static void callOperation(String operationName, String params, final DSCallback callback) {
        callOperation(operationName, "", params, null, false, callback);
    }

    public static void callOperation(String operationName, String action, String params, final DSCallback callback) {
        callOperation(operationName, action, params, null, false, callback);
    }

    public static void callOperation(String operationName, String action, Map params, final DataSource ds,
            final DSCallback callback) {
        String dataToSend = MapUtils.toJSON(params);
        callOperation(operationName, action, dataToSend, ds, false, callback);
    }

    public static String generateParameterString(String operationName, String action, String dataToSend) {
        StringBuilder sb = new StringBuilder();
        sb.append("action=").append(operationName);
        if (action != null && !action.isEmpty()) {
            sb.append("&subaction=");
            sb.append(action);
        }
        sb.append("&content=").append(URL.encodeQueryString(dataToSend));
        return sb.toString();
    }

    public static void callOperation(String operationName, String dataToSend,
            final Callback<String, Throwable> callback) {
        callOperation(operationName, (String) null, dataToSend, callback);
    }

    public static void callOperation(String operationName, String action, String dataToSend,
            final Callback<String, Throwable> callback) {
        String content = generateParameterString(operationName, action, dataToSend);
        try {
            final RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, getServerURL());
            rb.sendRequest(content, new RequestCallback() {
                @Override
                public void onResponseReceived(Request req, Response resp) {
                    if (callback != null) {
                        callback.onSuccess(resp.getText());
                    }
                }

                @Override
                public void onError(Request req, Throwable exception) {
                    if (callback != null) {
                        callback.onFailure(exception);
                    }
                    __logger.severe(exception.getMessage());
                }
            });
        } catch (RequestException ex) {
            __logger.severe(ex.getMessage());
        }
    }

    public static void callOperation(String operationName, String action, String dataToSend, final DataSource ds,
            boolean isLocal, final DSCallback callback) {
        String content = generateParameterString(operationName, action, dataToSend);
        try {
            if (!isLocal) {
                final RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, getServerURL());
                rb.sendRequest(content, new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request req, Response resp) {
                        DSResponse res = new DSResponse();
                        try {
                            res = processHttpResponse(resp.getText());
                        } catch (Exception ex) {
                            __logger.severe(ex.getMessage());
                            __logger.severe(resp.getText());
                        }
                        callback.execute(res, resp.getText(), null);
                    }

                    @Override
                    public void onError(Request req, Throwable exception) {
                        __logger.severe(exception.getMessage());
                    }
                });
            } else {
                String url = getLocalURL(content);
                JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
                jsonp.setCallbackParam(CALLBACKNAME);
                jsonp.requestString(url, new AsyncCallback<String>() {
                    public void onFailure(Throwable throwable) {
                        __logger.severe("Error: " + throwable);
                    }

                    public void onSuccess(String feed) {
                        __logger.info(feed);
                        DSResponse res = new DSResponse();
                        try {
                            res = processHttpResponse(feed);
                        } catch (Exception ex) {
                            __logger.severe(ex.getMessage());
                            __logger.severe(feed);
                        }
                        callback.execute(res, feed, null);
                    }
                });
            }
        } catch (RequestException ex) {
            __logger.severe(ex.getMessage());
        }
    }

    public static void callOperation(final DSCallback callback) {
        StringBuilder url = new StringBuilder();
        url.append("http://127.0.0.1:24010/");
        url.append("ZKIDROnline");
        url.append("/");
        url.append("ScanReadIdCardInfo?OP-DEV=1&CMD-URL=4");
        try {
            final RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url.toString());
            rb.sendRequest("", new RequestCallback() {
                @Override
                public void onResponseReceived(Request req, Response resp) {
                    DSResponse res = new DSResponse();
                    try {
                        res = processHttpResponse(resp.getText());
                    } catch (Exception ex) {
                        __logger.severe(ex.getMessage());
                        __logger.severe(resp.getText());
                    }
                    callback.execute(res, resp.getText(), null);
                }

                @Override
                public void onError(Request req, Throwable exception) {
                    __logger.severe(exception.getMessage());
                }
            });
        } catch (RequestException ex) {
            __logger.severe(ex.getMessage());
        }
    }

    public static void callJSONP(String url, String operationName, String action, String dataToSend,
            final DataSource ds, final DSCallback callback) {
        try {
            String content = generateParameterString(operationName, action, dataToSend);
            url = getJSONPURL(url, content);
            JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
            jsonp.requestString(url, new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable throwable) {
                    __logger.log(Level.SEVERE, "Error: {0}", throwable);
                }

                @Override
                public void onSuccess(String feed) {
                    __logger.info(feed);
                    DSResponse res = new DSResponse();
                    try {
                        res = processHttpResponse(URL.decodeQueryString(feed));
                    } catch (Exception ex) {
                        __logger.severe(ex.getMessage());
                        __logger.severe(feed);
                    }
                    callback.execute(res, feed, null);
                }
            });
        } catch (Exception ex) {
            __logger.severe(ex.getMessage());
        }
    }

    public static String getJSONPURL(String url, String params) {
        StringBuilder res = new StringBuilder();
        res.append(url);
        res.append("/");
        res.append("DelicacyServlet");
        res.append("?");
        res.append(params);
        return res.toString();
    }

    public String getOperationName() {
        return getActionMode().concat(getActionName());
    }

    public static DSResponse processHttpResponse(String json) throws Exception {

        DSResponse resp = new DSResponse();
        resp.setStatus(0);
        resp.setAttribute("respContent",json);
        String escapeed = JsonUtils.escapeJsonForEval(json);
        JSONValue result = JSONParser.parseLenient(escapeed);
        JSONObject jso = result.isObject();
        for (String kk : jso.keySet()) {
            if (!kk.equals("ResultSet")) {
                continue;
            }
            JSONObject valueSet = (JSONObject) jso.get(kk);
            for (String key : valueSet.keySet()) {
                switch (key) {
                    case "Result":
                        JSONArray objs = (JSONArray) valueSet.get(key);
                        ListGridRecord[] data;
                        data = getDataFromJSON(objs);
                        resp.setData(data);
                        break;
                    case "status":
                        resp.setStatus((int) ((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "errors":
                        Map errMap = new HashMap();
                        errMap.put("errorCode", "1001");
                        errMap.put("errorMsg", ((JSONString) valueSet.get(key)).stringValue());
                        resp.setErrors(errMap);
                        break;
                    case "totalPages":
                        resp.setAttribute("totalPages", (int) ((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "totalResultsAvailable":
                        resp.setAttribute("totalResultsAvailable", (int) ((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "currentPage":
                        resp.setAttribute("currentPage", (int) ((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "recordNumber":
                        resp.setTotalRows((int) ((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    case "pageLines":
                        resp.setAttribute("pageLines", (int) ((JSONNumber) valueSet.get(key)).doubleValue());
                        break;
                    default:
                        JSONValue val = valueSet.get(key);
                        if (val.isArray() != null) {
                            ListGridRecord[] data1 = getDataFromJSON(val.isArray());
                            Record rl = new Record();
                            rl.setAttribute(key, data1);
                            resp.setAttribute(key, rl);
                        } else {
                            ListGridRecord data1 = getDataFromJSONObject(val.isObject());
                            resp.setAttribute(key, data1);
                        }
                        break;
                }
            }
        }
        return resp;
    }

    public static Record[] getUserData(DSResponse res, String name) {
        Record data1 = res.getAttributeAsRecord(name);
        return data1.getAttributeAsRecordArray(name);
    }

    public static void copyRecord(Record source, Record dest) {
        String[] names = source.getAttributes();
        for (int i = 0; i < names.length; i++) {
            dest.setAttribute(names[i], source.getAttributeAsObject(names[i]));
        }
    }

    public static ListGridRecord convertRecordToListGridRecord(Record r) {
        if (r == null) {
            return null;
        }
        ListGridRecord res = new ListGridRecord();
        copyRecord(r, res);
        return res;
    }

    public static Record generateEmptyRecord(DataSource ds) {
        Record r = new Record();
        for (String n : ds.getFieldNames()) {
            r.setAttribute(n, "");
        }
        return r;
    }

    public static ListGridRecord[] convertRecordToListGridRecords(Record[] rs) {
        if (rs == null || rs.length == 0) {
            return new ListGridRecord[0];
        }
        ListGridRecord[] res = new ListGridRecord[rs.length];
        int idx = 0;
        for (Record r : rs) {
            res[idx] = convertRecordToListGridRecord(r);
            idx++;
        }
        return res;
    }

    public static void copyRecordNotInclude(Record source, Record dest, String... name) {
        String[] names = source.getAttributes();
        for (String name1 : names) {
            boolean found = false;
            for (String s : name) {
                if (s.equals(name1)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                continue;
            }
            dest.setAttribute(name1, source.getAttributeAsObject(name1));
        }
    }

    public static void printRecord(Record source) {
        __logger.info(source.toMap().toString());
    }

    public static ListGridRecord[] getDataFromJSON(DataSource ds, JSONArray jsonArray) throws Exception {
        ListGridRecord[] data = new ListGridRecord[jsonArray.size()];
        DataSourceField[] fields = ds.getFields();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONValue jso1 = jsonArray.get(i);
            if (jso1 == null) {
                continue;
            }
            JSONObject son = jso1.isObject();
            if (son == null) {
                continue;
            }
            data[i] = new ListGridRecord();
            for (DataSourceField field : fields) {
                JSONValue jv = son.get(field.getName());
                if (jv == null) {
                    continue;
                }
                switch (field.getType()) {
                    case BOOLEAN:
                        data[i].setAttribute(field.getName(), __getBoolean(jv));
                        break;
                    case INTEGER:
                        data[i].setAttribute(field.getName(), __getInt(jv));
                        break;
                    case FLOAT:
                        data[i].setAttribute(field.getName(), __getDouble(jv));
                        break;
                    case DATETIME:
                        data[i].setAttribute(field.getName(), __getDate(jv));
                        break;
                    case ANY:
                        DataSource child = DataSource.get(field.getChildTagName());
                        Record[] cr;
                        if (child != null) {
                            cr = getDataFromJSON(child, (JSONArray) jv);
                        } else {
                            cr = getDataFromJSON((JSONArray) jv);
                        }
                        data[i].setAttribute(field.getName(), cr);
                        break;
                    default:
                        data[i].setAttribute(field.getName(), __getString(jv));
                        break;
                }
            }
        }
        return data;
    }

    public static String __getString(JSONValue val) {
        JSONNumber numberValue;
        JSONString stringValue;
        JSONBoolean booleanValue;
        if ((stringValue = val.isString()) != null) {
            return stringValue.stringValue().replaceAll("<BR>", "\n");
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
            sval = stringValue.stringValue().replaceAll("<BR>", "\n");
            ;
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

    public static ListGridRecord[] getDataFromJSON(JSONArray jsonArray) throws Exception {
        ListGridRecord[] data = new ListGridRecord[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONValue jso1 = jsonArray.get(i);
            if (jso1 == null) {
                continue;
            }
            JSONObject son = jso1.isObject();
            if (son == null) {
                continue;
            }
            data[i] = getDataFromJSONObject(son);
        }
        return data;
    }

    public static ListGridRecord getDataFromJSONObject(JSONObject o) throws Exception {
        JSONBoolean booleanValue;
        JSONNumber numberValue;
        JSONString stringValue;
        JSONArray arrayValue;
        JSONObject objectValue;
        if (o == null) {
            return null;
        }
        ListGridRecord r = new ListGridRecord();
        for (String kk : o.keySet()) {
            JSONValue jv = o.get(kk);
            if (jv == null) {
                continue;
            }
            if ((stringValue = jv.isString()) != null) {
                String sval = stringValue.stringValue().replaceAll("<BR>", "\n");
                if (sval.startsWith("__DATE__")) {
                    r.setAttribute(kk, __getDate(stringValue));
                } else {
                    r.setAttribute(kk, sval);
                }
                continue;
            }
            if ((numberValue = jv.isNumber()) != null) {
                r.setAttribute(kk, numberValue.doubleValue());
                continue;
            }
            if ((booleanValue = jv.isBoolean()) != null) {
                r.setAttribute(kk, booleanValue.booleanValue());
                continue;
            }
            if ((arrayValue = jv.isArray()) != null) {
                int len = arrayValue.size();
                if (len > 0) {
                    JSONValue jso1 = arrayValue.get(0);
                    if (jso1.isObject() != null) {
                        ListGridRecord[] rs = getDataFromJSON(arrayValue);
                        r.setAttribute(kk, rs);
                    } else if (jso1.isBoolean() != null) {
                        boolean[] boolArr = new boolean[len];
                        for (int i = 0; i < len; i++) {
                            boolArr[i] = arrayValue.get(i).isBoolean().booleanValue();
                        }
                        r.setAttribute(kk, boolArr);
                    } else if (jso1.isString() != null) {
                        String[] strArr = new String[len];
                        for (int i = 0; i < len; i++) {
                            strArr[i] = arrayValue.get(i).isString().stringValue();
                        }
                        r.setAttribute(kk, strArr);
                    } else if (jso1.isNumber() != null) {
                        double[] dblArr = new double[len];
                        for (int i = 0; i < len; i++) {
                            dblArr[i] = arrayValue.get(i).isNumber().doubleValue();
                        }
                        r.setAttribute(kk, dblArr);
                    }
                } else {
                    r.setAttribute(kk, new ListGridRecord[0]);
                }
                continue;
            }
            if ((objectValue = jv.isObject()) != null) {
                ListGridRecord record = getDataFromJSONObject(objectValue);
                r.setAttribute(kk, record);
            }
        }
        return r;
    }

    public static void websocketClient(final String action, final String data, final BaseFactory factory,
            final Callback<BCollection, Throwable> callback) {
        if (!Websocket.isSupported()) {
            return;
        }
        String url = getWebsocketServerURL();
        // __logger.info(url);
        StringBuilder sb = new StringBuilder();
        sb.append("action=");
        sb.append(action);
        sb.append("&content=");
        sb.append(URL.encodeQueryString(data));
        final String dataToSend = sb.toString();
        final Websocket ws = new Websocket(url);
        ws.addListener(new WebsocketListener() {

            @Override
            public void onClose() {
            }

            @Override
            public void onMessage(String msg) {
                // msg = Base64Utils.decompress(msg);
                BCollection res = new BCollection();
                res.setDataFromJSON(factory, msg);
                callback.onSuccess(res);
                ws.close();
            }

            @Override
            public void onOpen() {
                ws.send(dataToSend);
            }

            @Override
            public void onBinaryMessage(byte[] msg) {
                try {
                    String msg1 = Base64Utils.decompressFromBytes(msg);
                    BCollection res = new BCollection();
                    res.setDataFromJSON(factory, msg1);
                    callback.onSuccess(res);
                    ws.close();
                } catch (GZIPException | UnsupportedEncodingException ex) {
                    __logger.severe(ex.getMessage());
                    callback.onFailure(ex);
                    ws.close();
                }
            }

        });
        ws.open();
    }

    public static void __logRecordData(Record rd) {
        StringBuilder sb = new StringBuilder();
        String[] fn = rd.getAttributes();
        for (String name : fn) {
            if (name.startsWith("__")) {
                continue;
            }
            sb.append(name);
            sb.append(":");
            sb.append(rd.getAttribute(name));
            sb.append(";");
        }
        __logger.info(sb.toString());
    }

    public static String generateString(Object... args) {
        StringBuilder sb = new StringBuilder();
        for (Object o : args) {
            sb.append(o);
        }
        return sb.toString();
    }

    public static String getBaseURL() {
        return GWT.getHostPageBaseURL();
    }

    public static String getServerURL(String actionName) {
        return getBaseDataURL().append(actionName).toString();
    }

    public static StringBuilder getBaseDataURL() {
        StringBuilder url = new StringBuilder();
        if (GWT.isProdMode()) {
            url.append(GWT.getHostPageBaseURL());
        } else {
            url.append("http://127.0.0.1:8080/");
            url.append(__serverName);
            url.append("/");
        }
        url.append("DelicacyServlet");
        url.append("?action=");
        return url;
    }

    public static String getServerURL() {
        StringBuilder url = new StringBuilder();
        if (GWT.isProdMode()) {
            url.append(GWT.getHostPageBaseURL());
        } else {
            url.append("http://127.0.0.1:8080/");
            url.append(__serverName);
            url.append("/");
        }
        url.append("DelicacyServlet");
        return url.toString();
    }

    public static String getLocalURL(String params) {
        StringBuilder url = new StringBuilder();
        url.append("http://");
        url.append(LOCALADDRESS);
        url.append(':');
        url.append(LOCALPORT == 0 ? 8080 : LOCALPORT);
        url.append("/");
        if (__serverName != null && __serverName.trim().length() > 0) {
            url.append(__serverName);
            url.append("/");
        }
        url.append("DelicacyServlet");
        url.append("?");
        url.append(params);
        return url.toString();
    }

    public static String getWebsocketServerURL() {
        StringBuilder url = new StringBuilder();
        if (GWT.isProdMode()) {
            url.append(GWT.getHostPageBaseURL().replace("http", "ws"));
        } else {
            url.append("ws://127.0.0.1:8080/");
            url.append(__serverName);
            url.append("/");
        }
        url.append("websocket/server");
        return url.toString();
    }

    public String getActionMode() {
        return __actionMode;
    }

    public void setActionMode(String _actionMode) {
        this.__actionMode = _actionMode;
    }

    public String getActionName() {
        return __actionName;
    }

    public void setActionName(String _actionName) {
        this.__actionName = _actionName;
    }

    /**
     * @return the __serverName
     */
    public static String getServerName() {
        return __serverName;
    }

    /**
     * @param aServerName the __serverName to set
     */
    public static void setServerName(String aServerName) {
        __serverName = aServerName;
    }

    private String __actionMode = ACTION_MODE_TABLE;
    private String __actionName;
    private static String __serverName = "Delicacy";
    private DSRequest __request;

    /**
     * @return the request
     */
    public DSRequest getRequest() {
        return __request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(DSRequest request) {
        this.__request = request;
    }

}
