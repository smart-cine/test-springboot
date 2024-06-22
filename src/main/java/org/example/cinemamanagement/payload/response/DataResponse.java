package org.example.cinemamanagement.payload.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private String message;
    private Object data;
}
