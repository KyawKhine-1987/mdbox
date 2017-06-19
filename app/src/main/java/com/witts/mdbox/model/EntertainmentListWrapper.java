package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/16/2017.
 */

public class EntertainmentListWrapper {

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("entertainmentList")
    @Expose
    public List<Entertainment> entertainmentList;

    @SerializedName("timezone")
    @Expose
    public String timezone;

    @SerializedName("time")
    @Expose
    public String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Entertainment> getEntertainmentList() {
        return entertainmentList;
    }

    public void setEntertainmentList(List<Entertainment> entertainmentList) {
        this.entertainmentList = entertainmentList;
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
