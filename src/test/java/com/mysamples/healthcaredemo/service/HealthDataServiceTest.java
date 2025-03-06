package com.mysamples.healthcaredemo.service;

import com.mysamples.healthcaredemo.domain.HealthData;
import com.mysamples.healthcaredemo.repository.HealthDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class HealthDataServiceTest {

    @InjectMocks
    private HealthDataService healthDataService;

    @Mock
    private HealthDataRepository healthDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddHealthData() {
        HealthData data = new HealthData();
        data.setPatientId("1");

        healthDataService.addHealthData(data);

        verify(healthDataRepository, times(1)).save(data);
    }
}
