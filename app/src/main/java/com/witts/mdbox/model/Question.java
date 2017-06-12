package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/2/2017.
 */

public class Question {

    @SerializedName("questionAttributes")
    @Expose
    public List<QuestionAttribute> questionAttributes;

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("questionId")
    public int questionId;

    @SerializedName("answerIds")
    @Expose
    public List<Integer> answerIds;

    @SerializedName("priority")
    public int priority;

    @SerializedName("publishInd")
    public String publishInd;

    public List<QuestionAttribute> getQuestionAttributes() {
        return questionAttributes;
    }

    public void setQuestionAttributes(List<QuestionAttribute> questionAttributes) {
        this.questionAttributes = questionAttributes;
    }

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public List<Integer> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Integer> answerIds) {
        this.answerIds = answerIds;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
