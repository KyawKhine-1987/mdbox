package com.witts.mdbox.service;

import com.witts.mdbox.model.FoodCategoryWrapper;
import com.witts.mdbox.model.FoodMenuWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 6/14/2017.
 */

public interface FoodCategoryListService {

    @GET(ApiConfig.FOOD_CATEGORY_LIST_URL)
    public Observable<WebServiceResult<FoodCategoryWrapper>> foodCategoryList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                                  @Query("date") String date, @Query("time") String time,
                                                                                  @Query("timezone") String timezone, @Query("channel") String channel,
                                                                                  @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo,
                                                                                  @Query("restaurantId")int restaurantId);

    @GET(ApiConfig.FOOD_MENU_URL)
    public Observable<WebServiceResult<FoodMenuWrapper>> foodMenuList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                      @Query("date") String date, @Query("time") String time,
                                                                      @Query("timezone") String timezone, @Query("channel") String channel,
                                                                      @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo,
                                                                      @Query("foodCategoryId") int foodCategoryId);
}
