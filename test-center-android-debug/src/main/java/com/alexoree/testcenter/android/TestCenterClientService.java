package com.alexoree.testcenter.android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by alex on 8/31/16.
 */
public class TestCenterClientService extends Service implements Runnable{
    /**
     * are we connected to a Test Center Server?
     */
    boolean connected=false;

    public TestCenterClientService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void setCurrentActivity(){

    }
    //keep track of the current activity

    //send broadcasts to the mothership every 30 seconds until a replace
    //upon reply, stop broadcast and switch to unicast

    //wait to calls for

    public boolean isConnected(){
        return connected;
    }

    @Override
    public void run() {

    }
}
