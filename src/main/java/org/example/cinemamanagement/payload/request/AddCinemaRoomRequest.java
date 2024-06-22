package org.example.cinemamanagement.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCinemaRoomRequest {
    private String name;
    @JsonProperty("cinema_id")
    private UUID cinemaId;
    @JsonProperty("cinema_layout_id")
    private UUID cinemaLayoutId;
}
