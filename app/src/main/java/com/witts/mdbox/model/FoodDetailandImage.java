package com.witts.mdbox.model;

import java.util.List;

/**
 * Created by wm02 on 6/15/2017.
 */

public class FoodDetailandImage {

    public String imageUrl;
    public List<FoodDetail> foodDetailList;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<FoodDetail> getFoodDetailList() {
        return foodDetailList;
    }

    public void setFoodDetailList(List<FoodDetail> foodDetailList) {
        this.foodDetailList = foodDetailList;
    }
}
