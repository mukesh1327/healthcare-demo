package com.mysamples.healthcaredemo.service;


import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import com.mysamples.healthcaredemo.domain.Alert;
import com.mysamples.healthcaredemo.domain.HealthData;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final ProducerTemplate producerTemplate;
    private final HealthDataService healthDataService;

    public void checkThresholds(HealthData data) {
        if (data.getHeartRate() < 50 || data.getHeartRate() > 120) {
            sendAlert(data, "HEART_RATE_ALERT", "Abnormal heart rate detected: " + data.getHeartRate());
        }

        if (data.getOxygenLevel() < 90) {
            sendAlert(data, "OXYGEN_LEVEL_ALERT", "Low oxygen level detected: " + data.getOxygenLevel());
        }

        if (data.getTemperature() < 35.0 || data.getTemperature() > 38.0) {
            sendAlert(data, "TEMPERATURE_ALERT", "Abnormal body temperature: " + data.getTemperature());
        }
    }

    private void sendAlert(HealthData data, String type, String message) {
        Alert alert = new Alert();
        alert.setPatientId(data.getPatientId());
        alert.setAlertType(type);
        alert.setMessage(message);
        alert.setTimestamp(Instant.now().toEpochMilli());

        // Store alert for API response
        healthDataService.addAlert(alert);

        // Send alert to Kafka
        producerTemplate.sendBody("direct:sendAlert", alert);
    }
}
