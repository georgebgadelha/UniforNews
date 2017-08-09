package com.example.georgebg.unifornews.application;

import android.app.Application;

import io.realm.Realm;

public class NoticiasApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
