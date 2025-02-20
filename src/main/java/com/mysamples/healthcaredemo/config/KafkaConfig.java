package com.mysamples.healthcaredemo.config;

import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String HEALTH_DATA_TOPIC = "health-data";
    public static final String ALERTS_TOPIC = "health-alerts";

    // Kafka Headers
    public static final String PATIENT_ID_HEADER = KafkaConstants.KEY;
    public static final String TIMESTAMP_HEADER = KafkaConstants.TIMESTAMP;
}