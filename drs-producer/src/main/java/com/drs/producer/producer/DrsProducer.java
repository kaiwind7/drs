package com.drs.producer.producer;

import com.drs.model.dto.DrsBaseDto;
import com.drs.model.enums.DrsProducerTypes;

public interface DrsProducer {
    DrsProducerTypes getType();
    void sendMessage(String topic, DrsBaseDto payload);
}
