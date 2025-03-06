package com.mysamples.healthcaredemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import com.mysamples.healthcaredemo.domain.Alert;
import com.mysamples.healthcaredemo.domain.HealthData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertService {

    private final ProducerTemplate producerTemplate;  // Ensure this is used
    private final List<Alert> generatedAlerts = new ArrayList<>();

    public void addAlert(Alert alert) {
        generatedAlerts.add(alert);
        log.info("Stored Alert: {}", alert);
    }

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

        // Store alert in memory for API response
        generatedAlerts.add(alert);
        log.info("Generated Alert: {}", alert);

        // Send alert to Kafka
        producerTemplate.sendBody("direct:sendAlert", alert);
    }

    public List<Alert> getAllGeneratedAlerts() {
        return generatedAlerts;
    }
}
