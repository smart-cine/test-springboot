package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private UUID id;
    private String title;
    private String director;
    @JsonProperty("release_date")
    private Timestamp releaseDate;
    private String country;
    @JsonProperty("restrict_age")
    private Integer restrictAge;
    private List<TagDTO> tags;
    @JsonProperty("picture_url")
    private String pictureUrl;
    @JsonProperty("trailer_url")
    private String trailerUrl;
    private Integer duration;
    private String description;

}
