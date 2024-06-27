package org.example.cinemamanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cinemamanagement.common.LayoutType;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaLayoutDTO {
    private UUID id;

    @JsonProperty("data")
    private String data;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private LayoutType layoutType;
}
