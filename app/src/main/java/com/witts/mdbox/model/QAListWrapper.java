package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 5/29/2017.
 */

public class QAListWrapper {

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("qalist")
    @Expose
    public List<QAObject> qalist;

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

    public List<QAObject> getQalist() {
        return qalist;
    }

    public void setQalist(List<QAObject> qalist) {
        this.qalist = qalist;
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
