package com.witts.mdbox.service;

import com.witts.mdbox.model.MenuListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 5/30/2017.
 */

public interface MainMenuEnquiryService {

    @GET(ApiConfig.MAIN_MENU_ENQUIRY_URL)
    public Observable<WebServiceResult<MenuListWrapper>> mainMenuEnquiry(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                         @Query("date") String date, @Query("time") String time,
                                                                         @Query("timezone") String timezone, @Query("channel") String channel,
                                                                         @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);
}
