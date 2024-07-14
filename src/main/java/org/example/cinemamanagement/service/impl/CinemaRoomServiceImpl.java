package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.mapper.CinemaRoomMapper;
import org.example.cinemamanagement.model.Cinema;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.model.CinemaRoom;
import org.example.cinemamanagement.payload.request.AddCinemaRoomRequest;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.repository.CinemaRepository;
import org.example.cinemamanagement.repository.CinemaRoomRepository;
import org.example.cinemamanagement.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaRoomServiceImpl implements CinemaRoomService {

    public CinemaLayoutRepository cinemaLayoutRepository;
    public CinemaRepository cinemaRepository;

    public CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public CinemaRoomServiceImpl(CinemaLayoutRepository cinemaLayoutRepository, CinemaRepository cinemaRepository, CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaLayoutRepository = cinemaLayoutRepository;
        this.cinemaRepository = cinemaRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    @Override
    public List<CinemaRoomDTO> getAllCinemaRooms() {
        return cinemaRoomRepository.findAll().stream()
                .map(CinemaRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaRoomDTO getCinemaRoomById(UUID id) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CinemaRoom not found"));
        return CinemaRoomMapper.toDTO(cinemaRoom);
    }

    @Override
    @Transactional
    public CinemaRoomDTO addCinemaRoom(AddCinemaRoomRequest addCinemaRoomRequest) {
        Cinema cinema = cinemaRepository.findById(addCinemaRoomRequest.getCinemaId())
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        CinemaLayout layout = cinemaLayoutRepository.findById(addCinemaRoomRequest.getCinemaLayoutId())
                .orElseThrow(() -> new RuntimeException("Cinema layout not found"));

        Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findByNameAndCinemaId(addCinemaRoomRequest.getName(),
                addCinemaRoomRequest.getCinemaId());

        if (cinemaRoom.isEmpty()) {
            if (!cinema.getCinemaLayouts().contains(layout)) {
                cinema.addCinemaLayout(layout);
            }
            CinemaRoom newCinemaroom = cinemaRoomRepository.save(CinemaRoom.builder()
                    .cinema(cinema)
                    .cinemaLayout(layout)
                    .name(addCinemaRoomRequest.getName())
                    .build());

            return CinemaRoomMapper.toDTO(newCinemaroom);
        }

        throw new RuntimeException("Cinema room already exists");
    }

    @Override
    public void updateCinemaRoom(CinemaRoomDTO cinemaRoomDTO) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(cinemaRoomDTO.getId())
                .orElseThrow(() -> new RuntimeException("CinemaRoom not found"));
        if (cinemaRoomDTO.getName() != null)
            cinemaRoom.setName(cinemaRoomDTO.getName());

        if (cinemaRoomDTO.getCinemaDTO() != null) {
            cinemaRoom.setCinema(

                    Cinema.builder()
                            .id(cinemaRoomDTO.getCinemaDTO().getId())
                            .variant(cinemaRoomDTO.getCinemaDTO().getVariant())
                            .name(cinemaRoomDTO.getCinemaDTO().getName())
                            .build()
            );
        }

        if (cinemaRoomDTO.getCinemaLayoutDTO() != null) {
            CinemaLayout cinemaLayout = new CinemaLayout();
            cinemaLayout.builder()
                    .id(cinemaRoomDTO.getCinemaLayoutDTO().getId())
//                    .xIndex(cinemaRoomDTO.getCinemaLayoutDTO().getXIndex())
//                    .yIndex(cinemaRoomDTO.getCinemaLayoutDTO().getYIndex())
                    .build();
            cinemaRoom.setCinemaLayout(cinemaLayout);
        }

        cinemaRoomRepository.save(cinemaRoom);
    }

    @Override
    public void deleteCinemaRoom(UUID id) {
        cinemaRoomRepository.deleteById(id);
    }
}
