package com.drs.loadtest.controller;

import com.drs.loadtest.model.RequestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> tempJsonMap = new HashMap<>();
        tempJsonMap.put("message", "receive 성공");

        return  ResponseEntity.ok(tempJsonMap);
    }
}
