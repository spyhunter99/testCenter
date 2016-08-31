package com.alexoree.testcenter.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Receives heartbeats for discovery purposes
 * Created by alex on 8/31/16.
 */

public class BroadcastReceiver implements Runnable{

    Callback callback;
    boolean running=false;
    Thread receiver;
    DatagramSocket socket;
    int listenPort=4000;

    public BroadcastReceiver(Callback cb){
        this.callback=cb;
    }
    public BroadcastReceiver(Callback cb, int listenPort){
        this.callback=cb;
        this.listenPort=listenPort;
    }

    public void start() throws Exception {
        socket = new DatagramSocket(new InetSocketAddress("255.255.255.255", listenPort));
        receiver = new Thread(this);
        running=true;
        receiver.start();

    }
    public void stop(){
        receiver.interrupt();
        socket.close();

    }
    public boolean isRunning(){
        return running;
    }

    @Override
    public void run() {
        while (running){
            byte[] buffer = new byte[65000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                if (packet.getLength() > 0){
                    InetAddress sender = packet.getAddress();
                    //add sender to the list of known devices

                    //parse message

                    //notify that someone's online and ready

                    callback.onNodeOnline(sender);
                }
            } catch (IOException e) {

            }
        }
    }

    public interface Callback{
        public void onNodeOnline(InetAddress address);
    }
}
