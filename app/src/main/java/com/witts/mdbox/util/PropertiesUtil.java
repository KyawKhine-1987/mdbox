package com.witts.mdbox.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by wm02 on 6/19/2017.
 */

public class PropertiesUtil {

    public static String getProperty(String key,String filename,Context context) {
        String message = "";
        Properties properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("property/"+filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            //inputStream = assetManager.open("property/"+filename);
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        message =properties.getProperty(key);
        return message;

    }
}
