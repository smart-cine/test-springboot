package org.example.cinemamanagement.payload.request;


import lombok.Data;

@Data
public class AddCinemaRequest {
    private String variant;
    private String name;
}
