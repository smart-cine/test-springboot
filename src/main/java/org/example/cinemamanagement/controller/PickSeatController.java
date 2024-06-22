package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.DeletePickSeatRequest;
import org.example.cinemamanagement.payload.request.PickSeatRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.payload.response.SocketResponse;
import org.example.cinemamanagement.service.PickSeatService;
import org.example.cinemamanagement.service.SocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pick-seat")
public class PickSeatController {

    PickSeatService pickSeatService;
    SocketIOService socketIOService;

    @Autowired
    public PickSeatController(PickSeatService pickSeatService, SocketIOService socketIOService) {
        this.pickSeatService = pickSeatService;
        this.socketIOService = socketIOService;
    }

    @GetMapping
    public String getPickSeat() {
        return "Pick Seat";
    }

    @GetMapping("/{performID}")
    public ResponseEntity<?> getPickedSeatsByPerformID(@PathVariable UUID performID) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all picked seats successfully");
        dataResponse.setData(pickSeatService.getAllSeatsPickedOfPerform(performID));
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/{performID}")
    public ResponseEntity<?> addPickSeats(@PathVariable UUID performID,
                                          @RequestBody List<PickSeatRequest> pickSeatRequests) {

        pickSeatService.addPickSeat(pickSeatRequests, performID);

        List<SocketResponse> socketResponses = pickSeatRequests.stream().map(pickSeatRequest -> {
            return SocketResponse.builder()
                    .x(pickSeatRequest.getX())
                    .y(pickSeatRequest.getY())
                    .build();
        }).toList();

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add pick seat successfully");
        dataResponse.setData(socketResponses);

        socketIOService.emit("seat-add", Map.of("performID", performID, "seats", socketResponses));
        return ResponseEntity.ok(Map.of("performID", performID, "seats", socketResponses));
    }

    @DeleteMapping("/{performID}")
    public ResponseEntity<?> deletePickSeat(@RequestBody List<DeletePickSeatRequest> DeletePickSeatRequests, @PathVariable UUID performID) {
        pickSeatService.deletePickSeat(DeletePickSeatRequests, performID);

        Object data = Map.of("performID", performID, "seats", DeletePickSeatRequests.stream()
                .map(deletePickSeatRequest -> {
                    return SocketResponse.builder()
                            .x(deletePickSeatRequest.getX())
                            .y(deletePickSeatRequest.getY())
                            .build();
                }).toList());

        socketIOService.emit("seat-remove", data);
        return ResponseEntity.ok(DataResponse.builder()
                .data(data)
                .message("Delete pick seat successfully")
                .build());

    }
}

