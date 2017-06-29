package com.witts.mdbox.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 6/16/2017.
 */

public class Entertainment implements Parcelable{

    @SerializedName("branchId")
    public int branchId;

    @SerializedName("imagePaths")
    @Expose
    public List<String> imagePaths;

    @SerializedName("groupList")
    @Expose
    public List<EntertainmentGroup> groupList;

    @SerializedName("entertainmentId")
    public int entertainmentId;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public int getEntertainmentId() {
        return entertainmentId;
    }

    public void setEntertainmentId(int entertainmentId) {
        this.entertainmentId = entertainmentId;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    @SerializedName("publishInd")
    public String publishInd;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public List<EntertainmentGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<EntertainmentGroup> groupList) {
        this.groupList = groupList;
    }
}
