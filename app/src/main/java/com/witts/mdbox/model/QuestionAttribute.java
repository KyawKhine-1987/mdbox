package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wm02 on 6/2/2017.
 */

public class QuestionAttribute {

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("questionAttributeId")
    public int questionAttributeId;

    @SerializedName("languageCode")
    public String languageCode;

    @SerializedName("questionText")
    public String questionText;

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public int getQuestionAttributeId() {
        return questionAttributeId;
    }

    public void setQuestionAttributeId(int questionAttributeId) {
        this.questionAttributeId = questionAttributeId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
