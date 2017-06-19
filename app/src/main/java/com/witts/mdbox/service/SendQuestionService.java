package com.witts.mdbox.service;

import com.witts.mdbox.model.SendQuestionWrapper;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wm02 on 6/14/2017.
 */

public interface SendQuestionService {
    @FormUrlEncoded
    @POST(ApiConfig.SEND_QUESTION_URL)
    public Observable<SendQuestionWrapper> getAccessToken(@Field("accessToken")String accessToken,@Field("questionId")int questionId,
                                                        @Field("date") String date, @Field("time") String time,
                                                        @Field("timezone") String timezone, @Field("channel") String channel,
                                                        @Field("clientVersion") String clientVersion, @Field("versionNo") String versionNo);
}
