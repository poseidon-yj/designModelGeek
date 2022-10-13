package com.poseidon.design.C39;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-06
 * <p>
 * 1.根据给定的时间区间，从数据库中拉取数据；
 * 2.根据原始数据，计算得到统计数据；
 * 3.将统计数据显示到终端（命令行或邮件）；
 * 4.定时触发以上 3 个过程的执行。
 */

/**
 * 1.根据给定的时间区间，从数据库中拉取数据；
 * 2.根据原始数据，计算得到统计数据；
 * 3.将统计数据显示到终端（命令行或邮件）；
 * 4.定时触发以上 3 个过程的执行。
 */

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ConsoleReporter 类相当于一个上帝类，定时根据给定的时间区间，从数据库中取出数据，借助 Aggregator 类完成统计工作，并将统计结果输出到命令行。
 * 这里的策略是将1、3、4逻辑放到ConsoleReporter类中，把第2个逻辑放到Aggregator类中
 */
public class ConsoleReporter {
    private IMetricsStorage metricsStorage;
    private ScheduledExecutorService executorService;
    private StatViewer statViewer;
    private Aggregator aggregator;

    public ConsoleReporter(IMetricsStorage metricsStorage, StatViewer statViewer, Aggregator aggregator) {
        this.metricsStorage = metricsStorage;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.statViewer = statViewer;
        this.aggregator = aggregator;
    }

    public void startRepeatedReporter(long periodSeconds, long durationInSeconds) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                Map<String, RequestStat> requestStats =  aggregator.aggregate(requestInfos,durationInMillis);
                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
                statViewer.output(requestStats,startTimeInMillis,endTimeInMillis);
            }
        }, 0, periodSeconds, TimeUnit.SECONDS);
    }

}
