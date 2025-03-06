package com.mysamples.healthcaredemo.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysamples.healthcaredemo.config.KafkaConfig;
import com.mysamples.healthcaredemo.domain.HealthData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DummyDataProducer {

    private final KafkaConfig kafkaConfig;
    private final ProducerTemplate producerTemplate;
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HealthData generateDummyData() {
        HealthData data = new HealthData();
        data.setPatientId("P" + (1000 + random.nextInt(9000)));
        data.setHeartRate(random.nextDouble() * 100 + 40);
        data.setOxygenLevel(random.nextDouble() * 20 + 80);
        data.setTemperature(random.nextDouble() * 5 + 35);
        data.setTimestamp(Instant.now().toEpochMilli());
        return data;
    }

    public void sendDummyData() {
        HealthData data = generateDummyData();
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            producerTemplate.sendBody("kafka:" + KafkaConfig.HEALTH_DATA_TOPIC + "?brokers=" + kafkaConfig.getKafkaBroker(), jsonData);
            log.info("Produced message: {}", jsonData);
        } catch (Exception e) {
            log.error("Error sending dummy data", e);
        }
    }
}
