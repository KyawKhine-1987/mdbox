package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/12/2017.
 */

public class SouvenirCategoryListWrapper {

    @SerializedName("date")
    public String date;

    @SerializedName("timezone")
    @Expose
    public String timezone;

    @SerializedName("languageList")
    @Expose
    public List<Language> languageList;

    @SerializedName("souvenirCategoryList")
    @Expose
    public List<SouvenirCategorySC> souvenirCategoryList;

    @SerializedName("time")
    public String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public List<SouvenirCategorySC> getSouvenirCategoryList() {
        return souvenirCategoryList;
    }

    public void setSouvenirCategoryList(List<SouvenirCategorySC> souvenirCategoryList) {
        this.souvenirCategoryList = souvenirCategoryList;
    }
}
