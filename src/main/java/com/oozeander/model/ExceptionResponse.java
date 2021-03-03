package com.oozeander.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.List;

@JsonPropertyOrder({"timestamp", "status_code", "errors", "path"})
public class ExceptionResponse {
    private final List<String> errors;
    private final LocalDateTime timestamp;
    @JsonProperty("status_code")
    private final Integer statusCode;
    private final String path;

    public ExceptionResponse(List<String> errors, LocalDateTime timestamp, Integer statusCode, String path) {
        this.errors = errors;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.path = path;
    }

    public List<String> getErrors() {
        return errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "errors=" + errors +
                ", timestamp=" + timestamp +
                ", statusCode=" + statusCode +
                ", path='" + path + '\'' +
                '}';
    }
}