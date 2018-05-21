package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.HobbyImage;


/**
 * Spring Data JPA repository for the HobbyImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HobbyImageRepository extends JpaRepository<HobbyImage, Long> {

}
