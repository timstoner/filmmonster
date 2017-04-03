package com.example.filmmonster.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the FilmText entity.
 */
public class FilmTextDTO implements Serializable {

    private Long id;

    private String title;

    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilmTextDTO filmTextDTO = (FilmTextDTO) o;

        if ( ! Objects.equals(id, filmTextDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FilmTextDTO{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
