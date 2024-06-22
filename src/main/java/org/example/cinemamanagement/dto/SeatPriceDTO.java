package org.example.cinemamanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SeatPriceDTO {

    private UUID id;

    private int x;

    private int y;

    private Long price;
}
