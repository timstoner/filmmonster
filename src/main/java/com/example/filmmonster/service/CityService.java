package com.example.filmmonster.service;

import com.example.filmmonster.domain.City;
import com.example.filmmonster.repository.CityRepository;
import com.example.filmmonster.repository.search.CitySearchRepository;
import com.example.filmmonster.service.dto.CityDTO;
import com.example.filmmonster.service.mapper.CityMapper;
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
 * Service Implementation for managing City.
 */
@Service
@Transactional
public class CityService {

    private final Logger log = LoggerFactory.getLogger(CityService.class);
    
    @Inject
    private CityRepository cityRepository;

    @Inject
    private CityMapper cityMapper;

    @Inject
    private CitySearchRepository citySearchRepository;

    /**
     * Save a city.
     *
     * @param cityDTO the entity to save
     * @return the persisted entity
     */
    public CityDTO save(CityDTO cityDTO) {
        log.debug("Request to save City : {}", cityDTO);
        City city = cityMapper.cityDTOToCity(cityDTO);
        city = cityRepository.save(city);
        CityDTO result = cityMapper.cityToCityDTO(city);
        citySearchRepository.save(city);
        return result;
    }

    /**
     *  Get all the cities.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cities");
        Page<City> result = cityRepository.findAll(pageable);
        return result.map(city -> cityMapper.cityToCityDTO(city));
    }

    /**
     *  Get one city by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CityDTO findOne(Long id) {
        log.debug("Request to get City : {}", id);
        City city = cityRepository.findOne(id);
        CityDTO cityDTO = cityMapper.cityToCityDTO(city);
        return cityDTO;
    }

    /**
     *  Delete the  city by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        cityRepository.delete(id);
        citySearchRepository.delete(id);
    }

    /**
     * Search for the city corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CityDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Cities for query {}", query);
        Page<City> result = citySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(city -> cityMapper.cityToCityDTO(city));
    }
}
