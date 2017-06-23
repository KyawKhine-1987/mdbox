package com.witts.mdbox.common;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wm02 on 6/22/2017.
 */

public class StatusBar implements CommonStatusBar{
    Context context;

    public StatusBar(Context context) {
        this.context = context;
    }

    @Override
    public int getWiFiSignal() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        // Level of a Scan Result
        List<ScanResult> wifiList = wifiManager.getScanResults();
        for (ScanResult scanResult : wifiList) {
            int level = WifiManager.calculateSignalLevel(scanResult.level, 3);
            Log.d("Wifi Signal Scan Result","Level is " + level + " out of 3.");
        }

        // Level of current connection
        int rssi = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(rssi, 3);
        Log.d("Wifi current connection","Level is " + level + " out of 3.");
        return level;
    }

    @Override
    public String getCommonDateTime(String languageCode) {
        String datetime = "";
        if(languageCode.equalsIgnoreCase("jp"))
            datetime = formatTime(new Date(), Locale.JAPANESE);
        else if(languageCode.equalsIgnoreCase("zh"))
            datetime = formatTime(new Date(), Locale.SIMPLIFIED_CHINESE);
        else if(languageCode.equalsIgnoreCase("en"))
            datetime = formatTime(new Date(), Locale.ENGLISH);
        else if(languageCode.equalsIgnoreCase("ko"))
            datetime = formatTime(new Date(), Locale.KOREAN);
        return datetime;
    }

    public static String formatTime(Date time, Locale locale){

        SimpleDateFormat formatter;

        try {
            formatter = new SimpleDateFormat("yyyy/MM/dd EEE kk:mm", locale);
        } catch(Exception e) {
            formatter = new SimpleDateFormat("yyyy/MM/dd EEE kk:mm", locale);
        }
        return formatter.format(time);
    }
}
