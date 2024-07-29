package com.drs.model.common;

import com.drs.model.enums.RetryTypes;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Retry {
    private int period;
    private RetryTypes unit;
    private int limit;
}
