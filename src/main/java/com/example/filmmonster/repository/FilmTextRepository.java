package com.example.filmmonster.repository;

import com.example.filmmonster.domain.FilmText;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FilmText entity.
 */
@SuppressWarnings("unused")
public interface FilmTextRepository extends JpaRepository<FilmText,Long> {

}
