package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/14/2017.
 */

public class FoodCategory {

    @SerializedName("foodCategoryId")
    public int foodCategoryId;

    @SerializedName("restaurantId")
    public int restaurantId;

    @SerializedName("attributeList")
    @Expose
    public List<AttributeSC> attributeList;

    @SerializedName("publishInd")
    public String publishInd;

    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    public void setFoodCategoryId(int foodCategoryId) {
        this.foodCategoryId = foodCategoryId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<AttributeSC> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<AttributeSC> attributeList) {
        this.attributeList = attributeList;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
