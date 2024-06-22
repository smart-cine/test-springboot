package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.SeatPriceDTO;
import org.example.cinemamanagement.model.Perform;
import org.example.cinemamanagement.model.SeatPrice;
import org.example.cinemamanagement.payload.request.AddSeatPriceRequest;
import org.example.cinemamanagement.repository.PerformRepository;
import org.example.cinemamanagement.repository.SeatPriceRepository;
import org.example.cinemamanagement.service.SeatPriceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SeatPriceServiceImpl implements SeatPriceService {
    @Autowired
    private PerformRepository performRepository;

    @Autowired
    private SeatPriceRepository seatPriceRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addSeatPrice(AddSeatPriceRequest req) {
        Perform perform = performRepository.findById(req.getPerformId())
                .orElseThrow(() -> new RuntimeException("Perform not found"));

        if (req.getX() == null || req.getY() == null)
            throw new RuntimeException("X or Y not allow NULL");

        if (seatPriceRepository.findByPerform_IdAndXAndY(req.getPerformId(), req.getX(), req.getY()) != null)
            throw new RuntimeException("X and Y already exist");

        if (req.getPrice() == null)
            throw new RuntimeException("Price not allow NULL");

        SeatPrice seatPrice = new SeatPrice();
        seatPrice.setPerform(perform);
        seatPrice.setX(req.getX());
        seatPrice.setY(req.getY());
        seatPrice.setPrice(req.getPrice());
        seatPriceRepository.save(seatPrice);

        return "Successfully";
    }

    @Override
    public List<SeatPriceDTO> getAllSeatPrice(UUID performId) {
        Perform perform = performRepository.findById(performId)
                .orElseThrow(() -> new RuntimeException("Perform not found"));

        List<SeatPriceDTO> seatPrice = perform.getSeatPrices().stream()
                .map((element) -> modelMapper.map(element, SeatPriceDTO.class))
                .collect(Collectors.toList());

        return seatPrice;
    }

    @Override
    public String deleteSeatPrice(UUID id) {
        seatPriceRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
