package com.mysamples.healthcaredemo.service;

import com.mysamples.healthcaredemo.domain.HealthData;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class AlertServiceTest {

    private final ProducerTemplate producerTemplate = Mockito.mock(ProducerTemplate.class);
    private final AlertService alertService = new AlertService(producerTemplate);

    @Test
    public void testCheckThresholds() {
        HealthData data = new HealthData();
        data.setPatientId("P5367");
        data.setHeartRate(123.54542314289287);
        data.setOxygenLevel(87.81203202532186);
        data.setTemperature(39.63438496148977);

        alertService.checkThresholds(data);

        // Verify that alerts were sent for all conditions
        Mockito.verify(producerTemplate, Mockito.times(3)).sendBody(Mockito.anyString(), any());
    }
}
