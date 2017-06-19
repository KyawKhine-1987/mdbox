package com.witts.mdbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kyaw Khine on 06/06/2017.
 */

/*API-2
url = http://139.59.248.59/json/device/locationlist?date=20170606&time=060000&timezone=UTC&channel=WEB&clientVersion=1.0
&versionNo=0002&accessToken=bc614006-fb63-4147-a5f7-bde64e7b4695
&languageCode=jp //This time isn't included that but it may need next time.
&locationCategoryId=1*/

public class LocationMap {
     /*This data is first step of attributes from LocationMap API-2 which is above url.*/

    @SerializedName("date")
    public String date;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("time")
    public String time;

    @SerializedName("locationList")
    @Expose
    public List<LocationList> locationList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<LocationList> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationList> locationList) {
        this.locationList = locationList;
    }
}
