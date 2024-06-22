package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.SeatPriceDTO;
import org.example.cinemamanagement.payload.request.AddSeatPriceRequest;

import java.util.List;
import java.util.UUID;

public interface SeatPriceService {
    public String addSeatPrice(AddSeatPriceRequest req);

    public List<SeatPriceDTO> getAllSeatPrice(UUID performId);

    public String deleteSeatPrice(UUID id);
}
