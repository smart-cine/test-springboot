package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddPerformRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.PerformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/performs")
public class PerformController {

    PerformService performService;

    @Autowired
    public PerformController(PerformService performService) {
        this.performService = performService;
    }

    @GetMapping
    public ResponseEntity<?> getPerforms() {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all performs successfully");
        dataResponse.setData(performService.getAllPerforms());

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerform() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addPerform(@RequestBody AddPerformRequest addPerformRequest)
    {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add perform successfully");
        dataResponse.setData(performService.addPerform(addPerformRequest));

        return ResponseEntity.ok(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerform() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deletePerform() {
        return ResponseEntity.ok().build();
    }
}
