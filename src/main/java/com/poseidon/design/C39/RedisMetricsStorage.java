package com.poseidon.design.C39;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-06
 */
public class RedisMetricsStorage implements IMetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
