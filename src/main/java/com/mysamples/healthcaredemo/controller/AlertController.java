package com.mysamples.healthcaredemo.controller;

import com.mysamples.healthcaredemo.domain.Alert;
import com.mysamples.healthcaredemo.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping("/api/v1/alerts")
    public List<Alert> getAllAlerts() {
        return alertService.getAllGeneratedAlerts();
    }
}
