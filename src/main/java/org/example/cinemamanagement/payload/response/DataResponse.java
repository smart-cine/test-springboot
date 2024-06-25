package org.example.cinemamanagement.payload.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private boolean success;
    private String message;
    private Object data;
}
