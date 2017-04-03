package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.Actor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Actor entity.
 */
public interface ActorSearchRepository extends ElasticsearchRepository<Actor, Long> {
}
