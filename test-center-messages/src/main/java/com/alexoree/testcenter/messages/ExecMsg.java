package com.alexoree.testcenter.messages;

import java.io.Serializable;

/**
 * Created by alex on 8/31/16.
 */
public class ExecMsg  extends BaseMessage implements Serializable {
    public static byte CODE = 0x02;

    public String method;
    //TODO methods for method name, playloads etc
}
