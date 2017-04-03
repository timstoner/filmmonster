package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.FilmActorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity FilmActor and its DTO FilmActorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FilmActorMapper {

    @Mapping(source = "actor.id", target = "actorId")
    @Mapping(source = "film.id", target = "filmId")
    FilmActorDTO filmActorToFilmActorDTO(FilmActor filmActor);

    List<FilmActorDTO> filmActorsToFilmActorDTOs(List<FilmActor> filmActors);

    @Mapping(source = "actorId", target = "actor")
    @Mapping(source = "filmId", target = "film")
    FilmActor filmActorDTOToFilmActor(FilmActorDTO filmActorDTO);

    List<FilmActor> filmActorDTOsToFilmActors(List<FilmActorDTO> filmActorDTOs);

    default Actor actorFromId(Long id) {
        if (id == null) {
            return null;
        }
        Actor actor = new Actor();
        actor.setId(id);
        return actor;
    }

    default Film filmFromId(Long id) {
        if (id == null) {
            return null;
        }
        Film film = new Film();
        film.setId(id);
        return film;
    }
}
