package com.drs.consumer.exception;

import com.qcp.common.api.exception.DefaultExceptionHandler;
import com.qcp.common.api.exception.HttpErrorResponse;
import com.qcp.common.api.exception.StatusError;
import com.qcp.common.api.exception.converter.ExceptionCodeConverter;
import com.qcp.common.api.exception.strategy.StatusDetermineStrategy;
import com.qcp.common.api.response.exception.BusinessLogicException;
import com.qcp.common.api.response.exception.ErrorResponse;
import com.qcp.common.api.response.exception.ExceptionContent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultExceptionHandler {
    private final Environment environment;
    private final StatusDetermineStrategy statusDetermineStrategy;
    private final ExceptionCodeConverter exceptionCodeConverter;

    public GlobalExceptionHandler(StatusDetermineStrategy statusDetermineStrategy, Environment environment, ExceptionCodeConverter exceptionCodeConverter) {
        super(environment, statusDetermineStrategy);
        this.statusDetermineStrategy = statusDetermineStrategy;
        this.exceptionCodeConverter = exceptionCodeConverter;
        this.environment = environment;
    }


    @ExceptionHandler(BusinessLogicException.class)
    @Override
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessLogicException exception) {
        logByLevel(exception, Level.INFO);

        StatusError[] errors = exceptionCodeConverter.toError(exception.getExceptionContent()
                .stream()
                .map(ExceptionContent::getCode)
                .toArray(String[]::new));
        ErrorResponse errorResponse = exceptionCodeConverter.toErrorResponse(errors);

        Integer[] statuses = Arrays.stream(errors)
                .map(error -> error.getStatus())
                .toArray(Integer[]::new);

        return ResponseEntity.status(statusDetermineStrategy.determine(statuses))
                .body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    @Override
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
        logByLevel(exception, Level.INFO);

        ExceptionCode exceptionCode = ExceptionCode.RUNTIME_EXCEPTION;
        ErrorResponse errorResponse = new HttpErrorResponse(exceptionCode.getCode(), exceptionCode.name(), BAD_REQUEST.value());

        return ResponseEntity.status(BAD_REQUEST.value())
                .body(errorResponse);
    }

    @ExceptionHandler(BindException.class)
    @Override
    public ResponseEntity<ErrorResponse> handleBindException(BindException exception) {
        logByLevel(exception, Level.INFO);

        ExceptionCode exceptionCode = ExceptionCode.INVALID_REQUEST_PARAMETER;
        List<String> invalidParameterNames = exception.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new HttpErrorResponse(exceptionCode.getCode(), exceptionCode.getTitle(), BAD_REQUEST.value(), invalidParameterNames);

        return ResponseEntity.status(BAD_REQUEST.value())
                .body(errorResponse);
    }

    @ExceptionHandler({MethodNotAllowedException.class, HttpRequestMethodNotSupportedException.class})
    @Override
    public ResponseEntity<ErrorResponse> handleMethodNotAllowedException(Exception exception) {
        logByLevel(exception, Level.INFO);

        ExceptionCode exceptionCode = ExceptionCode.METHOD_NOT_ALLOWED;
        ErrorResponse errorResponse = new HttpErrorResponse(exceptionCode.getCode(), exceptionCode.name(), exceptionCode.getStatus());

        return ResponseEntity.status(exceptionCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @Override
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        logByLevel(exception, Level.INFO);

        ExceptionCode exceptionCode = ExceptionCode.METHOD_ARGUMENT_TYPE_MISMATCH;
        ErrorResponse errorResponse = new HttpErrorResponse(exceptionCode.getCode(), exceptionCode.name(), exceptionCode.getStatus());

        return ResponseEntity.status(exceptionCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @Override
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        logByLevel(exception, Level.INFO);

        ExceptionCode exceptionCode = ExceptionCode.METHOD_ARGUMENT_NOT_READABLE;
        ErrorResponse errorResponse = new HttpErrorResponse(exceptionCode.getCode(), exceptionCode.getTitle(), exceptionCode.getStatus(), exceptionCode.getDetail());

        return ResponseEntity.status(exceptionCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @Override
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        logByLevel(exception, Level.ERROR);

        ExceptionCode exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new HttpErrorResponse(exceptionCode.getCode(), exceptionCode.name(), HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    private void logByLevel(Throwable exception, Level logLevel) {
        switch (logLevel) {
            case TRACE -> log.trace("{} - {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
            case DEBUG -> log.debug("{} - {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
            case INFO -> {
                if (environment.matchesProfiles("dev", "local")) {
                    log.info("{} - {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
                } else {
                    log.info("{} - {}", exception.getClass().getSimpleName(), exception.getMessage());
                }
            }
            case WARN -> log.warn("{} - {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
            case ERROR -> log.error("{} - {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        }
    }
}
