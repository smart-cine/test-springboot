package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.PaymentDTO;
import org.example.cinemamanagement.payload.request.AddPaymentRequest;

import java.util.UUID;

public interface PaymentService {
    public PaymentDTO getPayment(UUID id);
    public String addPayment(AddPaymentRequest req);
}
