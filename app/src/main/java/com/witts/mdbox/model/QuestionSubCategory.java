package com.witts.mdbox.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/2/2017.
 */

public class QuestionSubCategory implements Parcelable {

    @SerializedName("sortingOrder")
    public int sortingOrder;

    @SerializedName("questionSubCategoryId")
    public int questionSubCategoryId;

    @SerializedName("questionCategoryId")
    public int questionCategoryId;

    @SerializedName("iconPath")
    public String iconPath;

    @SerializedName("questionList")
    @Expose
    public List<Question> questionList;

    @SerializedName("subCategoryAttributes")
    @Expose
    public List<SubCategoryAttributes> subCategoryAttributes;

    @SerializedName("publishInd")
    public String publishInd;

    protected QuestionSubCategory(Parcel in) {
        sortingOrder = in.readInt();
        questionSubCategoryId = in.readInt();
        questionCategoryId = in.readInt();
        iconPath = in.readString();
        publishInd = in.readString();
    }

    public static final Creator<QuestionSubCategory> CREATOR = new Creator<QuestionSubCategory>() {
        @Override
        public QuestionSubCategory createFromParcel(Parcel in) {
            return new QuestionSubCategory(in);
        }

        @Override
        public QuestionSubCategory[] newArray(int size) {
            return new QuestionSubCategory[size];
        }
    };

    public int getSortingOrder() {
        return sortingOrder;
    }

    public void setSortingOrder(int sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public int getQuestionSubCategoryId() {
        return questionSubCategoryId;
    }

    public void setQuestionSubCategoryId(int questionSubCategoryId) {
        this.questionSubCategoryId = questionSubCategoryId;
    }

    public int getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(int questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<SubCategoryAttributes> getSubCategoryAttributes() {
        return subCategoryAttributes;
    }

    public void setSubCategoryAttributes(List<SubCategoryAttributes> subCategoryAttributes) {
        this.subCategoryAttributes = subCategoryAttributes;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sortingOrder);
        dest.writeInt(questionSubCategoryId);
        dest.writeInt(questionCategoryId);
        dest.writeString(iconPath);
        dest.writeString(publishInd);
    }
}
