package com.drs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrsApiDto extends DrsBaseDto {
    private String target;
    private String message;
    private Callback callback;
}

@Data
class Callback {
    private String url;
    private Map<String, String> extParam;
}

