package com.witts.mdbox.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm02 on 5/19/2017.
 */

public class RoomDetail {
    public String roomType;
    public String bedFee;
    public String roomSize;
    public String bedWidth;
    public String otherInfo;
    public List<String> otherInformation;
    public List<String> roomImageLarge;
    public List<String> roomImageSmall;

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBedFee() {
        return bedFee;
    }

    public void setBedFee(String bedFee) {
        this.bedFee = bedFee;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getBedWidth() {
        return bedWidth;
    }

    public void setBedWidth(String bedWidth) {
        this.bedWidth = bedWidth;
    }

    public List<String> getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(List<String> otherInformation) {
        this.otherInformation = otherInformation;
    }

    public List<String> getRoomImageLarge() {
        return roomImageLarge;
    }

    public void setRoomImageLarge(List<String> roomImageLarge) {
        this.roomImageLarge = roomImageLarge;
    }

    public List<String> getRoomImageSmall() {
        return roomImageSmall;
    }

    public void setRoomImageSmall(List<String> roomImageSmall) {
        this.roomImageSmall = roomImageSmall;
    }
}
