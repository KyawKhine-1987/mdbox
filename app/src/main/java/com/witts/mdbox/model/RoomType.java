package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 5/19/2017.
 */

public class RoomType {
     public String roomType;

    @SerializedName("images")
    @Expose
    public List<String> images;

    @SerializedName("attributeList")
    @Expose
    public List<RoomAttribute> attributeList;

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

    public List<RoomAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<RoomAttribute> attributeList) {
        this.attributeList = attributeList;
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
}
