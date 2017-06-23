package com.witts.mdbox.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 5/19/2017.
 */

public class RoomType implements Parcelable {
     public String roomType;

    @SerializedName("images")
    @Expose
    public List<String> images;

    @SerializedName("groupList")
    @Expose
    public List<RoomTypeGroup> groupList;

    @SerializedName("roomTypeId")
    public int roomTypeId;

    @SerializedName("publishInd")
    public String publishInd;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getPublishInd() {
        return publishInd;
    }

    public void setPublishInd(String publishInd) {
        this.publishInd = publishInd;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public List<RoomTypeGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<RoomTypeGroup> groupList) {
        this.groupList = groupList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
