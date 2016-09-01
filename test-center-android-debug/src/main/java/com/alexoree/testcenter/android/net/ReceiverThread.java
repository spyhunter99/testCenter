package com.alexoree.testcenter.android.net;

import com.alexoree.testcenter.messages.BaseMessage;
import com.alexoree.testcenter.messages.ServerHelloMsg;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by alex on 8/31/16.
 */
public class ReceiverThread implements Runnable {
    boolean connected = false;
    boolean running = false;
    DatagramSocket socket;
    int listenPort = 4000;
    TestCenterClientService callback;
    InetAddress server=null;

    public ReceiverThread(TestCenterClientService callback){
        this.callback = callback;
    }

    public void stop() {
        connected = false;
        running = false;
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public void run() {
        while (running) {
            byte[] buffer = new byte[65000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                if (packet.getLength() > 0) {
                    InetAddress sender = packet.getAddress();
                    //add sender to the list of known devices

                    //parse message

                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(packet.getData(), 0, packet.getLength()));
                    BaseMessage obj = (BaseMessage) ois.readObject();
                    ois.close();
                    if (server!=null && server == sender) {
                        if (obj instanceof ServerHelloMsg) {
                            connected = true;
                            server = sender;

                        } else
                            if (obj instanceof BaseMessage) {
                                boolean res= callback.handleMessage(obj);
                            }
                    }

                }
            } catch (Exception e) {

            }
        }

    }

}
