package org.example.cinemamanagement.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.cinemamanagement.common.LayoutType;

@Data
public class AddCinemaLayoutRequest {
    private LayoutType layoutType;
    private String data;
}
