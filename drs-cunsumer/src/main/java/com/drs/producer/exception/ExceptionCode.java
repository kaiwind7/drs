package com.drs.producer.exception;

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
    METHOD_ARGUMENT_NOT_READABLE("BACKEND-C0006", "파라미터 유효성 검사 오류입니다.", 400),

    // TODO 삭제예정
    //USER
    NEW_PASSWORD_SHOULD_NOT_CONTAIN_ID("BACKEND-U0001", "비밀번호에 아이디가 포함되어서는 안됩니다.", 400),

    NEW_PASSWORD_NOT_MATCHING("BACKEND-U0002", "새 비밀번호가 일치하지 않습니다.", 400),

    NEW_PASSWORD_SHOULD_NOT_SAME_AS_OLD("BACKEND-U0003", "현재 비밀번호와 같을 수 없습니다.", 400),
    ALREADY_EXISTS_USER("BACKEND-U0004", "이미 존재하는 회원 입니다.", 400),

    NOT_EXISTS_LOGIN_USER("BACKEND-U0005", "존재하지 않는 회원입니다.", 400),
    ALREADY_WITHDRAWN_USER("BACKEND-U0006", "이미 탈퇴한 회원입니다.", 400),
    INVALID_NAME_VALUE("BACKEND-U0007", "2~15자의 이름만 입력 가능합니다.", 400),
    INVALID_STATUS_UPDATE("BACKEND-U0008", "해당 회원 상태로 변경할 수 없습니다.", 400),
    INVALID_USER_LOGIN_PASSWORD("BACKEND-U0009", "비밀번호가 일치하지 않습니다.", 400),

    // Image Operation
    ALREADY_EXISTS_IMAGE_OPERATION("BACKEND-IO0001", "이미 존재하는 이미지처리 명입니다.", 400),

    // Upload Video
    ALREADY_EXISTS_VIDEO_INFO("BACKEND-UV0001", "이미 업로드된 동영상입니다.", 400),
    NOT_EXISTS_VIDEO_THUMBNAIL_INFO("BACKEND-UV0002", "동영상의 썸내일 정보가 없습니다.", 400),
    // 없는 이미지

    // Upload Image
    ALREADY_EXISTS_IMAGE_INFO("BACKEND-UI0001", "이미 업로드된 이미지입니다.", 500),
    NOT_ALLOWED_MIME("BACKEND-UI0002", "업로드가 허용되지 않는 파일입니다.", 500),

    // TOKEN
    TOKEN_CREATION_FAILED("BACKEND-TO0001", "토큰 발급의 실패하였습니다.", 400),
    DELETE_TOKEN_USER_FAILED("BACKEND-TO0002", "토큰 삭제의 실패하였습니다.", 400),
    EMPTY_TOKEN_FAILED("BACKEND-TO0003", "토큰정보가 없습니다.", 400),
    SELECT_TOKEN_USER_FAILED("BACKEND-TO0004", "토큰 조회중 실패하였습니다.", 400),
    TOKEN_CONTEXTPATH_DISCORD("BACKEND-TO0005", "토큰의 ContextPath 정보가 불일치 합니다.", 400),
    TOKEN_PLATFORM_DISCORD("BACKEND-TO0006", "토큰의 Platform 정보가 불일치 합니다.", 400),
    TOKEN_CONTEXTPATH_NOT_POSSIBLE("BACKEND-TO0007", "Path 정보의 특수문자/공백/한글이 입력이 되어 있습니다.", 400),
    SELECT_CONTEXTPATH_SERVICENAME_FAILED("BACKEND-TO0008", "ContextPath/ServiceName 조회를 실패하였습니다.", 400),
    POST_PLATFORM_INSERT_UPDATE_FAILED("BACKEND-TO0009", "Platform/ContextPath/ServiceName 조회를 실패하였습니다.", 400),
    TOKEN_EXPIRED("BACKEND-TO0010", "유효되지 않는 토큰입니다.", 400);



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
