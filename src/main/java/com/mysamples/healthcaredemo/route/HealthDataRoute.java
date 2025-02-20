package com.mysamples.healthcaredemo.route;


import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.mysamples.healthcaredemo.config.KafkaConfig;
import com.mysamples.healthcaredemo.domain.HealthData;
import com.mysamples.healthcaredemo.service.AlertService;
import com.mysamples.healthcaredemo.service.AnalyticsService;
import com.mysamples.healthcaredemo.service.HealthDataService;

@Component
@RequiredArgsConstructor
public class HealthDataRoute extends RouteBuilder {

    private final AlertService alertService;
    private final AnalyticsService analyticsService;
    private final HealthDataService healthDataService;

    @Override
    public void configure() {

        // Consume IoT health data from Kafka
        from("kafka:" + KafkaConfig.HEALTH_DATA_TOPIC + "?brokers=localhost:9092&groupId=healthcare-group")
            .routeId("HealthDataRoute")
            .unmarshal().json(HealthData.class)
            .log("Received health data: ${body}")
            .process(exchange -> {
                HealthData data = exchange.getIn().getBody(HealthData.class);

                // Store consumed data for API response
                healthDataService.addHealthData(data);

                // Check thresholds and generate alerts
                alertService.checkThresholds(data);

                // Send data to analytics system
                analyticsService.processData(data);
            });

        // Produce alerts to Kafka
        from("direct:sendAlert")
            .routeId("SendAlertRoute")
            .marshal().json()
            .to("kafka:" + KafkaConfig.ALERTS_TOPIC + "?brokers=localhost:9092")
            .log("Sent alert: ${body}");
    }
}

