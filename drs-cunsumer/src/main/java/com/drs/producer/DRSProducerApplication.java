package com.drs.producer;

import com.qcp.common.api.exception.EnableDefaultExceptionHandler;
import com.qcp.common.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableSwagger
@ComponentScan("com.mass.**")
@EnableDefaultExceptionHandler
@SpringBootApplication
public class DRSProducerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication. run(DRSProducerApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DRSProducerApplication.class);
    }
}
