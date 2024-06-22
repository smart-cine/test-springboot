package org.example.cinemamanagement.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddSeatPaymentRequest {
    private UUID payment_id;
    private UUID pickSeat_id;
}
