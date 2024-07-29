package com.drs.producer.service;

import com.drs.common.properties.KafkaProperties;
import com.drs.model.dto.DrsBaseDto;
import com.drs.producer.producer.DrsProducerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DrsProducerService {
    private final KafkaProperties kafkaConfig;
    private final DrsProducerFactory producerFactory;

    public void sendMessage(DrsBaseDto payload) {
        producerFactory.getProducer(payload.getType())
                .sendMessage(kafkaConfig.topics().api(), payload);
    }
}
