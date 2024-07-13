package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformDTO {
    private UUID id;
    @JsonProperty("film_id")
    private UUID filmId;
    @JsonProperty("translate_type")
    private String translateType;
    @JsonProperty("view_type")
    private String viewType;

    @JsonProperty("cinema_room_id")
    private UUID cinemaRoomId;
    @JsonProperty("start_time")
    private Timestamp startTime;
    @JsonProperty("end_time")
    private Timestamp endTime;

    private Double price;
}
