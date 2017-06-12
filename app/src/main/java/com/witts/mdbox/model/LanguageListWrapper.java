package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/2/2017.
 */

public class LanguageListWrapper {

    @SerializedName("messageId")
    public int messageId;

    @SerializedName("message")
    public String message;

    @SerializedName("key")
    public int key;

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("languageList ")
    @Expose
    public List<Menu> languageList;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
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

    public List<Menu> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Menu> languageList) {
        this.languageList = languageList;
    }
}
