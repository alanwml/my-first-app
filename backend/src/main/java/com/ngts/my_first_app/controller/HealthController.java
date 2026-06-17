package com.ngts.my_first_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    private final long startTime = System.currentTimeMillis();

    @GetMapping({"/","/health"})
    public Map<String, Object> healthCheck() {
        long upTime = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<>();

        response.put("status", "healthy");
        response.put("uptime", upTime - startTime + "ms");

        return response;
    }
}
