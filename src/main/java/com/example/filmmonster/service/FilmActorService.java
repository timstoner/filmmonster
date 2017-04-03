package com.example.filmmonster.service;

import com.example.filmmonster.domain.FilmActor;
import com.example.filmmonster.repository.FilmActorRepository;
import com.example.filmmonster.repository.search.FilmActorSearchRepository;
import com.example.filmmonster.service.dto.FilmActorDTO;
import com.example.filmmonster.service.mapper.FilmActorMapper;
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
 * Service Implementation for managing FilmActor.
 */
@Service
@Transactional
public class FilmActorService {

    private final Logger log = LoggerFactory.getLogger(FilmActorService.class);
    
    @Inject
    private FilmActorRepository filmActorRepository;

    @Inject
    private FilmActorMapper filmActorMapper;

    @Inject
    private FilmActorSearchRepository filmActorSearchRepository;

    /**
     * Save a filmActor.
     *
     * @param filmActorDTO the entity to save
     * @return the persisted entity
     */
    public FilmActorDTO save(FilmActorDTO filmActorDTO) {
        log.debug("Request to save FilmActor : {}", filmActorDTO);
        FilmActor filmActor = filmActorMapper.filmActorDTOToFilmActor(filmActorDTO);
        filmActor = filmActorRepository.save(filmActor);
        FilmActorDTO result = filmActorMapper.filmActorToFilmActorDTO(filmActor);
        filmActorSearchRepository.save(filmActor);
        return result;
    }

    /**
     *  Get all the filmActors.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<FilmActorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FilmActors");
        Page<FilmActor> result = filmActorRepository.findAll(pageable);
        return result.map(filmActor -> filmActorMapper.filmActorToFilmActorDTO(filmActor));
    }

    /**
     *  Get one filmActor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public FilmActorDTO findOne(Long id) {
        log.debug("Request to get FilmActor : {}", id);
        FilmActor filmActor = filmActorRepository.findOne(id);
        FilmActorDTO filmActorDTO = filmActorMapper.filmActorToFilmActorDTO(filmActor);
        return filmActorDTO;
    }

    /**
     *  Delete the  filmActor by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FilmActor : {}", id);
        filmActorRepository.delete(id);
        filmActorSearchRepository.delete(id);
    }

    /**
     * Search for the filmActor corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FilmActorDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FilmActors for query {}", query);
        Page<FilmActor> result = filmActorSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(filmActor -> filmActorMapper.filmActorToFilmActorDTO(filmActor));
    }
}
