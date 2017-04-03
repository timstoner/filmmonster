package com.example.filmmonster.service;

import com.example.filmmonster.domain.Rental;
import com.example.filmmonster.repository.RentalRepository;
import com.example.filmmonster.repository.search.RentalSearchRepository;
import com.example.filmmonster.service.dto.RentalDTO;
import com.example.filmmonster.service.mapper.RentalMapper;
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
 * Service Implementation for managing Rental.
 */
@Service
@Transactional
public class RentalService {

    private final Logger log = LoggerFactory.getLogger(RentalService.class);
    
    @Inject
    private RentalRepository rentalRepository;

    @Inject
    private RentalMapper rentalMapper;

    @Inject
    private RentalSearchRepository rentalSearchRepository;

    /**
     * Save a rental.
     *
     * @param rentalDTO the entity to save
     * @return the persisted entity
     */
    public RentalDTO save(RentalDTO rentalDTO) {
        log.debug("Request to save Rental : {}", rentalDTO);
        Rental rental = rentalMapper.rentalDTOToRental(rentalDTO);
        rental = rentalRepository.save(rental);
        RentalDTO result = rentalMapper.rentalToRentalDTO(rental);
        rentalSearchRepository.save(rental);
        return result;
    }

    /**
     *  Get all the rentals.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RentalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rentals");
        Page<Rental> result = rentalRepository.findAll(pageable);
        return result.map(rental -> rentalMapper.rentalToRentalDTO(rental));
    }

    /**
     *  Get one rental by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RentalDTO findOne(Long id) {
        log.debug("Request to get Rental : {}", id);
        Rental rental = rentalRepository.findOne(id);
        RentalDTO rentalDTO = rentalMapper.rentalToRentalDTO(rental);
        return rentalDTO;
    }

    /**
     *  Delete the  rental by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Rental : {}", id);
        rentalRepository.delete(id);
        rentalSearchRepository.delete(id);
    }

    /**
     * Search for the rental corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<RentalDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Rentals for query {}", query);
        Page<Rental> result = rentalSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(rental -> rentalMapper.rentalToRentalDTO(rental));
    }
}
