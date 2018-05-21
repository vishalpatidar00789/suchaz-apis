package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.TraitImage;


/**
 * Spring Data JPA repository for the TraitImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraitImageRepository extends JpaRepository<TraitImage, Long> {

}
