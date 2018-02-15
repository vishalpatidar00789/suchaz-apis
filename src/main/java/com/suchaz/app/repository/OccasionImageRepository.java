package com.suchaz.app.repository;

import com.suchaz.app.domain.OccasionImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OccasionImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OccasionImageRepository extends JpaRepository<OccasionImage, Long> {

}
