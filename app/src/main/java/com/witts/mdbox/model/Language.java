package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/2/2017.
 */

public class Language {

    @SerializedName("languageId")
    public int languageId;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("displayName")
    public String displayName;

    @SerializedName("displayLanguageCode")
    public String displayLanguageCode;

    @SerializedName("languageFlagUrl")
    public String languageFlagUrl;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayLanguageCode() {
        return displayLanguageCode;
    }

    public void setDisplayLanguageCode(String displayLanguageCode) {
        this.displayLanguageCode = displayLanguageCode;
    }

    public String getLanguageFlagUrl() {
        return languageFlagUrl;
    }

    public void setLanguageFlagUrl(String languageFlagUrl) {
        this.languageFlagUrl = languageFlagUrl;
    }
}
