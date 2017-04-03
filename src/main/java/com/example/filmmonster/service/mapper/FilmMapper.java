package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.FilmDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Film and its DTO FilmDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FilmMapper {

    @Mapping(source = "language.id", target = "languageId")
    @Mapping(source = "originalLanguage.id", target = "originalLanguageId")
    FilmDTO filmToFilmDTO(Film film);

    List<FilmDTO> filmsToFilmDTOs(List<Film> films);

    @Mapping(source = "languageId", target = "language")
    @Mapping(source = "originalLanguageId", target = "originalLanguage")
    Film filmDTOToFilm(FilmDTO filmDTO);

    List<Film> filmDTOsToFilms(List<FilmDTO> filmDTOs);

    default Language languageFromId(Long id) {
        if (id == null) {
            return null;
        }
        Language language = new Language();
        language.setId(id);
        return language;
    }
}
