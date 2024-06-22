package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AddCommentRequest {
    @JsonProperty("film_id")
    private UUID destId;

    private String body;
}
