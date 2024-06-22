package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.payload.request.AddCinemaLayoutRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.CinemaLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/layouts")
public class CinemaLayoutController {



    CinemaLayoutService cinemaLayoutService;

    @Autowired
    public CinemaLayoutController(CinemaLayoutService cinemaLayoutService) {
        this.cinemaLayoutService = cinemaLayoutService;
    }

    @GetMapping
    public ResponseEntity<?> getAllLayout() {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all layouts successfully");
        dataResponse.setData(cinemaLayoutService.getAllCinemaLayout());

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<?> addLayout(@RequestBody AddCinemaLayoutRequest cinemaLayoutRequest) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add layout successfully");
        dataResponse.setData(cinemaLayoutService.addCinemaLayout(cinemaLayoutRequest));

        return ResponseEntity.ok(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLayout(@PathVariable UUID id, @RequestBody CinemaLayoutDTO cinemaLayoutDTO) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Update layout successfully");
        dataResponse.setData(cinemaLayoutService.updateCinemaLayout(id,cinemaLayoutDTO));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLayout(@PathVariable UUID id) {

        cinemaLayoutService.deleteCinemaLayout(id);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Layout deleted successfully");

        return ResponseEntity.ok(dataResponse);
    }

}
