package org.example.cinemamanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FilmPriceDTO {
    private UUID id;

    private String type;

    private Long price;
}
