package com.drs.consumer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ApiPostExecutor implements ApiRequestExecutor {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public Mono<String> sendRequest(String url, Optional<Object> headers, Object data) throws URISyntaxException {
        URI uri = new URI(url);

        MultiValueMap<String, String> headerMultiMap = new LinkedMultiValueMap<>();
        headers.ifPresent(h -> headerMultiMap.setAll(objectMapper.convertValue(h, new TypeReference<>() {})));

        return webClient.mutate().uriBuilderFactory(new DefaultUriBuilderFactory(uri.getScheme() + "://" + uri.getAuthority()))
                .build()
                .method(HttpMethod.POST)
                .uri(uriBuilder -> uriBuilder
                        .path(uri.getPath())
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headerMultiMap))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve().bodyToMono(String.class);
    }
}
