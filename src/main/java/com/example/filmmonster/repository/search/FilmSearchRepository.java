package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.Film;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Film entity.
 */
public interface FilmSearchRepository extends ElasticsearchRepository<Film, Long> {
}
