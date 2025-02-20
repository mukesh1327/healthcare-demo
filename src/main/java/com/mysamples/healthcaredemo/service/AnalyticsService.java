package com.mysamples.healthcaredemo.service;

import org.springframework.stereotype.Service;

import com.mysamples.healthcaredemo.domain.HealthData;

@Service
public class AnalyticsService {

    public void processData(HealthData data) {
        // Simulate sending data to an analytics platform
        System.out.println("Processing data for analytics: " + data);
    }
}