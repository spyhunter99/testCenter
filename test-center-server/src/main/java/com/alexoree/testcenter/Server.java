package com.alexoree.testcenter;

import com.alexoree.testcenter.config.Configuration;
import com.alexoree.testcenter.errors.DiscoveryTimeOutException;
import com.alexoree.testcenter.net.BroadcastReceiver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Set;

public class Server implements BroadcastReceiver.Callback {

    Set<InetAddress> endPoints;
    BroadcastReceiver br;


    public static void main(String[] args) throws Exception{
        System.out.println("hello world!");

        Server server = new Server();

        server.loadConfiguration("config.properties");

        server.init();

        server.startDiscovery();

        server.waitForAllNodes();

        server.runTests();

        server.cleanup();

    }

    private void init() {
        //run init scripts, if any
        //adb connect?
        //push apk's etc

        //start adb logcat captures

    }

    private void cleanup() {
        //undeploy? reset

    }

    private void runTests() {


    }

    private void waitForAllNodes() throws Exception {
        long startTime = System.currentTimeMillis();
        while (Configuration.getInstance().getExpectedNodes() > endPoints.size()){
            Thread.sleep(1000);
            if (System.currentTimeMillis() - startTime > Configuration.getInstance().getDiscoveryTimeout()){
                throw new DiscoveryTimeOutException();
            }

        }
    }

    private void startDiscovery() {
        br = new BroadcastReceiver(this);
    }

    public void loadConfiguration(String s) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(s));
        Configuration.initialize(p);
    }

    @Override
    public void onNodeOnline(InetAddress address) {
        endPoints.add(address);
    }
}