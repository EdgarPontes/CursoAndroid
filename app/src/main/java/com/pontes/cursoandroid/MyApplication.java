package com.pontes.cursoandroid;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration rc = new RealmConfiguration.Builder().name("cursoandroid.realm").build();

        Realm.deleteRealm(rc);

        Realm.setDefaultConfiguration(rc);
    }
}
