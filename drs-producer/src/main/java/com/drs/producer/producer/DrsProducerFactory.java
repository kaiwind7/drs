package com.drs.producer.producer;

import com.drs.model.enums.DrsProducerTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DrsProducerFactory {
    private final Map<DrsProducerTypes, DrsProducer> producerMap = new HashMap<>();

    public DrsProducerFactory(List<DrsProducer> producerList) {
        producerList.forEach(producer -> {
            producerMap.put(producer.getType(), producer);
        });
    }

    public DrsProducer getProducer(DrsProducerTypes producerTypes) {
        return producerMap.get(producerTypes);
    }
}
