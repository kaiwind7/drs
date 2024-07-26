package com.drs.producer.producer;

import com.drs.model.dto.DrsBaseDto;
import com.drs.model.enums.DrsProducerTypes;

public class DrsDBProducer implements DrsProducer {

    @Override
    public DrsProducerTypes getType() {
        return DrsProducerTypes.DB2DB;
    }

    @Override
    public void sendMessage(String topic, DrsBaseDto payload) {
        // TODO : need implement DB to DB with Debezium
    }
}
