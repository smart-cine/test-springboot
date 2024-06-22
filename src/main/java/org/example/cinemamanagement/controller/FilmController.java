package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.payload.request.AddFilmRequest;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<?> getFilms(@RequestParam Map<String, String> params) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all films successfully");
        dataResponse.setData(filmService.getAllFilms(params.get("title")));

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<?> addFilm(@RequestBody AddFilmRequest addFilmRequest) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add film successfully");
        dataResponse.setData(filmService.addFilm(addFilmRequest));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable UUID id) {
        filmService.deleteFilm(id);
        return ResponseEntity.ok("Film deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmById(@PathVariable UUID id) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get film by id successfully");
        dataResponse.setData(filmService.getFilmById(id));

        return ResponseEntity.ok(dataResponse);
    }
}
