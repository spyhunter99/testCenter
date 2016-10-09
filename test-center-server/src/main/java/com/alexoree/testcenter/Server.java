package com.alexoree.testcenter;

import com.alexoree.testcenter.config.Configuration;
import com.alexoree.testcenter.errors.DiscoveryTimeOutException;
import com.alexoree.testcenter.model.Task;
import com.alexoree.testcenter.net.BroadcastReceiver;
import com.alexoree.testcenter.parser.TaskParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Server implements BroadcastReceiver.Callback {

    Set<InetAddress> endPoints;
    BroadcastReceiver br;
    List<Task> tasks;


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

    private void init() throws Exception{
        //run init scripts, if any
        Process p = Runtime.getRuntime().exec(Configuration.getInstance().getInitScript());
        int exitCode=p.waitFor();
        p.destroy();
        if (exitCode!=0)
            throw new Exception("init script failure");
        //adb connect?
        //push apk's etc

        //start adb logcat captures

        TaskParser parser = new TaskParser();
        tasks = parser.parse(new BufferedReader(new FileReader("test1.test")));

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
            System.out.println(".waiting for nodes...expecting " + Configuration.getInstance().getExpectedNodes() +  " current count is " + endPoints.size());
            if (System.currentTimeMillis() - startTime > Configuration.getInstance().getDiscoveryTimeout()){
                throw new DiscoveryTimeOutException();
            }

        }
    }

    private void startDiscovery() {
        br = new BroadcastReceiver(this, Configuration.getInstance().getListenPortDiscovery());
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