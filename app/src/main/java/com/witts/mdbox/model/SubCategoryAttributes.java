package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/2/2017.
 */

public class SubCategoryAttributes {

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("name")
    public String name;

    @SerializedName("subCategoryAttributeId")
    public int subCategoryAttributeId;

    @SerializedName("languageCode")
    public String languageCode;

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

    public int getSubCategoryAttributeId() {
        return subCategoryAttributeId;
    }

    public void setSubCategoryAttributeId(int subCategoryAttributeId) {
        this.subCategoryAttributeId = subCategoryAttributeId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}
