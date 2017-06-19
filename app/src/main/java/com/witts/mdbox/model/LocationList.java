package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

/*API-2
url = http://139.59.248.59/json/device/locationlist?date=20170606&time=060000&timezone=UTC&channel=WEB&clientVersion=1.0
&versionNo=0002&accessToken=bc614006-fb63-4147-a5f7-bde64e7b4695
&languageCode=jp //This time isn't included that but it may need next time.
&locationCategoryId=1*/

public class LocationList {
    /*This data is second step of attributes from LocationMap API-2 which is above url.*/

    @SerializedName("zoomLevel")
    public float zoomLevel;

    @SerializedName("branchId")
    public int branchId;

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("locationName")
    public String locationName;

    @SerializedName("address")
    public String address;

    @SerializedName("postalCode")
    public String postalCode;

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("description")
    public String description;

    @SerializedName("telephone")
    public String telephone;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("locationCategoryId")
    public int locationCategoryId;

    @SerializedName("url")
    public String url;

    @SerializedName("locationId")
    public int locationId;

    @SerializedName("placeName")
    public String placeName;

    @SerializedName("publishInd")
    public String publishInd;

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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public int getLocationCategoryId() {
        return locationCategoryId;
    }

    public void setLocationCategoryId(int locationCategoryId) {
        this.locationCategoryId = locationCategoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
