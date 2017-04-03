package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.Inventory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Inventory entity.
 */
public interface InventorySearchRepository extends ElasticsearchRepository<Inventory, Long> {
}
