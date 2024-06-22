package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddSeatPriceRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.SeatPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/seat-price")
public class SeatPriceController {
    @Autowired
    private SeatPriceService seatPriceService;

    @PostMapping
    public ResponseEntity<?> addSeatPrice(@RequestBody AddSeatPriceRequest req) {
        seatPriceService.addSeatPrice(req);
        return ResponseEntity.ok(DataResponse.builder()
                .message("Seat price added successfully")
                .data(null)
                .build());
    }

    @GetMapping("/{performId}")
    public ResponseEntity<?> getAllSeatPrice(@PathVariable UUID performId) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all seat price successfully");
        dataResponse.setData(seatPriceService.getAllSeatPrice(performId));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeatPrice(@PathVariable UUID id) {
        seatPriceService.deleteSeatPrice(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Seat price deleted successfully");
        return ResponseEntity.ok(dataResponse);
    }
}
