package com.poseidon.design.C39;


import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-06
 */
public class MetricsCollector {
    private IMetricsStorage metricsStorage;

    public MetricsCollector(IMetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    //用一个函数代替了最小原型中的两个函数

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}

