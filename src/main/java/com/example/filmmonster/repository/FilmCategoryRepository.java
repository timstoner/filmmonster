package com.example.filmmonster.repository;

import com.example.filmmonster.domain.FilmCategory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FilmCategory entity.
 */
@SuppressWarnings("unused")
public interface FilmCategoryRepository extends JpaRepository<FilmCategory,Long> {

}
