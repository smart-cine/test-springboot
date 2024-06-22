package org.example.cinemamanagement;

import org.example.cinemamanagement.dto.PerformDTO;
import org.example.cinemamanagement.mapper.PerformMapper;
import org.example.cinemamanagement.model.*;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

//@SpringBootTest

class CinemaManagementApplicationTests {

    Caculator caculator = new Caculator();

    @Test
    void itShouldAddNumbers() {
        Perform perform = Perform.builder()
                .cinemaRoom(CinemaRoom.builder().id(UUID.randomUUID()).build())
                .film(Film.builder().id(UUID.randomUUID()).
                        title("The Avengers")
                        .build())
                .viewType(ViewType.builder().id(UUID.randomUUID()).viewType("3D").build())
                .translateType(TranslateType.builder().id(UUID.randomUUID()).build())
                .startTime(Timestamp.valueOf(LocalDateTime.now()))
                .endTime(Timestamp.valueOf(LocalDateTime.now().plusHours(2)))
                .build();
        PerformDTO performDTO = PerformMapper.toDTO(perform);
        System.out.println(performDTO);
    }

    class Caculator {
        static int add(int a, int b) {
            return a + b;
        }
    }


}
