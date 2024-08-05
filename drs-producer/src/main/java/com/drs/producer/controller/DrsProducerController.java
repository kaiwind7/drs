package com.drs.producer.controller;

import com.drs.model.dto.DrsBaseDto;
import com.drs.producer.service.DrsProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produce")
public class DrsProducerController {

    private final DrsProducerService producerService;

    @PostMapping
    public ResponseEntity sendMessage(@RequestBody DrsBaseDto payload) {
        producerService.sendMessage(payload);
        // TODO : Kafka 메시지 전송 후 Acknowledge 확인 응답
        return ResponseEntity.ok().build();
    }
}
