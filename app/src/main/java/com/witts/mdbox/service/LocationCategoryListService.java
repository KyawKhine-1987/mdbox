package com.witts.mdbox.service;

import com.witts.mdbox.model.LocationCategory;
import com.witts.mdbox.model.LocationMap;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

public interface LocationCategoryListService {

    //Fetch the LocationCategoryList from API-1 and also LocationList from API-2.
    @GET(ApiConfig.LOCATION_CATEGORY_LIST_URL)
    public Observable<WebServiceResult<LocationCategory>> locationCategoryList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                               @Query("date") String date, @Query("time") String time,
                                                                               @Query("timezone") String timezone, @Query("channel") String channel,
                                                                               @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);

    @GET(ApiConfig.LOCATION_LIST_URL)
    public Observable<WebServiceResult<LocationMap>> locationList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                  @Query("date") String date, @Query("time") String time,
                                                                  @Query("timezone") String timezone, @Query("channel") String channel,
                                                                  @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo,
                                                                  @Query("locationCategoryId") int locationCategoryId);
    /*@Query("languageCode") String languageCode,*/
}
