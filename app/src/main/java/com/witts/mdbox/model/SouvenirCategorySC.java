package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/12/2017.
 */

public class SouvenirCategorySC {

    @SerializedName("souvenirCategoryId")
    public int souvenirCategoryId;

    @SerializedName("branchId")
    public int branchId;

    @SerializedName("publishInd")
    public String publishInd;

    @SerializedName("attributeId")
    public int attributeId;

    @SerializedName("attributeList")
    public List<AttributeSC> attributeList;

    public int getSouvenirCategoryId() {
        return souvenirCategoryId;
    }

    public void setSouvenirCategoryId(int souvenirCategoryId) {
        this.souvenirCategoryId = souvenirCategoryId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

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

    public List<AttributeSC> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<AttributeSC> attributeList) {
        this.attributeList = attributeList;
    }
}
