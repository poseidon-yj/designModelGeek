package com.poseidon.design.C40;

import java.util.Map;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-09
 */
public class EmailViewer implements StatViewer {
    /**private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender(...);
    }
    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    } */

    public void output(
            Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
