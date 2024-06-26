package org.example.cinemamanagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String role;
}
