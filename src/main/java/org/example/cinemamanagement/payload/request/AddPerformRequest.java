package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPerformRequest {
    @JsonProperty("film_id")
    private UUID filmId;

    @JsonProperty("view_type")
    private String viewType;

    @JsonProperty("translate_type")
    private String translateType;

    @JsonProperty("cinema_room_id")
    private UUID cinemaRoomId;

    @JsonProperty("start_time")
    private Timestamp startTime;

    @JsonProperty("end_time")
    private Timestamp endTime;
}
