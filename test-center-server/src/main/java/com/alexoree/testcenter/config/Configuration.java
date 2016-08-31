package com.alexoree.testcenter.config;

import java.util.Properties;

/**
 * Created by alex on 8/31/16.
 */

public class Configuration {

    private static Configuration instance=null;
    public static Configuration getInstance(){
        return instance;
    }

    public static void initialize(Properties properties){
        instance = new Configuration();
        instance.setExpectedNodes(Integer.parseInt(properties.getProperty("expect.devices")));
        instance.setDiscoveryTimeout(Integer.parseInt(properties.getProperty("disco.timeout")));
        instance.setListenPortDiscovery(Integer.parseInt(properties.getProperty("disco.port")));

    }

    public int getExpectedNodes() {
        return expectedNodes;
    }

    public void setExpectedNodes(int expectedNodes) {
        this.expectedNodes = expectedNodes;
    }

    public int getListenPortDiscovery() {
        return listenPortDiscovery;
    }

    public void setListenPortDiscovery(int listenPortDiscovery) {
        this.listenPortDiscovery = listenPortDiscovery;
    }

    private int expectedNodes;
    private int listenPortDiscovery=4000;
    private int discoveryTimeout=10 * 60 * 1000;    //10 minutes

    public int getDiscoveryTimeout() {
        return discoveryTimeout;
    }

    public void setDiscoveryTimeout(int discoveryTimeout) {
        this.discoveryTimeout = discoveryTimeout;
    }
}
