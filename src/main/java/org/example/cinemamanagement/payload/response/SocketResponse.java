package org.example.cinemamanagement.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SocketResponse {
    Integer x;
    Integer y;
}
