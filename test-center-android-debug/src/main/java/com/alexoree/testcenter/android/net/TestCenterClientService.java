package com.alexoree.testcenter.android.net;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.alexoree.testcenter.android.ActivityWatcher;
import com.alexoree.testcenter.messages.BaseMessage;
import com.alexoree.testcenter.messages.ExecMsg;

import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by alex on 8/31/16.
 */
public class TestCenterClientService  implements Runnable{
    /**
     * are we connected to a Test Center Server?
     */
    boolean connected=false;
    Thread sender;
    Thread receiver;

    public TestCenterClientService(){

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
        DiscoSender discoSender = new DiscoSender();
        sender = new Thread(discoSender, "discoSenderThread");
        sender.start();

        ReceiverThread rx = new ReceiverThread(this);
        receiver = new Thread(rx, "discoReceiverThread");
        while (!rx.isConnected()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        discoSender.onConnected();
        try {
            sender.interrupt();
        }catch (Exception ex){}


    }

    public boolean handleMessage(BaseMessage msg){
        if (msg instanceof ExecMsg){
            ExecMsg exec = (ExecMsg) msg;
            //reflection magic
            Activity topMostActivity = ActivityWatcher.getTopMostActivity();
            if (topMostActivity!=null){
                Method[] methods = topMostActivity.getClass().getMethods();
                for (int i=0; i < methods.length; i++){
                    if (methods[i].getName().equals(exec.)
                }
            }
        }
    }


}
