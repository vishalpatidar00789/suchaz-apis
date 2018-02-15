package com.suchaz.app.repository;

import com.suchaz.app.domain.RelationshipImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RelationshipImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelationshipImageRepository extends JpaRepository<RelationshipImage, Long> {

}
