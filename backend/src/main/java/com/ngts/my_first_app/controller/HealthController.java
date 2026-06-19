package com.ngts.my_first_app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HealthController {

    private final double startTime = System.currentTimeMillis();

    @GetMapping({"/","/health","/healthz"})
    public Map<String, Object> healthCheck() {
        log.info("[GET] Health check endpoint Called.");
        double upTime = (System.currentTimeMillis() - startTime) / 1000.0;

        Map<String, Object> response = new HashMap<>();

        response.put("status", "healthy");
        response.put("uptime", upTime + "s");
        return response;
    }
}
