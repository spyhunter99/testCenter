package com.alexoree.testcenter.messages;

import java.io.Serializable;

/**
 * Created by alex on 8/31/16.
 */
public class ServerHelloMsg  extends BaseMessage implements Serializable {
    public static byte CODE = 0x01;
}
