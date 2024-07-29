package com.drs.consumer.listener;

import com.drs.model.dto.DrsApiDto;
import com.drs.model.dto.DrsBaseDto;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class DrsApiListener {

    @KafkaListener(groupId = "${kafka.consumer.group.api}", topics = "${kafka.topics.api}")
    public void consume(ConsumerRecord<String, DrsApiDto> data, Acknowledgment ack, Consumer<String, DrsBaseDto> consumer) {
        // TODO : send consumed message to target after consume
    }

    // TODO : Retrier
}
