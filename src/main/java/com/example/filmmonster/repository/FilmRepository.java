package com.example.filmmonster.repository;

import com.example.filmmonster.domain.Film;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Film entity.
 */
@SuppressWarnings("unused")
public interface FilmRepository extends JpaRepository<Film,Long> {

}
