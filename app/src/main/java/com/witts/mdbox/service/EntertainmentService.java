package com.witts.mdbox.service;

import com.witts.mdbox.model.EntertainmentListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 6/16/2017.
 */

public interface EntertainmentService {

    @GET(ApiConfig.ENTERTAINMENT_LIST_URL)
    public Observable<WebServiceResult<EntertainmentListWrapper>> roomTypeList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                               @Query("date") String date, @Query("time") String time,
                                                                               @Query("timezone") String timezone, @Query("channel") String channel,
                                                                               @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);
}
