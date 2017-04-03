package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.FilmText;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the FilmText entity.
 */
public interface FilmTextSearchRepository extends ElasticsearchRepository<FilmText, Long> {
}
