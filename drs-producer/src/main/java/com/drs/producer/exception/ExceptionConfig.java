package com.drs.producer.exception;

import com.qcp.common.api.exception.converter.ExceptionCodeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {
    @Bean
    public ExceptionCodeConverter exceptionCodeConverter() {
        return new BasicExceptionCodeConverter();
    }
}
