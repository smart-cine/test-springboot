package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.example.cinemamanagement.common.LayoutType;

@Data
public class AddCinemaLayoutRequest {
    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private LayoutType layoutType;
    private String data;
}
