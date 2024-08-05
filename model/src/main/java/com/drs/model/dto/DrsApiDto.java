package com.drs.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrsApiDto extends DrsBaseDto {
    private String protocol;
    private String method;
    private String uri;
    private JsonNode headers;
    private JsonNode data;
    private boolean isOrdered;
}
