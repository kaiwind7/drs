package com.drs.consumer.exception;

import com.qcp.common.api.exception.StatusError;
import com.qcp.common.api.exception.converter.ExceptionCodeConverter;
import com.qcp.common.api.response.exception.Error;
import com.qcp.common.api.response.exception.ErrorResponse;

import java.util.Arrays;
import java.util.List;

public class BasicExceptionCodeConverter implements ExceptionCodeConverter {
    @Override
    public StatusError[] toError(String[] codes) {
        return Arrays.stream(codes)
                .map(rawCode -> ExceptionCode.lookupByCode(rawCode).orElseThrow(() -> new InvalidCodeException("code[" + rawCode + "] is not found")))
                .map(code -> new StatusError(code.getCode(), code.getDetail(), code.getStatus()))
                .toArray(StatusError[]::new);
    }

    @Override
    public ErrorResponse toErrorResponse(Error[] errors) {
        ErrorResponse errorResponse = new ErrorResponse(List.of(errors));
        return errorResponse;
    }
}
