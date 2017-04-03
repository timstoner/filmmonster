package com.example.filmmonster.repository;

import com.example.filmmonster.domain.FilmActor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FilmActor entity.
 */
@SuppressWarnings("unused")
public interface FilmActorRepository extends JpaRepository<FilmActor,Long> {

}
