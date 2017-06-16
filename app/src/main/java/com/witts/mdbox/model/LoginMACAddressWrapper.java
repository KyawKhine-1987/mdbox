package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/13/2017.
 */

public class LoginMACAddressWrapper {

    @SerializedName("accessToken")
    public String accessToken;

    @SerializedName("hotelId")
    public int hotelId;

    @SerializedName("hotelAliasName")
    public String hotelAliasName;

    @SerializedName("hotelName")
    public String hotelName;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelAliasName() {
        return hotelAliasName;
    }

    public void setHotelAliasName(String hotelAliasName) {
        this.hotelAliasName = hotelAliasName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
