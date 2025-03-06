package com.mysamples.healthcaredemo.service;

import com.mysamples.healthcaredemo.domain.HealthData;
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
        log.info("Saving Health Data to Database: {}", data);
        healthDataRepository.save(data);
    }

    public List<HealthData> getAllHealthData() {
        return healthDataRepository.findAll();
    }
}
