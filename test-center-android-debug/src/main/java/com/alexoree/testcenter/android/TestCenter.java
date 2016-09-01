package com.alexoree.testcenter.android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.alexoree.testcenter.android.net.TestCenterClientService;

/**
 * Created by alex on 8/31/16.
 */
public class TestCenter {



    public static void initialize(Context ctx){

    }


    public static void install(Application application) {
        ActivityWatcher.installOnIcsPlus(application);

        application.startService(new Intent(application, TestCenterClientService.class));
    }
}
