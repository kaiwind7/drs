package com.drs.consumer.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DrsApiRequestFactory {

    private final Map<HttpMethod, ApiRequestExecutor> executorMap = new HashMap<>();

    public DrsApiRequestFactory(List<ApiRequestExecutor> executorList) {
        executorList.forEach(e -> {
            executorMap.put(e.getHttpMethod(), e);
        });
    }

    public ApiRequestExecutor getExecutor(HttpMethod method) {
        return executorMap.get(method);
    }
}
