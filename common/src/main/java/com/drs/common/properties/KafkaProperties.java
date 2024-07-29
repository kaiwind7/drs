package com.drs.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka")
public record KafkaProperties(
        Server server,
        Topics topics
) {
    public record Server(
        String bootstrapServer
    ) {}

    public record Topics(
           String api,
           String db2db,
           String log
    ) {}
}
