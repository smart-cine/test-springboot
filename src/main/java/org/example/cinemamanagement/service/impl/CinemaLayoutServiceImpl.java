package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.CinemaLayoutDTO;
import org.example.cinemamanagement.mapper.CinemaLayoutMapper;
import org.example.cinemamanagement.model.CinemaLayout;
import org.example.cinemamanagement.payload.request.AddCinemaLayoutRequest;
import org.example.cinemamanagement.repository.CinemaLayoutRepository;
import org.example.cinemamanagement.service.CinemaLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaLayoutServiceImpl implements CinemaLayoutService {

    @Autowired
    private CinemaLayoutRepository cinemaLayoutRepository;

    @Override
    public List<CinemaLayoutDTO> getAllCinemaLayout() {
        return cinemaLayoutRepository.findAll()
                .stream()
                .map(CinemaLayoutMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaLayoutDTO getCinemaLayout(UUID id) {
        CinemaLayout layout = cinemaLayoutRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + id));
        return CinemaLayoutMapper.toDTO(layout);
    }

    @Override
    @Transactional
    public CinemaLayoutDTO addCinemaLayout(AddCinemaLayoutRequest cinemaLayoutRequest) {
        Optional<CinemaLayout> layout = cinemaLayoutRepository.findByXIndexAndYIndex(cinemaLayoutRequest.getXIndex()
                , cinemaLayoutRequest.getYIndex());
        if (layout.isPresent()) {
            throw new RuntimeException("Cinema layout already exists with xIndex: "
                    + cinemaLayoutRequest.getXIndex() + " and yIndex: " + cinemaLayoutRequest.getYIndex());
        }

        CinemaLayout newLayout = new CinemaLayout();
        newLayout.setXIndex(cinemaLayoutRequest.getXIndex());
        newLayout.setYIndex(cinemaLayoutRequest.getYIndex());
        cinemaLayoutRepository.save(newLayout);
        return CinemaLayoutMapper.toDTO(newLayout);

    }

    @Override
    @Transactional
    public CinemaLayoutDTO updateCinemaLayout(UUID idLayout, CinemaLayoutDTO cinemaLayoutDTO) {
        CinemaLayout layout = cinemaLayoutRepository
                .findById(idLayout)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + idLayout));
        if (cinemaLayoutDTO.getXIndex() != null
                && !cinemaLayoutDTO.getXIndex().equals(layout.getXIndex()))
            layout.setXIndex(cinemaLayoutDTO.getXIndex());

        if (cinemaLayoutDTO.getYIndex() != null
                && !cinemaLayoutDTO.getYIndex().equals(layout.getYIndex()))
            layout.setYIndex(cinemaLayoutDTO.getYIndex());
        cinemaLayoutRepository.save(layout);

        return CinemaLayoutMapper.toDTO(layout);
    }

    @Override
    @Transactional
    public void deleteCinemaLayout(UUID id) {
        CinemaLayout layout = cinemaLayoutRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cinema layout not found with id: " + id));

        cinemaLayoutRepository.deleteById(id);
    }
}
