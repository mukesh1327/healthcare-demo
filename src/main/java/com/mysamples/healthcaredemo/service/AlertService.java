package com.mysamples.healthcaredemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import com.mysamples.healthcaredemo.domain.Alert;
import com.mysamples.healthcaredemo.domain.HealthData;
import com.mysamples.healthcaredemo.exception.AlertProcessingException;

import jakarta.annotation.PreDestroy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertService {

    private final ProducerTemplate producerTemplate;  
    private final CamelContext camelContext;
    private final List<Alert> generatedAlerts = new ArrayList<>();

    public void addAlert(Alert alert) {
        try {
            generatedAlerts.add(alert);
            log.info("Stored Alert: {}", alert);
        } catch (Exception e) {
            log.error("Error while adding alert: {}", e.getMessage(), e);
            throw new AlertProcessingException("Failed to store alert: " + alert, e);
        }
    }

    @PreDestroy
    public void shutdownKafkaConsumers() {
        try {
            log.info("Shutting down Kafka consumers...");

            if (camelContext != null) {
                log.info("Stopping Camel context to close Kafka consumers...");
                camelContext.stop();
            }

            log.info("Kafka consumers shut down successfully.");
        } catch (Exception e) {
            log.error("Error shutting down Kafka consumers: {}", e.getMessage(), e);
        }
    }

    public void checkThresholds(HealthData data) {
        try {
            log.info("Checking health data for patient {}: {}", data.getPatientId(), data);

            if (data.getHeartRate() < 50 || data.getHeartRate() > 120) {
                sendAlert(data, "HEART_RATE_ALERT", "Abnormal heart rate detected: " + data.getHeartRate());
            }

            if (data.getOxygenLevel() < 90) {
                sendAlert(data, "OXYGEN_LEVEL_ALERT", "Low oxygen level detected: " + data.getOxygenLevel());
            }

            if (data.getTemperature() < 35.0 || data.getTemperature() > 38.0) {
                sendAlert(data, "TEMPERATURE_ALERT", "Abnormal body temperature: " + data.getTemperature());
            }

        } catch (Exception e) {
            log.error("Error while processing health data for patient {}: {}", data.getPatientId(), e.getMessage(), e);
            throw new AlertProcessingException("Error checking thresholds for patient: " + data.getPatientId(), e);
        }
    }

    private void sendAlert(HealthData data, String type, String message) {
        Alert alert = new Alert();
        alert.setPatientId(data.getPatientId());
        alert.setAlertType(type);
        alert.setMessage(message);
        alert.setTimestamp(Instant.now().toEpochMilli());

        try {
            generatedAlerts.add(alert);
            log.info("Generated Alert: {}", alert);
            producerTemplate.sendBody("direct:sendAlert", alert);
        } catch (Exception e) {
            log.error("Error while sending alert for patient {}: {}", data.getPatientId(), e.getMessage(), e);
            throw new AlertProcessingException("Failed to send alert for patient: " + data.getPatientId(), e);
        }
    }

    public List<Alert> getAllGeneratedAlerts() {
        return generatedAlerts;
    }
}
