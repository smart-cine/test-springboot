package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.FilmPriceDTO;
import org.example.cinemamanagement.payload.request.AddFilmPriceRequest;

import java.util.List;
import java.util.UUID;

public interface FilmPriceService {
    public String addFilmPrice(AddFilmPriceRequest addFilmPriceRequest);

    public List<FilmPriceDTO> getAllFilmPrice(UUID filmId);

    public String deleteFilmPrice(UUID id);
}
