package com.chinmay.globantconnect;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by chinmay.deshpande on 11/04/18.
 */

public class GlobantConnectApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}