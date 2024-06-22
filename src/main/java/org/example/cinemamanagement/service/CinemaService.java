package org.example.cinemamanagement.service;


import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.payload.request.AddCinemaRequest;

import java.util.List;
import java.util.UUID;

public interface CinemaService {
    public List<CinemaDTO> getAllCinema();

    public CinemaDTO getCinema(UUID id);

    public CinemaDTO addCinema(AddCinemaRequest addCinemaRequest);

    public void deleteCinema(UUID id);

    public CinemaDTO updateCinema(CinemaDTO cinemaDTO);

    // public CinemaLayoutDTO addCinemaLayoutIntoCinema(UUID idCinema, CinemaLayoutDTO cinemaLayoutDTO);

    public List<CinemaLayoutDTO> getCinemaLayoutByCinemaId(UUID id);

    //  public List<CinemaRoomDTO> getAllCinemaRoomByCinemaId(UUID id);

    public CinemaManagerDTO deleteCinemaManagerOutOfCinema(String emailUser, UUID idCinema);

}
