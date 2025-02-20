package com.mysamples.healthcaredemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysamples.healthcaredemo.domain.HealthData;
import com.mysamples.healthcaredemo.producer.DummyDataProducer;
import com.mysamples.healthcaredemo.service.HealthDataService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HealthDataController {

    private final DummyDataProducer dummyDataProducer;
    private final HealthDataService healthDataService;

    // Generate and send dummy health data to Kafka
    @PostMapping("/api/v1/health-data/send")
    public String sendDummyHealthData() {
        dummyDataProducer.sendDummyData();
        return "Dummy health data sent to Kafka!";
    }

    // Get all consumed health data
    @GetMapping("/api/v1/health-data")
    public List<HealthData> getAllHealthData() {
        return healthDataService.getConsumedHealthData();
    }
}
