package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.Rental;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Rental entity.
 */
public interface RentalSearchRepository extends ElasticsearchRepository<Rental, Long> {
}
