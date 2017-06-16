package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/14/2017.
 */

public class Food {

    @SerializedName("period")
    public String period;

    @SerializedName("foodCategoryId")
    public int foodCategoryId;

    @SerializedName("countryCode")
    public String countryCode;

    @SerializedName("imagePath")
    public String imagePath;

    @SerializedName("foodId")
    public int foodId;

    @SerializedName("attributeList")
    @Expose
    public List<FoodAttribute> attributeList;

    @SerializedName("restaurantId")
    public int restaurantId;

    @SerializedName("publishInd")
    public String publishInd;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public List<FoodAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<FoodAttribute> attributeList) {
        this.attributeList = attributeList;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
