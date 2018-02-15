package com.suchaz.app.repository;

import com.suchaz.app.domain.TraitImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TraitImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraitImageRepository extends JpaRepository<TraitImage, Long> {

}
