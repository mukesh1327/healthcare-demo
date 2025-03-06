package com.mysamples.healthcaredemo.domain;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "health_data")
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientId;
    private double heartRate;
    private double oxygenLevel;
    private double temperature;
    private long timestamp;
}
