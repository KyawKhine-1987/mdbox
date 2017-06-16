package com.witts.mdbox.service;

import com.witts.mdbox.model.RoomTypeListWrapper;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.util.api.ApiConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wm02 on 6/15/2017.
 */

public interface RoomTypeService {

    @GET(ApiConfig.ROOM_TYPE_LIST_URL)
    public Observable<WebServiceResult<RoomTypeListWrapper>> roomTypeList(@Query("accessToken") String accessToken, @Query("languageCode") String languageCode,
                                                                              @Query("date") String date, @Query("time") String time,
                                                                              @Query("timezone") String timezone, @Query("channel") String channel,
                                                                              @Query("clientVersion") String clientVersion, @Query("versionNo") String versionNo);
}
