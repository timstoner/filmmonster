package com.example.filmmonster.service;

import com.example.filmmonster.domain.Staff;
import com.example.filmmonster.repository.StaffRepository;
import com.example.filmmonster.repository.search.StaffSearchRepository;
import com.example.filmmonster.service.dto.StaffDTO;
import com.example.filmmonster.service.mapper.StaffMapper;
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
 * Service Implementation for managing Staff.
 */
@Service
@Transactional
public class StaffService {

    private final Logger log = LoggerFactory.getLogger(StaffService.class);
    
    @Inject
    private StaffRepository staffRepository;

    @Inject
    private StaffMapper staffMapper;

    @Inject
    private StaffSearchRepository staffSearchRepository;

    /**
     * Save a staff.
     *
     * @param staffDTO the entity to save
     * @return the persisted entity
     */
    public StaffDTO save(StaffDTO staffDTO) {
        log.debug("Request to save Staff : {}", staffDTO);
        Staff staff = staffMapper.staffDTOToStaff(staffDTO);
        staff = staffRepository.save(staff);
        StaffDTO result = staffMapper.staffToStaffDTO(staff);
        staffSearchRepository.save(staff);
        return result;
    }

    /**
     *  Get all the staff.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<StaffDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Staff");
        Page<Staff> result = staffRepository.findAll(pageable);
        return result.map(staff -> staffMapper.staffToStaffDTO(staff));
    }

    /**
     *  Get one staff by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public StaffDTO findOne(Long id) {
        log.debug("Request to get Staff : {}", id);
        Staff staff = staffRepository.findOne(id);
        StaffDTO staffDTO = staffMapper.staffToStaffDTO(staff);
        return staffDTO;
    }

    /**
     *  Delete the  staff by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Staff : {}", id);
        staffRepository.delete(id);
        staffSearchRepository.delete(id);
    }

    /**
     * Search for the staff corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<StaffDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Staff for query {}", query);
        Page<Staff> result = staffSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(staff -> staffMapper.staffToStaffDTO(staff));
    }
}
