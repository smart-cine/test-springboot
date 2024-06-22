package org.example.cinemamanagement.payload.request;

// import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class DeletePickSeatRequest {
    private Integer x;
    private Integer y;
//    @JsonProperty("perform_id")
//    private UUID performID;
}
