package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.Staff;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Staff entity.
 */
public interface StaffSearchRepository extends ElasticsearchRepository<Staff, Long> {
}
