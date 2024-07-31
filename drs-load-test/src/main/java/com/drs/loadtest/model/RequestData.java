package com.drs.loadtest.model;

import lombok.Builder;

@Builder
public record RequestData (
        String data
) {}
