package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;


public interface FilmRepository extends JpaRepository<Film, UUID>, JpaSpecificationExecutor<Film> {
    public Boolean existsFilmByTitle(String title);
}
