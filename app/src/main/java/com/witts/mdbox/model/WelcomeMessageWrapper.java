package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by winhlaingtun on 5/15/17.
 */

public class WelcomeMessageWrapper {
    public int branchId;
    public int hotelId;

    @SerializedName("logo_url")
    @Expose
    public String logoUrl;

    @SerializedName("welcomeMessageList")
    @Expose
    public List<WelcomeMessage> welcomeMessageList;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<WelcomeMessage> getWelcomeMessageList() {
        return welcomeMessageList;
    }

    public void setWelcomeMessageList(List<WelcomeMessage> welcomeMessageList) {
        this.welcomeMessageList = welcomeMessageList;
    }
}
