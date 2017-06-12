package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/2/2017.
 */

public class QuestionWrapper {

    @SerializedName("date")
    public String date;

    @SerializedName("timezone")
    @Expose
    public String timezone;

    @SerializedName("questionCategoryList")
    @Expose
    public List<QuestionCategory> questionCategoryList;

    @SerializedName("time")
    public String time;

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

    public List<QuestionCategory> getQuestionCategoryList() {
        return questionCategoryList;
    }

    public void setQuestionCategoryList(List<QuestionCategory> questionCategoryList) {
        this.questionCategoryList = questionCategoryList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
