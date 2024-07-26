package com.drs.model.dto;

import com.drs.model.enums.DrsProducerTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DrsApiDto.class, name = "API")
})
@NoArgsConstructor
@Getter
public abstract class DrsBaseDto {
    private DrsProducerTypes type;
}
