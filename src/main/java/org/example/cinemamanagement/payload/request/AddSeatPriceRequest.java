package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class AddSeatPriceRequest {
    @JsonProperty("perform_id")
    private UUID performId;

    private Integer x;

    private Integer y;

    private Long price;
}
