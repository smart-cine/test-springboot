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
    @JsonProperty("film")
    private FilmDTO filmDTO;
    @JsonProperty("view_type")
    private ViewTypeDTO viewTypeDTO;
    @JsonProperty("translate_type")
    private TranslateTypeDTO translateTypeDTO;
    @JsonProperty("cinema_room")
    private CinemaRoomDTO cinemaRoomDTO;
    @JsonProperty("start_time")
    private Timestamp startTime;
    @JsonProperty("end_time")
    private Timestamp endTime;
}
