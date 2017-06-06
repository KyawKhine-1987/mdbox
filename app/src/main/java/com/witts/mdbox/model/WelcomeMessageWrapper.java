package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by winhlaingtun on 5/15/17.
 */

public class WelcomeMessageWrapper {

    @SerializedName("date")
    public String date;

    @SerializedName("sortingOrder")
    public String sortingOrder;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("time")
    public String time;

    @SerializedName("key")
    public String key;

    @SerializedName("welcomeMessageList")
    @Expose
    public List<WelcomeMessage> welcomeMessageList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<WelcomeMessage> getWelcomeMessageList() {
        return welcomeMessageList;
    }

    public void setWelcomeMessageList(List<WelcomeMessage> welcomeMessageList) {
        this.welcomeMessageList = welcomeMessageList;
    }
}
