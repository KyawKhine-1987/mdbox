package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/12/2017.
 */

public class Souvenir {

    @SerializedName("souvenirId")
    public int souvenirId;

    @SerializedName("souvenirCategoryId")
    public int souvenirCategoryId;

    @SerializedName("imagePath")
    public String imagePath;

    @SerializedName("publishInd")
    public String publishInd;

    @SerializedName("attributeList")
    @Expose
    public List<SouvenirAttribute> attributeList;

    public int getSouvenirCategoryId() {
        return souvenirCategoryId;
    }

    public void setSouvenirCategoryId(int souvenirCategoryId) {
        this.souvenirCategoryId = souvenirCategoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    public List<SouvenirAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<SouvenirAttribute> attributeList) {
        this.attributeList = attributeList;
    }

    public int getSouvenirId() {
        return souvenirId;
    }

    public void setSouvenirId(int souvenirId) {
        this.souvenirId = souvenirId;
    }
}
