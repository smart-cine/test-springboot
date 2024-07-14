package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.PerformDTO;
import org.example.cinemamanagement.mapper.PerformMapper;
import org.example.cinemamanagement.model.*;
import org.example.cinemamanagement.payload.request.AddPerformRequest;
import org.example.cinemamanagement.repository.*;
import org.example.cinemamanagement.service.PerformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformServiceImpl implements PerformService {
    PerformRepository performRepository;

    CinemaRoomRepository cinemaRoomRepository;

    FilmRepository filmRepository;

    @Autowired
    public PerformServiceImpl(PerformRepository performRepository,
                              CinemaRoomRepository cinemaRoomRepository,
                              FilmRepository filmRepository
    ) {
        this.performRepository = performRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public List<PerformDTO> getAllPerforms() {
        return performRepository.findAll().stream().map(
                PerformMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PerformDTO addPerform(AddPerformRequest addPerformRequest) {
        CinemaRoom cinemaRoom = cinemaRoomRepository
                .findById(addPerformRequest.getCinemaRoomId())
                .orElseThrow(() -> new RuntimeException("Cinema room not found"));
        Film film = filmRepository.findById(addPerformRequest.getFilmId())
                .orElseThrow(() -> new RuntimeException("Film not found"));


        if(!performRepository.TimeForPerformIsAvailable(
                cinemaRoom.getId(),
                addPerformRequest.getStartTime(),
                addPerformRequest.getEndTime()
        ).isEmpty()) {
            throw new RuntimeException("Time for perform is not available");
        }

        Perform perform = performRepository.save(
                Perform.builder()
                        .film(film)
                        .cinemaRoom(cinemaRoom)
//                        .viewType(viewType)
//                        .translateType(translateType)
                        .startTime(addPerformRequest.getStartTime())
                        .endTime(addPerformRequest.getEndTime())
                        .build()
        );

        return PerformMapper.toDTO(perform);
    }
}
