package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by winhlaingtun on 5/15/17.
 */

public class WelcomeMessage {
    public String DisplayLanguageName;
    public String WelcomeMessage;
    public String LanguageFlagUrl;
    public String LanguageCode;

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

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

}
