package com.witts.mdbox;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.gson.Gson;
import com.witts.mdbox.common.Constant;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by winhlaingtun on 4/30/17.
 */

public class MDBApplication extends Application {
    private static SharedPreferences sharedPreference;
    public static String accessToken = null;
    public static String refreshToken = null;
    public static String clientVersionNo = null;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setFontAttrId(R.attr.fontPath).build());
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arial.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            clientVersionNo = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        sharedPreference = this.getSharedPreferences(Constant.PREFERENCE_KEY, MODE_PRIVATE);
        accessToken = this.getStringSharedPreference(Constant.PREFERENCE_TOKEN_KEY);
        refreshToken = this.getStringSharedPreference(Constant.PREFERENCE_REFRESH_KEY);
    }

    public void saveStringSharedPreference(String key,String value){
        sharedPreference.edit().putString(key,value).commit();
    }

    public void saveListSharedPreference(String key,List<?> value){
        Gson gson = new Gson();
        String jsonvalue = gson.toJson(value);
        sharedPreference.edit().putString(key, jsonvalue).commit();
    }

    public void saveBooleanSharedPreference(String key, boolean value){
        sharedPreference.edit().putBoolean(key,value).commit();
    }

    public String getStringSharedPreference(String key){
        return sharedPreference.getString(key, "");
    }

    public Boolean getBooleanSharedPreference(String key){
        return sharedPreference.getBoolean(key, false);
    }

    public void clearSharedPreference(String key){
        sharedPreference.edit().remove(key).commit();
    }

    public static void saveToken(String token) {
        accessToken = token;
        sharedPreference.edit().putString(Constant.PREFERENCE_TOKEN_KEY, token).commit();
    }

    public static void saveRefreshToken(String token){
        refreshToken = token;
        sharedPreference.edit().putString(Constant.PREFERENCE_REFRESH_KEY, token).commit();
    }

    public void clearSharedPreference(){
        sharedPreference.edit().clear().commit();
    }

    public static boolean isAuthenticated(){
        return accessToken != null && !accessToken.isEmpty();
    }

    public static void notifyLogout(){
        accessToken = null;
    }
}
