package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/8/2017.
 */

public class Message {

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("messageId")
    public int messageId;

    @SerializedName("message")
    public String message;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("key")
    public String key;

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

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

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
