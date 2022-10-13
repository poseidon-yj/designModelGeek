package com.poseidon.design.C40;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-06
 */
public class Demo {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        StatViewer statViewer = new ConsoleViewer();
        Aggregator aggregator = new Aggregator();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage,statViewer,aggregator);
        consoleReporter.startRepeatedReporter(60, 60);

        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 1665036180));
        collector.recordRequest(new RequestInfo("register", 223, 1665036180));
        collector.recordRequest(new RequestInfo("register", 323, 1665036180));
        collector.recordRequest(new RequestInfo("login", 23, 1665036180));
        collector.recordRequest(new RequestInfo("login", 1223, 1665036180));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
