package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/14/2017.
 */

public class FoodMenuWrapper {

    @SerializedName("date")
    public String date;

    @SerializedName("foodCategoryList")
    public List<FoodMenu> foodCategoryList;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("time")
    public String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<FoodMenu> getFoodCategoryList() {
        return foodCategoryList;
    }

    public void setFoodCategoryList(List<FoodMenu> foodCategoryList) {
        this.foodCategoryList = foodCategoryList;
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
