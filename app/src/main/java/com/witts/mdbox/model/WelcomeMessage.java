package com.witts.mdbox.model;

/**
 * Created by winhlaingtun on 5/15/17.
 */

public class WelcomeMessage {
    public String welcomeMessage;
    public String languageCode;
    public String languageFlagUrl;
    public String displayLanguageName;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageFlagUrl() {
        return languageFlagUrl;
    }

    public void setLanguageFlagUrl(String languageFlagUrl) {
        this.languageFlagUrl = languageFlagUrl;
    }

    public String getDisplayLanguageName() {
        return displayLanguageName;
    }

    public void setDisplayLanguageName(String displayLanguageName) {
        this.displayLanguageName = displayLanguageName;
    }
}
