package org.example.cinemamanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PickSeatDTO {
    private UUID id;
    private UserDTO userDTO;
    private PerformDTO performDTO;
    private Integer x;
    private Integer y;
}
