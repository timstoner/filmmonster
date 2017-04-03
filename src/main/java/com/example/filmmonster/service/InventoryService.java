package com.example.filmmonster.service;

import com.example.filmmonster.domain.Inventory;
import com.example.filmmonster.repository.InventoryRepository;
import com.example.filmmonster.repository.search.InventorySearchRepository;
import com.example.filmmonster.service.dto.InventoryDTO;
import com.example.filmmonster.service.mapper.InventoryMapper;
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
 * Service Implementation for managing Inventory.
 */
@Service
@Transactional
public class InventoryService {

    private final Logger log = LoggerFactory.getLogger(InventoryService.class);
    
    @Inject
    private InventoryRepository inventoryRepository;

    @Inject
    private InventoryMapper inventoryMapper;

    @Inject
    private InventorySearchRepository inventorySearchRepository;

    /**
     * Save a inventory.
     *
     * @param inventoryDTO the entity to save
     * @return the persisted entity
     */
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        log.debug("Request to save Inventory : {}", inventoryDTO);
        Inventory inventory = inventoryMapper.inventoryDTOToInventory(inventoryDTO);
        inventory = inventoryRepository.save(inventory);
        InventoryDTO result = inventoryMapper.inventoryToInventoryDTO(inventory);
        inventorySearchRepository.save(inventory);
        return result;
    }

    /**
     *  Get all the inventories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<InventoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Inventories");
        Page<Inventory> result = inventoryRepository.findAll(pageable);
        return result.map(inventory -> inventoryMapper.inventoryToInventoryDTO(inventory));
    }

    /**
     *  Get one inventory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public InventoryDTO findOne(Long id) {
        log.debug("Request to get Inventory : {}", id);
        Inventory inventory = inventoryRepository.findOne(id);
        InventoryDTO inventoryDTO = inventoryMapper.inventoryToInventoryDTO(inventory);
        return inventoryDTO;
    }

    /**
     *  Delete the  inventory by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Inventory : {}", id);
        inventoryRepository.delete(id);
        inventorySearchRepository.delete(id);
    }

    /**
     * Search for the inventory corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<InventoryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Inventories for query {}", query);
        Page<Inventory> result = inventorySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(inventory -> inventoryMapper.inventoryToInventoryDTO(inventory));
    }
}
