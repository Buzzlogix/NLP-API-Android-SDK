/*
 * BuzzlogixTextAnalysisAPILib
 *
 * This file was automatically generated for buzzlogix by APIMATIC BETA v2.0 on 11/25/2015
 */
package com.buzzlogix;

import android.content.Context;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Configuration {
    //The base Uri for API calls
    public static String baseUri = "http://text.buzzlogix.com:8000";

    //retains status whether we have already initialized
    private static boolean initialized = false;
    private static RequestQueue requestQueue = null;

    //Initialization of configuration
    public static void initialize(Context context){
        if(initialized)
            throw new IllegalStateException("Already initialized");

        requestQueue = Volley.newRequestQueue(context);
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo app = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            Bundle bundle = app.metaData;

            apikey = bundle.getString("apikey");

            initialized = true;
        }
        catch (Exception ex){
            throw new IllegalStateException("Unable to initialize", ex);
        }
    }

    // volley request queue needs to be initialized before use
    public static RequestQueue getRequestQueue() {
        if(!initialized)
            throw new IllegalStateException("Must initialize before accessing request queue");
        return requestQueue;
    }
    

    //private backing field for Apikey
    private static String apikey = null;

    //Supply your API Key. 

    public static String getApikey() {
        if(!initialized)
            throw new IllegalStateException("Must initialize configuration before use");

        return apikey;
    }
}
