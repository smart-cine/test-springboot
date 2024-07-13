package org.example.cinemamanagement.mapper;

import org.example.cinemamanagement.dto.CinemaRoomDTO;
import org.example.cinemamanagement.model.CinemaRoom;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CinemaRoomMapper {
    public static CinemaRoomDTO toDTO(CinemaRoom cinemaRoom) {

        return CinemaRoomDTO.builder()
                .cinemaId(cinemaRoom.getCinema().getId())
                .cinemaLayoutId(cinemaRoom.getCinemaLayout().getId())
                .id(cinemaRoom.getId())
                .name(cinemaRoom.getName())
                .roomType(cinemaRoom.getRoomType().name())
                .build();
    }

    public static CinemaRoom toEntity(CinemaRoomDTO cinemaRoomDTO) {
        TypeMap<CinemaRoomDTO, CinemaRoom> typeMap = new ModelMapper().createTypeMap(CinemaRoomDTO.class, CinemaRoom.class);
        return typeMap.map(cinemaRoomDTO);
    }
}
