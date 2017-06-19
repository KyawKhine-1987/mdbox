package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/12/2017.
 */

public class SouvenirCategory {

    @SerializedName("souvenirCategoryId")
    public int souvenirCategoryId;

    @SerializedName("branchId")
    public int branchId;

    @SerializedName("publishInd")
    public String publishInd;

    @SerializedName("categoryAttributeList")
    @Expose
    public List<CategoryAttribute> categoryAttributeList;

    @SerializedName("souvenirList")
    @Expose
    public List<Souvenir> souvenirList;

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

    public List<CategoryAttribute> getCategoryAttributeList() {
        return categoryAttributeList;
    }

    public void setCategoryAttributeList(List<CategoryAttribute> categoryAttributeList) {
        this.categoryAttributeList = categoryAttributeList;
    }

    public List<Souvenir> getSouvenirList() {
        return souvenirList;
    }

    public void setSouvenirList(List<Souvenir> souvenirList) {
        this.souvenirList = souvenirList;
    }
}
