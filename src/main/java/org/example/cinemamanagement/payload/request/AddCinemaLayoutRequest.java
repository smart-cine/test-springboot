package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddCinemaLayoutRequest {
    @JsonProperty("x_index")
    private Integer xIndex;
    @JsonProperty("y_index")
    private Integer yIndex;
}
