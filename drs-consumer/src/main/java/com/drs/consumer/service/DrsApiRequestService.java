package com.drs.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class DrsApiRequestService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final DrsApiRequestFactory drsApiRequestFactory;

    public Mono<String> sendRequest(String method, String url, Optional<Object> headers, Object data) throws URISyntaxException {
        return drsApiRequestFactory.getExecutor(HttpMethod.valueOf(method)).sendRequest(url, headers, data);
    }

    public void sendCallback(String method, String url, Optional<Object> headers, Object data) throws URISyntaxException {
        URI uri = new URI(url);

        webClient.mutate().uriBuilderFactory(new DefaultUriBuilderFactory(uri.getScheme() + "://" + uri.getAuthority()))
                .defaultHeaders(httpHeaders -> httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .build()
                .method(HttpMethod.valueOf(method))
                .uri(uriBuilder -> uriBuilder
                        .path(uri.getPath())
                        .build())
                .bodyValue(data)
                .retrieve()
                .bodyToMono(Object.class).subscribe();

    }
}
