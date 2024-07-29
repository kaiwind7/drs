package com.drs.model.dto;

import com.drs.model.common.Callback;
import com.drs.model.common.Retry;
import com.drs.model.enums.DrsProducerTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DrsApiDto.class, name = "API")
})
@NoArgsConstructor
@Getter
@Setter
public abstract class DrsBaseDto {
    private DrsProducerTypes type;
    private Callback callback;
    private Retry retry;
}
