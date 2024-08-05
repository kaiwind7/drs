package com.drs.consumer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class DrsApiRequestService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public Mono<Object> sendRequest(String method, String url, Optional<Object> headers, Object data) throws MalformedURLException {
        URL u = new URL(url);

        MultiValueMap<String, String> paramMultiMap = new LinkedMultiValueMap<>();
        paramMultiMap.setAll(objectMapper.convertValue(data, new TypeReference<>() {}));

        MultiValueMap<String, String> headerMultiMap = new LinkedMultiValueMap<>();
        headers.ifPresent(h -> headerMultiMap.setAll(objectMapper.convertValue(h, new TypeReference<>() {})));

        return webClient.mutate().uriBuilderFactory(new DefaultUriBuilderFactory(u.getProtocol() + "://" + u.getAuthority()))
                .build()
                .method(HttpMethod.valueOf(method))
                .uri(uriBuilder -> uriBuilder
                        .path(u.getPath())
                        .queryParams(paramMultiMap)
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headerMultiMap))
                .retrieve().bodyToMono(Object.class);
    }

    public void sendCallback(String method, String url, Optional<Object> headers, Object data) throws MalformedURLException {
        URL u = new URL(url);

        webClient.mutate().uriBuilderFactory(new DefaultUriBuilderFactory(u.getProtocol() + "://" + u.getAuthority()))
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json"))
                .build()
                .method(HttpMethod.valueOf(method))
                .uri(uriBuilder -> uriBuilder
                        .path(u.getPath())
                        .build())
                .bodyValue(data)
                .retrieve()
                .bodyToMono(Object.class).subscribe();

    }
}
