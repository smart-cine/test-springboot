package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.mapper.FilmMapper;
import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.Tag;
import org.example.cinemamanagement.payload.request.AddFilmRequest;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.repository.TagRepository;
import org.example.cinemamanagement.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    TagRepository tagRepository;
    FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(TagRepository tagRepository, FilmRepository filmRepository) {
        this.tagRepository = tagRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmDTO getFilmById(UUID id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));
        return FilmMapper.toDTO(film);
    }

    @Override
    public FilmDTO addFilm(AddFilmRequest addFilmRequest) {
        Optional<Film> film = filmRepository.findFilmByTitle(addFilmRequest.getTitle());
        if (film.isPresent()) {
            throw new RuntimeException("Film already exists");
        }

        List<Tag> tags = addFilmRequest.getTags().stream().map(tag -> {
            Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
            return tagOptional.orElseGet(() -> tagRepository.save(Tag
                    .builder()
                    .name(tag.getName())
                    .build())
            );
        }).collect(Collectors.toList());

        Film tempFilm = filmRepository.save(
                Film
                        .builder()
                        .title(addFilmRequest.getTitle())
                        .director(addFilmRequest.getDirector())
                        .country(addFilmRequest.getCountry())
                        .releaseDate(addFilmRequest.getReleaseDate())
                        .restrictAge(addFilmRequest.getRestrictAge())
                        .pictureUrl(addFilmRequest.getPictureUrl())
                        .trailerUrl(addFilmRequest.getTrailerUrl())
                        .duration(addFilmRequest.getDuration())
                        .tags(tags)
                        .build());
        return FilmMapper.toDTO(tempFilm);
    }

    @Override
    public FilmDTO updateFilm(FilmDTO filmDTO) {
        Film film = filmRepository.findById(filmDTO.getId())
                .orElseThrow(() -> new RuntimeException("Film not found"));
        if (filmDTO.getTitle() != null)
            film.setTitle(filmDTO.getTitle());

        if (filmDTO.getDirector() != null)
            film.setDirector(filmDTO.getDirector());

        if (filmDTO.getCountry() != null)
            film.setCountry(filmDTO.getCountry());

        if (filmDTO.getRestrictAge() != null)
            film.setRestrictAge(filmDTO.getRestrictAge());

        if (filmDTO.getReleaseDate() != null)
            film.setReleaseDate(filmDTO.getReleaseDate());

        if (filmDTO.getTags() != null) {
            List<Tag> tags = filmDTO.getTags().stream()
                    .map(tagDTO -> Tag.builder()
                            .id(tagDTO.getId())
                            .name(tagDTO.getName())
                            .build()
                    )
                    .collect(Collectors.toList());
            film.setTags(tags);
        }
        filmRepository.save(film);
        return FilmMapper.toDTO(film);
    }

    @Override
    public void deleteFilm(UUID id) {
        filmRepository.deleteById(id);
    }

    @Override
    public List<FilmDTO> getAllFilms(String title) {
        if (title == null || title.isEmpty() || title.isBlank())
            return filmRepository.findAll().stream()
                    .map(FilmMapper::toDTO).collect(Collectors.toList());

        return filmRepository.findAll(title).stream()
                .map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> getFilmByParams(Map<String, String> params) {
        return List.of();
    }
}
