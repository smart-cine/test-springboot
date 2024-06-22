package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddCinemaRoomRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class CinemaRoomController {

    @Autowired
    CinemaRoomService cinemaRoomService;

    @GetMapping
    public ResponseEntity<?> getAllRoom() {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add cinema room successfully");
        dataResponse.setData(cinemaRoomService.getAllCinemaRooms());

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody AddCinemaRoomRequest addCinemaRoomRequest) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add cinema room successfully");
        dataResponse.setData(cinemaRoomService.addCinemaRoom(addCinemaRoomRequest));

        return ResponseEntity.ok(dataResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateRoom() {
        return ResponseEntity.ok("Room updated");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoom() {
        return ResponseEntity.ok("Room deleted");
    }
}
