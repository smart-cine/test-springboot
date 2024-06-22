package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.User;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaManagerDTO {
    @JsonProperty("cinema_manager")
    private User user;
    @JsonProperty("cinemas")
    private List<Cinema> cinemas;
}
