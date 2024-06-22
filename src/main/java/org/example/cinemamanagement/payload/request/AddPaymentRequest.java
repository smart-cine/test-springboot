package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AddPaymentRequest {
    @JsonProperty("cinema_id")
    private UUID cinemaId;
    private Long amount;

    @JsonProperty("list_pick_seat")
    private List<AddOrDeletePickSeatRequest> pickSeats;
}
