package com.witts.mdbox.util;

import com.witts.mdbox.MDBApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thanhtetaung on 18/1/16.
 */
public class WebserviceUtil {
    private static final String REQ_PARAM_TOKEN = "accessToken";

    private static final String RES_PARAM_TOKEN = "accessToken";
    private static final String RES_PARAM_RESPONSE = "response";

    public static Map<String,String> getBasicRequestParams(){
        Map<String,String> params = new HashMap<String, String>();

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
