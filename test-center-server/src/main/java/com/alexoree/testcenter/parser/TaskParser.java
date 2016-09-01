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


            }

        }

        return r;

    }
}
