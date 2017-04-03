package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.FilmCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the FilmCategory entity.
 */
public interface FilmCategorySearchRepository extends ElasticsearchRepository<FilmCategory, Long> {
}
