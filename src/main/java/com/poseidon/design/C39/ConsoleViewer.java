package com.poseidon.design.C39;

import com.google.gson.Gson;

import java.util.Map;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-09
 */
public class ConsoleViewer implements StatViewer{
    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
