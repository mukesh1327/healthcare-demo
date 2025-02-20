package com.mysamples.healthcaredemo.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import com.mysamples.healthcaredemo.domain.Alert;
import com.mysamples.healthcaredemo.domain.HealthData;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthDataService {

    @Getter
    private final List<HealthData> consumedHealthData = new ArrayList<>();

    @Getter
    private final List<Alert> generatedAlerts = new ArrayList<>();

    public void addHealthData(HealthData data) {
        consumedHealthData.add(data);
    }

    public void addAlert(Alert alert) {
        generatedAlerts.add(alert);
    }
}
