package com.mysamples.healthcaredemo.exception;

public class AlertProcessingException extends RuntimeException {
    public AlertProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
