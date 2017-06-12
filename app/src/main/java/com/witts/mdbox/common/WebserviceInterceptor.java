package com.witts.mdbox.common;

import android.util.Log;

import com.witts.mdbox.MDBApplication;
import com.witts.mdbox.util.WebserviceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by thanhtetaung on 17/1/16.
 */
public class WebserviceInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        WebserviceRequestBody newBody = new WebserviceRequestBody(request.body());
        if(request.method().equalsIgnoreCase("GET")){
            HttpUrl.Builder builder = url.newBuilder();
            Map<String,String> params = WebserviceUtil.getBasicRequestParams();
            for(Map.Entry<String, String> entry : params.entrySet()) {
                builder.addQueryParameter(entry.getKey(), entry.getValue());
            }
            url = builder.build();
        }
        else {

            //adding basic params including token
            newBody.addParameters(WebserviceUtil.getBasicRequestParams());
        }
        Request.Builder builder = request.newBuilder().url(url);
        if(request.method().equalsIgnoreCase("POST")){
            builder.post(newBody);
        }
        Request newRequest = builder.build();
        Response response = chain.proceed(newRequest);

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        String content = buffer.clone().readString(Charset.forName("UTF-8"));
        Log.i("body", content);
        if(newRequest.url().toString().endsWith("/login") && response.code() == 200){
            String token = WebserviceUtil.extractToken(content);
            if(token != null){
                MDBApplication.saveToken(token);
            }
        }

        return response;
    }
}
