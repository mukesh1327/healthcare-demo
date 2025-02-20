package com.mysamples.healthcaredemo.domain;

import lombok.Data;

@Data
public class HealthData {
    private String patientId;
    private double heartRate;
    private double oxygenLevel;
    private double temperature;
    private long timestamp;
}
