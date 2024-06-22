package org.example.cinemamanagement.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Data
public class ErrorMessage {
    private Timestamp timestamp;
    @JsonProperty("status_code")
    private int statusCode;
    private String message;
    private Object data;
}
