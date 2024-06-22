package org.example.cinemamanagement.mapper;

import org.example.cinemamanagement.dto.CinemaDTO;
import org.example.cinemamanagement.model.Cinema;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CinemaMapper {
    public static CinemaDTO toDTO(Cinema cinema) {
        TypeMap<Cinema, CinemaDTO> typeMap = new ModelMapper().createTypeMap(Cinema.class, CinemaDTO.class);
        return typeMap.map(cinema);
        /*return CinemaDTO.builder()
                .id(cinema.getId())
                .variant(cinema.getVariant())
                .name(cinema.getName())
                .build();*/
    }
}
