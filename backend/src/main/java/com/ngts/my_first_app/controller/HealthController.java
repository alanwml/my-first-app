package com.ngts.my_first_app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "Health API Endpoints")
@RestController
public class HealthController {

    private final double startTime = System.currentTimeMillis();

    @GetMapping({"/","/health","/healthz"})
    public ResponseEntity<Map<String, Object>> healthCheck() {
        log.info("[GET] Health check endpoint Called.");
        double upTime = (System.currentTimeMillis() - startTime) / 1000.0;

        Map<String, Object> response = new HashMap<>();

        response.put("status", "healthy");
        response.put("uptime", upTime + "s");
        return ResponseEntity.ok(response);
    }
}
