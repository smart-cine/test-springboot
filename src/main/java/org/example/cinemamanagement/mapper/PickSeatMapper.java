package org.example.cinemamanagement.mapper;

import org.example.cinemamanagement.dto.PickSeatDTO;
import org.example.cinemamanagement.dto.TranslateTypeDTO;
import org.example.cinemamanagement.dto.ViewTypeDTO;
import org.example.cinemamanagement.model.PickSeat;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class PickSeatMapper {
    public static PickSeatDTO toDTO(PickSeat pickSeat) {
        TypeMap<PickSeat, PickSeatDTO> typeMap = new ModelMapper()
                .createTypeMap(PickSeat.class, PickSeatDTO.class);
        typeMap.addMappings(mapper ->
        {
            mapper.map(PickSeat::getUser, PickSeatDTO::setUserDTO);
            mapper.map(PickSeat::getPerform, PickSeatDTO::setPerformDTO);
        });

        PickSeatDTO pickSeatDTO = typeMap.map(pickSeat);
        pickSeatDTO.getPerformDTO().setFilmDTO(FilmMapper.toDTO(pickSeat.getPerform().getFilm()));
        pickSeatDTO.getPerformDTO().setCinemaRoomDTO(CinemaRoomMapper.toDTO(pickSeat.getPerform().getCinemaRoom()));
        pickSeatDTO.getPerformDTO().setViewTypeDTO(ViewTypeDTO.builder()
                .id(pickSeat.getPerform().getViewType().getId())
                .viewType(pickSeat.getPerform().getViewType().getViewType())
                .build());

        pickSeatDTO.getPerformDTO().setTranslateTypeDTO(TranslateTypeDTO.builder()
                .id(pickSeat.getPerform().getTranslateType().getId())
                .translateType(pickSeat.getPerform().getTranslateType().getTranslateType())
                .build());
        return pickSeatDTO;
    }
}
