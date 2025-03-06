package com.mysamples.healthcaredemo.service;

import static org.mockito.Mockito.*;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {

    @Mock
    private ProducerTemplate producerTemplate;

    @Mock
    private CamelContext camelContext;

    @InjectMocks
    private AlertService alertService; // This will inject mocks automatically

    @BeforeEach
    void setUp() {
        alertService = new AlertService(producerTemplate, camelContext);
    }

    @Test
    void testShutdownKafkaConsumers() throws Exception {
        doNothing().when(camelContext).stop();

        alertService.shutdownKafkaConsumers();

        verify(camelContext, times(1)).stop();
    }
}
