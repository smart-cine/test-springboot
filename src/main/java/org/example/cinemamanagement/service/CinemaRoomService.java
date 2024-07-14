package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.payload.request.AddCinemaRoomRequest;

import java.util.List;
import java.util.UUID;

public interface CinemaRoomService {

    public List<CinemaRoomDTO> getAllCinemaRooms();
    public CinemaRoomDTO getCinemaRoomById(UUID id);

    public CinemaRoomDTO addCinemaRoom(AddCinemaRoomRequest addCinemaRoomRequest);

    public void updateCinemaRoom(CinemaRoomDTO cinemaRoomDTO);

    public void deleteCinemaRoom(UUID id);



}
