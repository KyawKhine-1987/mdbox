package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

/*API-1
url = http://139.59.248.59/json/device/location-categorylist?date=20170606&time=042600&timezone=UTC&channel=WEB&clientVersion=1.0
&versionNo=0002&accessToken=bc614006-fb63-4147-a5f7-bde64e7b4695&languageCode=jp*/

public class LocationAttribute {
    /*This data is third step of attributes from LocationAttribute API-1 which is above url.*/

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("name")
    public String name;

    @SerializedName("categoryAttributeId")
    public int categoryAttributeId;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("value")
    public String value;

    @SerializedName("locationCategoryId")
    public int locationCategoryId;

    @SerializedName("publishInd")
    public String publishInd;

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryAttributeId() {
        return categoryAttributeId;
    }

    public void setCategoryAttributeId(int categoryAttributeId) {
        this.categoryAttributeId = categoryAttributeId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLocationCategoryId() {
        return locationCategoryId;
    }

    public void setLocationCategoryId(int locationCategoryId) {
        this.locationCategoryId = locationCategoryId;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
