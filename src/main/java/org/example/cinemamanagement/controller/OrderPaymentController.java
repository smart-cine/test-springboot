package org.example.cinemamanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cinemamanagement.payload.request.OrderRequestDTO;
import org.example.cinemamanagement.service.impl.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class OrderPaymentController {
    @Autowired
    private OrderPaymentService orderPaymentService;

    @PostMapping("/api/v1/payment/create-order")
    public ResponseEntity<Map<String, Object>> createOrderPayment(
            HttpServletRequest request,
            @RequestBody OrderRequestDTO orderRequestDTO) throws IOException {

        Map<String, Object> result = this.orderPaymentService
                .createOrder(request, orderRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
