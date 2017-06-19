package com.witts.mdbox.service;

import com.witts.mdbox.model.SouvenirCategoryListWrapper;
import com.witts.mdbox.model.SouvenirListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 6/12/2017.
 */

public interface SouvenirService {

    @GET(ApiConfig.SOUVENIR_CATEGORY_LIST_URL)
    public Observable<WebServiceResult<SouvenirCategoryListWrapper>> souvenirCategoryList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                               @Query("date") String date, @Query("time") String time,
                                                                               @Query("timezone") String timezone, @Query("channel") String channel,
                                                                               @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);

    @GET(ApiConfig.SOUVENIR_LIST_URL)
    public Observable<WebServiceResult<SouvenirListWrapper>> souvenirList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                          @Query("date") String date, @Query("time") String time,
                                                                          @Query("timezone") String timezone, @Query("channel") String channel,
                                                                          @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo,
                                                                          @Query("souvenirCategoryId") int souvenirCategoryId);
}
