package com.mysamples.healthcaredemo.service;

import com.mysamples.healthcaredemo.domain.HealthData;
import com.mysamples.healthcaredemo.exception.DatabaseOperationException;
import com.mysamples.healthcaredemo.repository.HealthDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthDataService {

    private final HealthDataRepository healthDataRepository;

    public void addHealthData(HealthData data) {
        try {
            log.info("Saving Health Data to Database: {}", data);
            healthDataRepository.save(data);
            log.info("Health data successfully saved for patient: {}", data.getPatientId());
        } catch (Exception e) {
            log.error("Error while saving health data: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to save health data for patient: " + data.getPatientId(), e);
        }
    }

    public List<HealthData> getAllHealthData() {
        try {
            log.info("Fetching all health data records...");
            return healthDataRepository.findAll();
        } catch (Exception e) {
            log.error("Error while fetching health data: {}", e.getMessage(), e);
            throw new DatabaseOperationException("Failed to retrieve health data.", e);
        }
    }
}
