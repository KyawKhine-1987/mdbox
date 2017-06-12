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

    @SerializedName("languageList")
    @Expose
    public List<Language> languageList;

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public List<Key> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Key> keyList) {
        this.keyList = keyList;
    }

    @SerializedName("keyList")
    @Expose
    public List<Key> keyList;

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

}
