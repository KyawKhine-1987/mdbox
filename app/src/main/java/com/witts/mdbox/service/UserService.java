package com.witts.mdbox.service;

import com.witts.mdbox.model.WebServiceResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by thanhtetaung on 16/1/16.
 */
public interface UserService {
    @FormUrlEncoded
    @POST("login")
    public Observable<WebServiceResult> login(@Field("username") String userName, @Field("password") String password);
}