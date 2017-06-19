package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/12/2017.
 */

public class SouvenirAttribute {

    @SerializedName("attributeId")
    public int attributeId;

    @SerializedName("souvenirId")
    public int souvenirId;

    @SerializedName("attributeName")
    public String attributeName;

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("displayName")
    public String displayName;

    @SerializedName("aliasName")
    public String aliasName;

    @SerializedName("value")
    public String value;

    @SerializedName("unit")
    public String unit;

    @SerializedName("publishInd")
    public String publishInd;

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    public int getSouvenirId() {
        return souvenirId;
    }

    public void setSouvenirId(int souvenirId) {
        this.souvenirId = souvenirId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
