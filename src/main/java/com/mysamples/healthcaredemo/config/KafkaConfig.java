package com.mysamples.healthcaredemo.config;

import lombok.Getter;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaConfig {

    public static final String HEALTH_DATA_TOPIC = "health-data";
    public static final String ALERTS_TOPIC = "health-alerts";

    // Kafka Headers
    public static final String PATIENT_ID_HEADER = KafkaConstants.KEY;
    public static final String TIMESTAMP_HEADER = KafkaConstants.TIMESTAMP;

    @Value("${KAFKA_URL:localhost:9092}")
    private String kafkaBroker;
}
