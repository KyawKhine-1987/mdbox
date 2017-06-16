package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

/*API-1
url = http://139.59.248.59/json/device/location-categorylist?date=20170606&time=042600&timezone=UTC&channel=WEB&clientVersion=1.0
&versionNo=0002&accessToken=bc614006-fb63-4147-a5f7-bde64e7b4695&languageCode=jp*/

public class Location {
    /*This data is second step of attributes from Location API-1 which is above url.*/

    @SerializedName("zoomLevel")
    public float zoomLevel;

    @SerializedName("branchId")
    public int branchId;

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("locationCategoryId")
    public int locationCategoryId;

    @SerializedName("publishInd")
    public String publishInd;

    @SerializedName("attributes")
    @Expose
    public List<LocationAttribute> LocationAttribute;

    public float getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(float zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public List<com.witts.mdbox.model.LocationAttribute> getLocationAttribute() {
        return LocationAttribute;
    }

    public void setLocationAttribute(List<com.witts.mdbox.model.LocationAttribute> locationAttribute) {
        LocationAttribute = locationAttribute;
    }
}
