package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Cinema;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CinemaRepositoryTest {

    @Autowired
    private CinemaRepository underTest;

    @Test
    void findById() {
//        given
        Cinema cinema = Cinema.builder()
                .name("CGV")
                .variant("Binh Thuan")
                .build();
        underTest.save(cinema);
//        when
        Optional<Cinema> cinemaOptional = underTest.findById(cinema.getId());

//        then
//        assertThat(cinemaOptional).isPresent();
        System.out.println(cinemaOptional.get().getName());
    }
}