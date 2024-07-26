package com.drs.producer.producer;

import com.drs.model.dto.DrsBaseDto;
import com.drs.model.enums.DrsProducerTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component

public class DrsApiProducer implements DrsProducer {

    private final KafkaTemplate kafkaTemplate;

    @Override
    public DrsProducerTypes getType() {
        return DrsProducerTypes.API;
    }

    @Override
    public void sendMessage(String topic, DrsBaseDto payload) {
        kafkaTemplate.send(topic, payload);
    }

    public void send(String topic, DrsBaseDto payload) {
        CompletableFuture<SendResult> future = kafkaTemplate.send(topic, payload);

        future.whenComplete((((sendResult, throwable) -> {
            if(throwable == null) {
                // TODO : if needs post process after produce message.
            }
        })));
    }
}
