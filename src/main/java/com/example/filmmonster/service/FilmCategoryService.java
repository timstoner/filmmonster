package com.example.filmmonster.service;

import com.example.filmmonster.domain.FilmCategory;
import com.example.filmmonster.repository.FilmCategoryRepository;
import com.example.filmmonster.repository.search.FilmCategorySearchRepository;
import com.example.filmmonster.service.dto.FilmCategoryDTO;
import com.example.filmmonster.service.mapper.FilmCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FilmCategory.
 */
@Service
@Transactional
public class FilmCategoryService {

    private final Logger log = LoggerFactory.getLogger(FilmCategoryService.class);
    
    @Inject
    private FilmCategoryRepository filmCategoryRepository;

    @Inject
    private FilmCategoryMapper filmCategoryMapper;

    @Inject
    private FilmCategorySearchRepository filmCategorySearchRepository;

    /**
     * Save a filmCategory.
     *
     * @param filmCategoryDTO the entity to save
     * @return the persisted entity
     */
    public FilmCategoryDTO save(FilmCategoryDTO filmCategoryDTO) {
        log.debug("Request to save FilmCategory : {}", filmCategoryDTO);
        FilmCategory filmCategory = filmCategoryMapper.filmCategoryDTOToFilmCategory(filmCategoryDTO);
        filmCategory = filmCategoryRepository.save(filmCategory);
        FilmCategoryDTO result = filmCategoryMapper.filmCategoryToFilmCategoryDTO(filmCategory);
        filmCategorySearchRepository.save(filmCategory);
        return result;
    }

    /**
     *  Get all the filmCategories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<FilmCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FilmCategories");
        Page<FilmCategory> result = filmCategoryRepository.findAll(pageable);
        return result.map(filmCategory -> filmCategoryMapper.filmCategoryToFilmCategoryDTO(filmCategory));
    }

    /**
     *  Get one filmCategory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FilmCategoryDTO findOne(Long id) {
        log.debug("Request to get FilmCategory : {}", id);
        FilmCategory filmCategory = filmCategoryRepository.findOne(id);
        FilmCategoryDTO filmCategoryDTO = filmCategoryMapper.filmCategoryToFilmCategoryDTO(filmCategory);
        return filmCategoryDTO;
    }

    /**
     *  Delete the  filmCategory by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FilmCategory : {}", id);
        filmCategoryRepository.delete(id);
        filmCategorySearchRepository.delete(id);
    }

    /**
     * Search for the filmCategory corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FilmCategoryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FilmCategories for query {}", query);
        Page<FilmCategory> result = filmCategorySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(filmCategory -> filmCategoryMapper.filmCategoryToFilmCategoryDTO(filmCategory));
    }
}
