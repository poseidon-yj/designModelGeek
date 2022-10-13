package com.poseidon.design.C40;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class EmailReporterTest {

    @Test
    public void startDailyReport() {
    }

    @Test
    public void trimTimeFieldsToZeroOfNextDay() throws ParseException {
        MetricsStorage metricsStorage = new RedisMetricsStorage();
        StatViewer statViewer = new EmailViewer();
        Aggregator aggregator = new Aggregator();
        EmailReporter emailReporter = new EmailReporter(metricsStorage, aggregator,statViewer);
        String t = "Thu Oct 13 20:29:30 CST 2022";
        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        Date date = emailReporter.trimTimeFieldsToZeroOfNextDay(df.parse(t));

        String res = "Thu Oct 14 00:00:00 CST 2022";
        assertEquals(df.parse(res), date);
    }
}