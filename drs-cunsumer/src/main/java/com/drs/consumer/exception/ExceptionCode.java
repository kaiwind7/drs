package com.drs.consumer.exception;

import com.qcp.common.api.response.exception.ExceptionContent;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public enum ExceptionCode implements ExceptionContent {
    //COMMON
    RUNTIME_EXCEPTION("BACKEND-C0001", "runtime exception", 400),
    INVALID_REQUEST_PARAMETER("BACKEND-C0002", "파라미터 입력이 잘못되었습니다. - {0}", 400),
    INTERNAL_SERVER_ERROR("BACKEND-C0003", "시스템에러, 관리자에게 문의바랍니다.", 500),
    METHOD_ARGUMENT_TYPE_MISMATCH("BACKEND-C0004", "파라미터 타입 오류입니다.", 400),
    METHOD_NOT_ALLOWED("BACKEND-C0005", "허용하지 않는 HTTP 메서드입니다.", 405),
    METHOD_ARGUMENT_NOT_READABLE("BACKEND-C0006", "파라미터 유효성 검사 오류입니다.", 400);

    private final String code;
    private final String detail;
    private final int status;

    private static final Map<String, ExceptionCode> codeMap = Arrays.stream(ExceptionCode.values()).collect(Collectors.toMap(ExceptionCode::getCode, exceptionCode -> exceptionCode));

    ExceptionCode(String code, String detail, int status) {
        this.code = code;
        this.detail = detail;
        this.status = status;
    }

    @Override
    public String getTitle() {
        return this.name();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    public Integer getStatus() {
        return status;
    }

    public String toString() {
        return name() + "-" + code + ":" + detail;
    }

    public static Optional<ExceptionCode> lookupByCode(String code) {
        return Optional.ofNullable(codeMap.get(code));
    }

}
