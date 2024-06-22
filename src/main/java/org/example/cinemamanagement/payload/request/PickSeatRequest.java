package org.example.cinemamanagement.payload.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickSeatRequest {
    private Integer x;
    private Integer y;
}
