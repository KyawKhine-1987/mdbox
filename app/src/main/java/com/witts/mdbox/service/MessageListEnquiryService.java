package com.witts.mdbox.service;

import com.witts.mdbox.model.LanguageListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 6/2/2017.
 */

public interface MessageListEnquiryService {

    @GET(ApiConfig.MESSAGE_LIST_ENQUIRY_URL)
    public Observable<WebServiceResult<LanguageListWrapper>> mainMenuEnquiry(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                             @Query("key") String key, @Query("date") String date, @Query("time") String time,
                                                                             @Query("timezone") String timezone, @Query("channel") String channel,
                                                                             @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);
}
