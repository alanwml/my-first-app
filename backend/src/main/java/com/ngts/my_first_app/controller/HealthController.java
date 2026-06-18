package com.ngts.my_first_app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HealthController {

    private final long startTime = System.currentTimeMillis();

    @GetMapping({"/","/health","/healthz"})
    public Map<String, Object> healthCheck() {
        log.info("[GET] Health check endpoint Called.");
//        log.debug("NGTS BANZAI!");
        long upTime = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<>();

        response.put("status", "healthy");
        response.put("uptime", upTime - startTime + "ms");
        return response;
    }
}
