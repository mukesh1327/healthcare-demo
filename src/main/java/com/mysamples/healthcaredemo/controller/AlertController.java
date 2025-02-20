package com.mysamples.healthcaredemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysamples.healthcaredemo.domain.Alert;
import com.mysamples.healthcaredemo.service.HealthDataService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlertController {

    private final HealthDataService healthDataService;

    // Get all generated alerts
    @GetMapping("/api/v1/alerts")
    public List<Alert> getAllAlerts() {
        return healthDataService.getGeneratedAlerts();
    }
}
