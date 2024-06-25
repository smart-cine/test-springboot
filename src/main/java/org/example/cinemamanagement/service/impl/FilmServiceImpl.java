package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.FilmDTO;
import org.example.cinemamanagement.mapper.FilmMapper;
import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.Tag;
import org.example.cinemamanagement.payload.request.AddFilmRequest;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.repository.TagRepository;
import org.example.cinemamanagement.service.FilmService;
import org.example.cinemamanagement.utils.CursorBasedPageable;
import org.example.cinemamanagement.utils.PageResponse;
import org.example.cinemamanagement.utils.PageSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Boolean checkExistence = filmRepository.existsFilmByTitle(addFilmRequest.getTitle());
        if (checkExistence) {
            throw new RuntimeException("Film already exists");
        }

        List<Tag> tags = addFilmRequest.getTags().stream().map(tag -> {
                    Optional<Tag> currTag = tagRepository.findTagByName(tag.getName());
                    if (currTag.isEmpty()) {
                        return tagRepository.save(Tag.builder().name(tag.getName()).build());
                    }
                    return currTag.get();
                }
        ).toList();

        Film tempFilm = filmRepository.save(Film.builder()
                .title(addFilmRequest.getTitle())
                .director(addFilmRequest.getDirector())
                .country(addFilmRequest.getCountry())
                .releaseDate(addFilmRequest.getReleaseDate())
                .restrictAge(addFilmRequest.getRestrictAge())
                .pictureUrl(addFilmRequest.getPictureUrl())
                .trailerUrl(addFilmRequest.getTrailerUrl())
                .duration(addFilmRequest.getDuration())
                .tags(tags)
                .description(addFilmRequest.getDescription())
                .language(addFilmRequest.getLanguage())
                .build());

        return FilmMapper.toDTO(tempFilm);
    }

    @Override
    public FilmDTO updateFilm(FilmDTO filmDTO) {
//        Film film = filmRepository.findById(filmDTO.getId())
//                .orElseThrow(() -> new RuntimeException("Film not found"));
//        if (filmDTO.getTitle() != null)
//            film.setTitle(filmDTO.getTitle());
//
//        if (filmDTO.getDirector() != null)
//            film.setDirector(filmDTO.getDirector());
//
//        if (filmDTO.getCountry() != null)
//            film.setCountry(filmDTO.getCountry());
//
//        if (filmDTO.getRestrictAge() != null)
//            film.setRestrictAge(filmDTO.getRestrictAge());
//
//        if (filmDTO.getReleaseDate() != null)
//            film.setReleaseDate(filmDTO.getReleaseDate());
//
//        if (filmDTO.getTags() != null) {
//            List<Tag> tags = filmDTO.getTags().stream()
//                    .map(tagDTO -> Tag.builder()
//                            .id(tagDTO.getId())
//                            .name(tagDTO.getName())
//                            .build()
//                    )
//                    .collect(Collectors.toList());
//            film.setTags(tags);
//        }
//        filmRepository.save(film);
//        return FilmMapper.toDTO(film);

        return null;
    }

    @Override
    public void deleteFilm(UUID id) {
        filmRepository.deleteById(id);
    }


    @Override
    public PageResponse<List<FilmDTO>> page(
            PageSpecification<Film> pageSpecification,
            CursorBasedPageable cursorBasedPageable) {

//        String sqlQuery = buildSQLQuery(paramsString);
//       List <Film> films = filmRepository.findAll(sqlQuery);
//       List <FilmDTO> filmDTOS = films.stream()
//               .map(FilmMapper::toDTO)
//               .collect(Collectors.toList());
//
//       String previosCursor = cursorBasedPageable.getPrevPageCursor();
//       String nextCursor = cursorBasedPageable.getNextPageCursor();
//
//       return new PageResponse<>(filmDTOS, previosCursor, nextCursor);

        var filmSlide  =  filmRepository.findAll(pageSpecification,
                Pageable.ofSize(cursorBasedPageable.getSize()));

        if(!filmSlide.hasContent()) return new PageResponse<>(false, null, null);
        Map<String, String> pagingMap = new HashMap<>();

        List<Film> films = filmSlide.getContent();
        pagingMap.put("previousPageCursor", cursorBasedPageable.getEncodedCursor(films.get(0).getTitle(),hasPreviousPage(films.get(0))));
        pagingMap.put("nextPageCursor", cursorBasedPageable.getEncodedCursor(films.get(films.size() - 1).getTitle(), filmSlide.hasNext()));
        pagingMap.put("size", String.valueOf(cursorBasedPageable.getSize()));
        pagingMap.put("total", String.valueOf(filmSlide.getTotalElements()));

//        return new PageResponse<>(true ,films.stream().map(FilmMapper::toDTO).collect(Collectors.toList()),
//                cursorBasedPageable.getEncodedCursor(films.get(0).getTitle(), filmSlide.hasPrevious() ),
//                cursorBasedPageable.getEncodedCursor(films.get(films.size() - 1).getTitle(), filmSlide.hasNext()));
        return new PageResponse<>(true ,films.stream()
                .map(FilmMapper::toDTO).collect(Collectors.toList()),
                pagingMap);
    }

    private Boolean hasPreviousPage(Film firstFilm) {
        CursorBasedPageable tempCursor = CursorBasedPageable.builder()
                .build();

        tempCursor.setPrevPageCursor(tempCursor.getEncodedCursor(firstFilm.getTitle(), true));
        var slide = filmRepository.findAll(new PageSpecification<Film>("title", tempCursor), Pageable.ofSize(1));
        return slide.hasContent();
    }

    private String buildSQLQuery(Map<String, String> paramsString) {
        StringBuilder sqlQuery = new StringBuilder("select * from film where ");
        for (Map.Entry<String, String> entry : paramsString.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if(value != null && !value.isEmpty())
            {
                sqlQuery.append(key).append(" like %").append(value).append("%");
            }
        }

        return sqlQuery.toString();
    }


    @Override
    public List<FilmDTO> getFilmByParams(Map<String, String> params) {
        return List.of();
    }
}
