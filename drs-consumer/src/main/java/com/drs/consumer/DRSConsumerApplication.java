package com.drs.consumer;

import com.qcp.common.api.exception.EnableDefaultExceptionHandler;
import com.qcp.common.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableSwagger
@EnableDefaultExceptionHandler
@SpringBootApplication
public class DRSConsumerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication. run(DRSConsumerApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DRSConsumerApplication.class);
    }
}
