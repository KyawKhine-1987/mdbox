package com.witts.mdbox.service;

import com.witts.mdbox.model.LoginMACAddressWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wm02 on 6/13/2017.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST(ApiConfig.LOGIN_URL)
    public Observable<WebServiceResult<LoginMACAddressWrapper>> getAccessToken(@Field("macAddress") String macAddress,
                                                                                @Field("date") String date, @Field("time") String time,
                                                                                @Field("timezone") String timezone, @Field("channel") String channel,
                                                                                @Field("clientVersion") String clientVersion, @Field("versionNo") String versionNo);
}
