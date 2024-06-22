package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.FilmPriceDTO;
import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.FilmPrice;
import org.example.cinemamanagement.payload.request.AddFilmPriceRequest;
import org.example.cinemamanagement.repository.FilmPriceRepository;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.service.FilmPriceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmPriceServiceImpl implements FilmPriceService {

    @Autowired
    private FilmPriceRepository filmPriceRepository;

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addFilmPrice(AddFilmPriceRequest req) {
        Film film = filmRepository.findById(req.getFilmId())
                .orElseThrow(() -> new RuntimeException("Film not found"));
        if (req.getPrice() == null)
            throw new RuntimeException("Type not allow NULL");
        if (req.getPrice() == null)
            throw new RuntimeException("Price not allow NULL");

        FilmPrice filmPrice = new FilmPrice();
        filmPrice.setFilm(film);
        filmPrice.setType(req.getType());
        filmPrice.setPrice(req.getPrice());
        filmPriceRepository.save(filmPrice);

        return "Successfully";
    }

    @Override
    public List<FilmPriceDTO> getAllFilmPrice(UUID filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        List<FilmPriceDTO> filmPrices = film.getFilmPrices().stream()
                .map((element) -> modelMapper.map(element, FilmPriceDTO.class))
                .collect(Collectors.toList());

        return filmPrices;
    }

    @Override
    public String deleteFilmPrice(UUID id) {
        filmPriceRepository.deleteById(id);
        return "Deleted successfully";
    }

}
