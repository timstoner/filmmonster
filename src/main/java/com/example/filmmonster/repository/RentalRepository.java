package com.example.filmmonster.repository;

import com.example.filmmonster.domain.Rental;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Rental entity.
 */
@SuppressWarnings("unused")
public interface RentalRepository extends JpaRepository<Rental,Long> {

}
