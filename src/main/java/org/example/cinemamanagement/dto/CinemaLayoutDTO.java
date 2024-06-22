package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaLayoutDTO {
    private UUID id;

    @JsonProperty("x_index")
    private Integer xIndex;

    @JsonProperty("y_index")
    private Integer yIndex;
}
