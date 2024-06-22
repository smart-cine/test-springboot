package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {
    @JsonProperty("comment_id")
    private UUID commentId;

    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("film_id")
    private UUID filmId;

    private String body;
}
