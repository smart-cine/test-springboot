package org.example.cinemamanagement.mapper;

import org.example.cinemamanagement.dto.PerformDTO;
import org.example.cinemamanagement.model.Perform;

public class PerformMapper {
    public static PerformDTO toDTO(Perform perform) {
        return PerformDTO.builder()
                .id(perform.getId())
                .filmId(perform.getFilm().getId())
                .cinemaRoomId(perform.getCinemaRoom().getId())
                .price(perform.getPrice())
                .translateType(perform.getTranslateType().name())
                .viewType(perform.getViewType().name())
                .startTime(perform.getStartTime())
                .endTime(perform.getEndTime())
                .build();
    }

    public static Perform toEntity(PerformDTO performDTO) {
        return Perform.builder()
                .startTime(performDTO.getStartTime())
                .endTime(performDTO.getEndTime())
                .build();
    }
}
