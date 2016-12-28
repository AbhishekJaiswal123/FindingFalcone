package com.abhishek.findingfalcone.app;

import android.app.Application;
import android.content.Context;

import com.abhishek.findingfalcone.data.source.Pref;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by abhishek on 22/12/16.
 */

public class App extends Application {

    private static RequestQueue volleyQueue;
    private static App instance;
    private static Context appContext;
    private static Pref pref;




    public static App getInstance() {
        return instance;
    }

    public static Pref getPref() {
        if (pref == null) {
            pref = new Pref(getAppContext());
        }
        return pref;
    }

    public static Context getAppContext() {
        return appContext;
    }


    public static RequestQueue getVolleyQueue() {
        if (volleyQueue == null) {
            volleyQueue = Volley.newRequestQueue(getAppContext());
        }
        return volleyQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        instance = this;
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
    }
}
