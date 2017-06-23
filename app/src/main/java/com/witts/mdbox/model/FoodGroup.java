package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/23/2017.
 */

public class FoodGroup {

    @SerializedName("groupOrder")
    public int groupOrder;

    @SerializedName("attributeList")
    @Expose
    public List<FoodAttribute> attributeList;

    @SerializedName("key")
    public String key;

    public int getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }

    public List<FoodAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<FoodAttribute> attributeList) {
        this.attributeList = attributeList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
