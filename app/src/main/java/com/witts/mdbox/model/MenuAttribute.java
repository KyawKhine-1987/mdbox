package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 5/30/2017.
 */

public class MenuAttribute {

    @SerializedName("attributeId")
    public int attributeId;

    @SerializedName("menuName")
    public String menuName;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("publishInd")
    public String publishInd;

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
