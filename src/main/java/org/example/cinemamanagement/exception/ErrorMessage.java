package org.example.cinemamanagement.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.cinemamanagement.common.ErrorKey;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Data
public class ErrorMessage {
    private Timestamp timestamp;
    @JsonProperty("error_key")
    private Enum<ErrorKey> statusCode;
    private String message;
    private Object data;
    private Boolean success;
}
