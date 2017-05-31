package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wm02 on 5/30/2017.
 */

public class MenuListWrapper {

    @SerializedName("date")
    @Expose
    public String date;

    @SerializedName("menuList")
    @Expose
    public List<Menu> menuList;

    @SerializedName("timezone")
    @Expose
    public String timezone;

    @SerializedName("time")
    @Expose
    public String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
