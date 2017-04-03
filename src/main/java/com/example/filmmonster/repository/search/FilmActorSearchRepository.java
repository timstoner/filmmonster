package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.FilmActor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the FilmActor entity.
 */
public interface FilmActorSearchRepository extends ElasticsearchRepository<FilmActor, Long> {
}
