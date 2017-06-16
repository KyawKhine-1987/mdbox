package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/12/2017.
 */

public class CategoryAttribute {

    @SerializedName("categoryAttributeId")
    public int categoryAttributeId;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("attributeName")
    public String attributeName;

    @SerializedName("displayName")
    public String displayName;

    @SerializedName("aliasName")
    public String aliasName;

    @SerializedName("value")
    public String value;

    @SerializedName("publishInd")
    public String publishInd;

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

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
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

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
