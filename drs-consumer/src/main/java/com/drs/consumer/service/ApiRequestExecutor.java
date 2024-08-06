package com.drs.consumer.service;

import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;
import java.util.Optional;

public interface ApiRequestExecutor {
    HttpMethod getHttpMethod();
    Mono<String> sendRequest(String url, Optional<Object> headers, Object data) throws URISyntaxException;
}
