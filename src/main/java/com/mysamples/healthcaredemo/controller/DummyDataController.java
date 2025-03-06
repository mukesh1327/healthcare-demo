package com.mysamples.healthcaredemo.controller;

import com.mysamples.healthcaredemo.producer.DummyDataProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dummy-data")
@RequiredArgsConstructor
public class DummyDataController {

    private final DummyDataProducer dummyDataProducer;

    @PostMapping("/send")
    public String sendDummyHealthData() {
        dummyDataProducer.sendDummyData();
        return "Dummy health data sent to Kafka!";
    }
}
