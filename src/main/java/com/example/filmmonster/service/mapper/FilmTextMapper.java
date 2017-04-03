package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.FilmTextDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity FilmText and its DTO FilmTextDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FilmTextMapper {

    FilmTextDTO filmTextToFilmTextDTO(FilmText filmText);

    List<FilmTextDTO> filmTextsToFilmTextDTOs(List<FilmText> filmTexts);

    FilmText filmTextDTOToFilmText(FilmTextDTO filmTextDTO);

    List<FilmText> filmTextDTOsToFilmTexts(List<FilmTextDTO> filmTextDTOs);
}
