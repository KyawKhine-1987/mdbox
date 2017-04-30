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

        if(MDBApplication.accessToken != null){
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + MDBApplication.accessToken)
                    .build();
        }

        if(request.method().equalsIgnoreCase("GET") || request.body() instanceof MultipartBody){
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
            builder.header("Content-Type", "application/json");
            builder.post(newBody);
        }
        Request newRequest = builder.build();
        Response response = chain.proceed(newRequest);

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        String content = buffer.clone().readString(Charset.forName("UTF-8"));
        Log.i("body", content);

        //validating json
        try{
            JSONObject obj = new JSONObject(content);
            if(response.code() != 200){
                String desc = obj.getString("error_description");
                String error = obj.getString("error");

                WebserviceException exception = new WebserviceException(desc);
                exception.setErrorMessage(desc);
                exception.setError(error);
                exception.setCode(response.code());
                throw exception;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("Invalid Response");
        } catch (WebserviceException e){
            throw e;
        }
        return response;
    }
}
