package com.alexoree.testcenter.android;

import java.util.Map;

public interface TestCenterInterface {
    void start();
    void destroy();
    void registerCallback();

    interface Callback{
        void processMessage(Map<String,String> properties);
    }
}
