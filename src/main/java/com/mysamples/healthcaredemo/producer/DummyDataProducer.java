package com.mysamples.healthcaredemo.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysamples.healthcaredemo.config.KafkaConfig;
import com.mysamples.healthcaredemo.domain.HealthData;

import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DummyDataProducer {

    private final ProducerTemplate producerTemplate;
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();  // Initialize ObjectMapper

    public HealthData generateDummyData() {
        HealthData data = new HealthData();
        data.setPatientId("P" + (1000 + random.nextInt(9000)));
        data.setHeartRate(random.nextDouble() * 100 + 40); // 40 - 140 bpm
        data.setOxygenLevel(random.nextDouble() * 20 + 80); // 80 - 100%
        data.setTemperature(random.nextDouble() * 5 + 35); // 35 - 40°C
        data.setTimestamp(Instant.now().toEpochMilli());
        return data;
    }

    public void sendDummyData() {
        HealthData data = generateDummyData();
        try {
            // Serialize HealthData to JSON
            String jsonData = objectMapper.writeValueAsString(data);
            producerTemplate.sendBody("kafka:" + KafkaConfig.HEALTH_DATA_TOPIC + "?brokers=localhost:9092", jsonData);
            System.out.println("Produced message: " + jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
