package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AddFilmPriceRequest {
    @JsonProperty("film_id")
    private UUID filmId;

    private String type;

    private Long price;
}
