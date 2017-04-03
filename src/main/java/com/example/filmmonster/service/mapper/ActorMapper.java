package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.ActorDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Actor and its DTO ActorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActorMapper {

    ActorDTO actorToActorDTO(Actor actor);

    List<ActorDTO> actorsToActorDTOs(List<Actor> actors);

    Actor actorDTOToActor(ActorDTO actorDTO);

    List<Actor> actorDTOsToActors(List<ActorDTO> actorDTOs);
}
