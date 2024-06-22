package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.PickSeatDTO;
import org.example.cinemamanagement.payload.request.DeletePickSeatRequest;
import org.example.cinemamanagement.payload.request.PickSeatRequest;

import java.util.List;
import java.util.UUID;

public interface PickSeatService {
    List<PickSeatDTO> getAllSeatsPickedOfPerform(UUID performID);

    List<PickSeatDTO> getAllPickSeatsByUser();

    PickSeatDTO getPickSeatById();

    public Object addPickSeat(List<PickSeatRequest> pickSeatRequests, UUID performId);


    Object deletePickSeat(List<DeletePickSeatRequest> deletePickSeatRequests, UUID performID);

}
