package com.alexoree.testcenter.app1;

import android.app.Application;

import com.alexoree.testcenter.android.TestCenter;

/**
 * Created by alex on 8/31/16.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        TestCenter.install(this);
    }
}
