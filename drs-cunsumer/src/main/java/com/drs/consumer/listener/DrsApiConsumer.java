package com.drs.consumer.listener;

import com.drs.consumer.service.DrsApiRequestService;
import com.drs.model.common.Callback;
import com.drs.model.dto.DrsApiDto;
import com.drs.model.dto.DrsBaseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class DrsApiConsumer {

    private final DrsApiRequestService requestService;

    @KafkaListener(groupId = "${kafka.consumer.group.api}", topics = "${kafka.topics.api}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, DrsApiDto> data, Acknowledgment ack, Consumer<String, DrsBaseDto> consumer) throws MalformedURLException {
        Mono<Object> responseMono = requestService.sendRequest(data.value().getMethod(), data.value().getUri(), Optional.ofNullable(data.value().getHeaders()), data.value().getData());

        responseMono.subscribe(response -> {
            Callback callback = data.value().getCallback();

            try {
                requestService.sendCallback(callback.getMethod(), callback.getUri(), Optional.ofNullable(""), response);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    // TODO : Retrier
}
