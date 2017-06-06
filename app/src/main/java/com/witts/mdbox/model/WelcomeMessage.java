package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by winhlaingtun on 5/15/17.
 */

public class WelcomeMessage {

    @SerializedName("messageId")
    public String messageId;

    @SerializedName("message")
    public String message;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("languageList")
    @Expose
    public List<Language> languageList;

    public String DisplayLanguageName;
    public String WelcomeMessage;
    public String LanguageFlagUrl;

    public String getLanguageFlagUrl() {
        return LanguageFlagUrl;
    }

    public void setLanguageFlagUrl(String languageFlagUrl) {
        LanguageFlagUrl = languageFlagUrl;
    }

    public String getDisplayLanguageName() {
        return DisplayLanguageName;
    }

    public void setDisplayLanguageName(String displayLanguageName) {
        DisplayLanguageName = displayLanguageName;
    }

    public String getWelcomeMessage() {
        return WelcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        WelcomeMessage = welcomeMessage;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
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

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }
}
