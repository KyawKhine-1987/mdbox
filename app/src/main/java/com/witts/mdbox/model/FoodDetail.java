package com.witts.mdbox.model;

/**
 * Created by wm02 on 5/23/2017.
 */

public class FoodDetail {
    public String foodName;
    public String japaneseName;
    public String foodPrice;
    public String foodMaterial;
    public String otherInfo;
    public String foodImageSmallUrl;
    public String foodImageLargeUrl;

    public String getFoodImageSmallUrl() {
        return foodImageSmallUrl;
    }

    public void setFoodImageSmallUrl(String foodImageSmallUrl) {
        this.foodImageSmallUrl = foodImageSmallUrl;
    }

    public String getFoodImageLargeUrl() {
        return foodImageLargeUrl;
    }

    public void setFoodImageLargeUrl(String foodImageLargeUrl) {
        this.foodImageLargeUrl = foodImageLargeUrl;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodMaterial() {
        return foodMaterial;
    }

    public void setFoodMaterial(String foodMaterial) {
        this.foodMaterial = foodMaterial;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
