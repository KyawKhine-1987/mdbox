package com.witts.mdbox.service;

import com.witts.mdbox.model.QAListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wm02 on 5/29/2017.
 */

public interface QAListService {
    @FormUrlEncoded
    @POST(ApiConfig.QA_LIST_API_URL)
    public Observable<WebServiceResult<QAListWrapper>> qaList(@Field("accessToken") String accessToken, @Field("languageCode") String languageCode,
                                                              @Field("date") String date, @Field("time") String time,
                                                              @Field("timezone") String timezone, @Field("channel") String channel,
                                                              @Field("clientVersion") String clientVersion, @Field("versionNo") String versionNo);
}
