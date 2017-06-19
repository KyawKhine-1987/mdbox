package com.witts.mdbox.service;

import com.witts.mdbox.model.RestaurantWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 6/14/2017.
 */

public interface RestaurantService {

    @GET(ApiConfig.RESTAURANT_URL)
    public Observable<WebServiceResult<RestaurantWrapper>> foodCategoryList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                            @Query("date") String date, @Query("time") String time,
                                                                            @Query("timezone") String timezone, @Query("channel") String channel,
                                                                            @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);

}
