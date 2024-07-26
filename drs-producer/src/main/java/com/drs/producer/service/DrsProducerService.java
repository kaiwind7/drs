package com.drs.producer.service;

import com.drs.model.dto.DrsBaseDto;
import com.drs.producer.producer.DrsProducerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DrsProducerService {

//    @Value("${kafka.topic.api}")
//    private List<String>  apiTopic;

    private final DrsProducerFactory producerFactory;

    public void sendMessage(DrsBaseDto payload) {
//        producerFactory.getProducer(payload.getType()).sendMessage(apiTopic.get(0), payload);
        producerFactory.getProducer(payload.getType()).sendMessage("temp-api-topic1", payload);
    }
}
