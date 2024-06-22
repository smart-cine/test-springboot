package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.payload.request.AddPaymentRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPayment(@PathVariable UUID id) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get payment successfully");
        dataResponse.setData(paymentService.getPayment(id));

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<?> addPayment(@RequestBody AddPaymentRequest req) {
        paymentService.addPayment(req);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add payment successfully");
        return ResponseEntity.ok(dataResponse);
    }
}