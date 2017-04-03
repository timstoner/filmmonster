package com.example.filmmonster.service.mapper;

import com.example.filmmonster.domain.*;
import com.example.filmmonster.service.dto.FilmCategoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity FilmCategory and its DTO FilmCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FilmCategoryMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "film.id", target = "filmId")
    FilmCategoryDTO filmCategoryToFilmCategoryDTO(FilmCategory filmCategory);

    List<FilmCategoryDTO> filmCategoriesToFilmCategoryDTOs(List<FilmCategory> filmCategories);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "filmId", target = "film")
    FilmCategory filmCategoryDTOToFilmCategory(FilmCategoryDTO filmCategoryDTO);

    List<FilmCategory> filmCategoryDTOsToFilmCategories(List<FilmCategoryDTO> filmCategoryDTOs);

    default Category categoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
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
