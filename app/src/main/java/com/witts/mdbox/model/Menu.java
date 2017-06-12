package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 5/30/2017.
 */

public class Menu {

    @SerializedName("menuId")
    public int menuId;

    @SerializedName("branchId")
    public int branchId;

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("logoPath")
    public String logoPath;

    @SerializedName("publishInd")
    public String publishInd;

    @SerializedName("menuAttributes")
    @Expose
    public List<MenuAttribute> menuAttributes;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    public List<MenuAttribute> getMenuAttributes() {
        return menuAttributes;
    }

    public void setMenuAttributes(List<MenuAttribute> menuAttributes) {
        this.menuAttributes = menuAttributes;
    }
}
