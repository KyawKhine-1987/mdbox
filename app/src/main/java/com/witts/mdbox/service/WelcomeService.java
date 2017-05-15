package com.witts.mdbox.service;

import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.model.WelcomeMessageWrapper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by winhlaingtun on 5/16/17.
 */

public interface WelcomeService {
    @POST("HotelLanguageSettingEnquiryAPI")
    public Observable<WebServiceResult<WelcomeMessageWrapper>> hotelLanguageSettingList();
}
