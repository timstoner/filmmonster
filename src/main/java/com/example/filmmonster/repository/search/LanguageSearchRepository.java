package com.example.filmmonster.repository.search;

import com.example.filmmonster.domain.Language;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Language entity.
 */
public interface LanguageSearchRepository extends ElasticsearchRepository<Language, Long> {
}
