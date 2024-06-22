package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.dto.CinemaManagerDTO;
import org.example.cinemamanagement.mapper.CinemaLayoutMapper;
import org.example.cinemamanagement.mapper.CinemaMapper;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.payload.request.AddCinemaRequest;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CinemaLayoutRepository cinemaLayoutRepository;

    @Override
    public List<CinemaDTO> getAllCinema() {
        return cinemaRepository.findAll()
                .stream()
                .map(CinemaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaDTO getCinema(UUID id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + id));

        return CinemaMapper.toDTO(cinema);
    }

    // check
    @Override
    public CinemaDTO addCinema(AddCinemaRequest addCinemaRequest) {
        Cinema cinema = Cinema.builder()
                .name(addCinemaRequest.getName())
                .variant(addCinemaRequest.getVariant())
                .build();

        cinemaRepository.save(cinema);

        return CinemaMapper.toDTO(cinema);
    }

    @Override
    public CinemaDTO updateCinema(CinemaDTO cinemaDTO) {
        Cinema cinema = cinemaRepository.findById(cinemaDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + cinemaDTO.getId()));

        cinema.setName(cinemaDTO.getName());
        cinema.setVariant(cinemaDTO.getVariant());

        cinemaRepository.save(cinema);

        return CinemaMapper.toDTO(cinema);
    }

    @Override
    public void deleteCinema(UUID id) {
        cinemaRepository.deleteById(id);
    }

    // check
    @Override
    public CinemaManagerDTO deleteCinemaManagerOutOfCinema(String emailUser, UUID idCinema) {
        Cinema cinema = cinemaRepository.findById(idCinema)
                .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + idCinema));

        User user = userRepository.findUserByEmail(emailUser)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + emailUser));

        cinema.getCinemaManagers().remove(user);
        cinemaRepository.save(cinema);

        return CinemaManagerDTO
                .builder()
                .user(user)
                .cinemas(user.getCinemas())
                .build();
    }

/*  @Override
    @Transactional
    public CinemaLayoutDTO addCinemaLayoutIntoCinema(UUID cinemaID, CinemaLayoutDTO cinemaLayoutDTO) {
        CinemaLayout cinemaLayout = CinemaLayout.builder()
                .xIndex(cinemaLayoutDTO.getXIndex())
                .yIndex(cinemaLayoutDTO.getYIndex())
                .build();
        Cinema cinema = cinemaRepository.findById(cinemaID)
                .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + cinemaID));
        if (cinemaLayoutRepository.findByXIndexAndYIndex(cinemaLayout.getXIndex(), cinemaLayout.getYIndex()).isPresent())
                return null;

        cinema.addCinemaLayout(cinemaLayout);
        return CinemaLayoutDTO.builder()
                .id(cinemaLayoutRepository.save(cinemaLayout).getId())
                .xIndex(cinemaLayout.getXIndex())
                .yIndex(cinemaLayout.getYIndex())
                .build();
    }*/



    @Override
    public List<CinemaLayoutDTO> getCinemaLayoutByCinemaId(UUID id) {
        Cinema cinema = cinemaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cinema not found with id: " + id));

        return cinema.getCinemaLayouts().stream()
                .map(CinemaLayoutMapper::toDTO)
                .collect(Collectors.toList());
    }

    /*@Override
    public List<CinemaRoomDTO> getAllCinemaRoomByCinemaId(UUID id) {
        return null;
    }

    @Override
    public CinemaRoomDTO addCinemaRoom(CinemaRoomDTO cinemaRoomDTO) {
        return null;
    }*/
}

