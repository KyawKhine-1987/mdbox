package com.witts.mdbox.service;

import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.model.WelcomeMessageWrapper;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by winhlaingtun on 5/16/17.
 */

public interface WelcomeService {
    @POST(ApiConfig.MESSAGE_URL)
    public Observable<WebServiceResult<WelcomeMessageWrapper>> hotelLanguageSettingList(@Query("accessToken") String accessToken, @Query("keys") String key,
                                                                                        @Query("date") String date, @Query("time") String time,
                                                                                        @Query("timezone") String timezone, @Query("channel") String channel,
                                                                                        @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);
}
