package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/14/2017.
 */

public class Restaurant {

    @SerializedName("imagePath")
    public String imagePath;

    @SerializedName("attributeList")
    @Expose
    public List<RestaurantAttribute> attributeList;

    @SerializedName("restaurantId")
    public int restaurantId;

    @SerializedName("publishInd")
    public String publishInd;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<RestaurantAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<RestaurantAttribute> attributeList) {
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
