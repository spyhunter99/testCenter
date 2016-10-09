package com.alexoree.testcenter.parser;

import com.alexoree.testcenter.model.Task;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 8/31/16.
 */
public class TaskParser {

    public List<Task> parse(BufferedReader br) throws Exception {
        String line;
        List<Task> r = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            // Deal with the line
            if (line.trim().startsWith("#")) {
                //ignore it, comment
            } else {
                String working = line.trim();
                if (line.startsWith("wait")){
                    try {
                        Task t = new Task();

                        t.method = "serverWait";
                        t.arguments = new String[1];
                        t.arguments[0] = line.substring(line.indexOf(" ")).trim();

                        r.add(t);
                    }catch (Exception ex){
                        throw new Exception("method 'wait' requires a period of time",ex);
                    }
                }
                if (line.startsWith("log")){
                    try {
                        Task t = new Task();

                        t.method = "log";
                        t.arguments = new String[1];
                        t.arguments[0] = line.substring(line.indexOf(" ")).trim();

                        r.add(t);
                    }catch (Exception ex){
                        throw new Exception("method 'log' requires a some text",ex);
                    }
                }
                if (line.startsWith("toast")){
                    try {
                        Task t = new Task();
                        t.method = "toast";
                        t.arguments = new String[1];
                        t.arguments[0] = line.substring(line.indexOf(" ")).trim();
                        r.add(t);
                    }catch (Exception ex){
                        throw new Exception("method 'toast' requires a some text",ex);
                    }
                }
                if (line.startsWith("exec")){
                    try {
                        Task t = new Task();

                        t.method = "exec";
                        t.arguments = new String[1];
                        t.arguments[0] = line.substring(line.indexOf(" ")).trim();
                        String[] remains = parseDevices(t.arguments[0]);
                        if (remains.length==2)
                            t.arguments[0]

                        r.add(t);
                    }catch (Exception ex){
                        throw new Exception("method 'toast' requires a some text",ex);
                    }
                }


            }

        }

        return r;

    }
}
