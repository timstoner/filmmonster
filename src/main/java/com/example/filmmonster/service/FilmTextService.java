package com.example.filmmonster.service;

import com.example.filmmonster.domain.FilmText;
import com.example.filmmonster.repository.FilmTextRepository;
import com.example.filmmonster.repository.search.FilmTextSearchRepository;
import com.example.filmmonster.service.dto.FilmTextDTO;
import com.example.filmmonster.service.mapper.FilmTextMapper;
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
 * Service Implementation for managing FilmText.
 */
@Service
@Transactional
public class FilmTextService {

    private final Logger log = LoggerFactory.getLogger(FilmTextService.class);
    
    @Inject
    private FilmTextRepository filmTextRepository;

    @Inject
    private FilmTextMapper filmTextMapper;

    @Inject
    private FilmTextSearchRepository filmTextSearchRepository;

    /**
     * Save a filmText.
     *
     * @param filmTextDTO the entity to save
     * @return the persisted entity
     */
    public FilmTextDTO save(FilmTextDTO filmTextDTO) {
        log.debug("Request to save FilmText : {}", filmTextDTO);
        FilmText filmText = filmTextMapper.filmTextDTOToFilmText(filmTextDTO);
        filmText = filmTextRepository.save(filmText);
        FilmTextDTO result = filmTextMapper.filmTextToFilmTextDTO(filmText);
        filmTextSearchRepository.save(filmText);
        return result;
    }

    /**
     *  Get all the filmTexts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<FilmTextDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FilmTexts");
        Page<FilmText> result = filmTextRepository.findAll(pageable);
        return result.map(filmText -> filmTextMapper.filmTextToFilmTextDTO(filmText));
    }

    /**
     *  Get one filmText by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FilmTextDTO findOne(Long id) {
        log.debug("Request to get FilmText : {}", id);
        FilmText filmText = filmTextRepository.findOne(id);
        FilmTextDTO filmTextDTO = filmTextMapper.filmTextToFilmTextDTO(filmText);
        return filmTextDTO;
    }

    /**
     *  Delete the  filmText by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FilmText : {}", id);
        filmTextRepository.delete(id);
        filmTextSearchRepository.delete(id);
    }

    /**
     * Search for the filmText corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FilmTextDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FilmTexts for query {}", query);
        Page<FilmText> result = filmTextSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(filmText -> filmTextMapper.filmTextToFilmTextDTO(filmText));
    }
}
