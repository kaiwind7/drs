package com.drs.loadtest.controller;

import com.drs.loadtest.model.RequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestContorller {
    @GetMapping("/health/check")
    public ResponseEntity test() {
        return ResponseEntity.ok("성공");
    }

    @PostMapping("/receive")
    public ResponseEntity receive(@RequestBody RequestData requestData) {
        log.info(requestData.toString());
        return  ResponseEntity.ok("receive 성공");
    }
}
