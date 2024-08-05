package com.drs.model.common;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Callback {
    private String protocol;
    private String method;
    private String uri;
    private JsonNode extParams;

    private Retry retry;
}
