package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.OccasionImage;


/**
 * Spring Data JPA repository for the OccasionImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OccasionImageRepository extends JpaRepository<OccasionImage, Long> {

}
