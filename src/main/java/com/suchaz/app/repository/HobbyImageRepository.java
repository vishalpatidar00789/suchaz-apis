package com.suchaz.app.repository;

import com.suchaz.app.domain.HobbyImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the HobbyImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HobbyImageRepository extends JpaRepository<HobbyImage, Long> {

}
