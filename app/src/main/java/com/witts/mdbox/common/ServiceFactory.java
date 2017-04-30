package com.witts.mdbox.common;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by thanhtetaung on 16/1/16.
 */
public class ServiceFactory {

    private static Retrofit retrofit;

    private ServiceFactory(){

    }

    static{
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new WebserviceInterceptor()).build();
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static <T> T getService(Class<T> _class){
        return retrofit.create(_class);
    }
}
