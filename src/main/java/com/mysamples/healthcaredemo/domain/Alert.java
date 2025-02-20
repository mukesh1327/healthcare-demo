package com.mysamples.healthcaredemo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Alert {
    private String patientId;
    private String alertType;
    private String message;
    private long timestamp;
}
