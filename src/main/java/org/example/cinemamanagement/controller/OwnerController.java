package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<?> getAmountOfFilm() {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get amount of film successfully");
        dataResponse.setData(ownerService.getAmountOfFilm());

        return ResponseEntity.ok(dataResponse);
    }
}
