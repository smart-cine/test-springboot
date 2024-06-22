package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.payload.request.AddFilmRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface FilmService {

    public FilmDTO getFilmById(UUID id);

    public FilmDTO addFilm(AddFilmRequest addFilmRequest);

    public FilmDTO updateFilm(FilmDTO filmDTO);

    public void deleteFilm(UUID id);

    public List<FilmDTO> getAllFilms(String title);

    public List<FilmDTO> getFilmByParams(Map<String,String> params);
}
