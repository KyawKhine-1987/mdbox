package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm02 on 6/2/2017.
 */

public class QuestionCategory {

        @SerializedName("questionSubCategoryList")
        @Expose
        public ArrayList<QuestionSubCategory> questionSubCategoryList;

        @SerializedName("branchId")
        public int branchId;

        @SerializedName("categoryAttributes")
        @Expose
        public List<CategoryAttributes> categoryAttributes;

        @SerializedName("questionCategoryId")
        public int questionCategoryId;

        @SerializedName("publishInd")
        public String publishInd;

    public ArrayList<QuestionSubCategory> getQuestionSubCategoryList() {
        return questionSubCategoryList;
    }

    public void setQuestionSubCategoryList(ArrayList<QuestionSubCategory> questionSubCategoryList) {
        this.questionSubCategoryList = questionSubCategoryList;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public List<CategoryAttributes> getCategoryAttributes() {
        return categoryAttributes;
    }

    public void setCategoryAttributes(List<CategoryAttributes> categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
    }

    public int getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(int questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }
}
