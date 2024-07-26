package com.drs.consumer.listener;

import com.drs.model.dto.DrsApiDto;
import com.drs.model.dto.DrsBaseDto;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DrsKafkaListener {

    @KafkaListener(groupId = "drsTest1", topics = "temp-api-topic1")
    public void consume(ConsumerRecord<String, DrsApiDto> data, Acknowledgment ack, Consumer<String, DrsBaseDto> consumer) {
        // TODO : send consumed message to target after consume
    }

    // TODO : Retrier
}
