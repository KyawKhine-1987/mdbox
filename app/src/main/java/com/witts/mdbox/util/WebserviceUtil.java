package com.witts.mdbox.util;

import com.witts.mdbox.MDBApplication;
import com.witts.mdbox.common.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thanhtetaung on 18/1/16.
 */
public class WebserviceUtil {
    private static final String REQ_PARAM_TIME = "time";
    private static final String REQ_PARAM_DATE = "date";
    private static final String REQ_PARAM_TIMEZONE = "timezone";
    private static final String REQ_PARAM_CHANNEL = "channel";
    private static final String REQ_PARAM_CLIENT_VERSION = "clientVersion";
    private static final String REQ_PARAM_VERSION = "versionNo";
    private static final String REQ_PARAM_TOKEN = "accessToken";


    private static final String RES_PARAM_TOKEN = "accessToken";
    private static final String RES_PARAM_META = "meta";
    private static final String RES_PARAM_CODE = "responseCode";
    private static final String RES_PARAM_MESSAGE = "responseMsg";
    private static final String RES_PARAM_RESPONSE = "response";
    protected static final String WS_DEFAULT_VERSION = "0001";

    public static Map<String,String> getBasicRequestParams(){
        Map<String,String> params = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        params.put(REQ_PARAM_DATE, StringUtil.convertDateToString(cal.getTime(), StringUtil.DateFormat.DATE_FORMAT_yyyyMMdd));
        params.put(REQ_PARAM_TIME, StringUtil.convertDateToString(cal.getTime(), StringUtil.DateFormat.DATE_FORMAT_HHmmss));
        params.put(REQ_PARAM_TIMEZONE, cal.getTimeZone().getID());
        params.put(REQ_PARAM_VERSION, WS_DEFAULT_VERSION);

        params.put(REQ_PARAM_CHANNEL, Constant.CHANNEL);
        params.put(REQ_PARAM_CLIENT_VERSION, MDBApplication.clientVersionNo);

        if(!StringUtil.isEmpty(MDBApplication.accessToken))
            params.put(REQ_PARAM_TOKEN, MDBApplication.accessToken);
        return params;
    }

    public  static String extractToken(String jsonString){
        String token = null;
        try {
            JSONObject obj = new JSONObject(jsonString);
            if(obj.has(RES_PARAM_RESPONSE)){
                JSONObject response = obj.getJSONObject(RES_PARAM_RESPONSE);
                if(response.has(RES_PARAM_TOKEN)){
                    token = response.getString(RES_PARAM_TOKEN);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String getParametersAsString(Map<String, String> parameters){
        StringBuffer pString = new StringBuffer();
        for(Map.Entry<String,String> entry : parameters.entrySet()){
            if(pString.length() > 0){
                pString.append("&");
            }
            pString.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }
        return pString.toString();
    }
}
