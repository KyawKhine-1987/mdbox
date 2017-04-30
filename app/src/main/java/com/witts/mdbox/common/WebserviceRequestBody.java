package com.witts.mdbox.common;

import com.witts.mdbox.util.WebserviceUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by thanhtetaung on 17/1/16.
 */
public class WebserviceRequestBody extends RequestBody {

    private RequestBody requestBody;
    private Map<String,String> parameters = new HashMap<String, String>();

    public WebserviceRequestBody(RequestBody requestBody){
        this.requestBody = requestBody;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        long originalLength = requestBody.contentLength();
        long contentLength = originalLength + WebserviceUtil.getParametersAsString(parameters).length();
        if(originalLength > 0){
            contentLength++;
        }
        return contentLength;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        requestBody.writeTo(sink);
        if(requestBody.contentLength() > 0){
            sink.writeString("&", Charset.forName("UTF-8"));
        }
        sink.writeString(WebserviceUtil.getParametersAsString(parameters), Charset.forName("UTF-8"));
    }

    public void addParameter(String name, String value){
        this.parameters.put(name, value);
    }

    public void addParameters(Map<String, String> params){
        this.parameters.putAll(params);
    }
}
