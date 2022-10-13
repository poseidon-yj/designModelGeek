package com.poseidon.design.C39;

import java.util.List;
import java.util.Map;

public interface IMetricsStorage {
    public void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);


    Map<String,List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
