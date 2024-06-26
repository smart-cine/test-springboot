package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;


public interface FilmRepository extends JpaRepository<Film, UUID>, JpaSpecificationExecutor<Film> {
    public Boolean existsFilmByTitle(String title);

    @Query("select f from Film f where f.title < ?1 order by f.title desc limit 1")
    public Optional<Film> theFirstFilmBehindCurrFilm(String title);
}
