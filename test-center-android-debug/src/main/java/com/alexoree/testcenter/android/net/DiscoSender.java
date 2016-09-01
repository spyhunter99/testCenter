package com.alexoree.testcenter.android.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by alex on 8/31/16.
 */
public class DiscoSender implements Runnable {


    private boolean connected = false;


    @Override
    public void run() {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(4000);
            byte[] msg = "HELLO-TEST_CENTER".getBytes();
            DatagramPacket packet = new DatagramPacket(msg, msg.length);
            packet.setAddress(InetAddress.getByName("255.255.255.255"));
            socket.setBroadcast(true);
            while (!connected) {
                try {
                    socket.send(packet);
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void onConnected() {
        connected = false;
    }
}
