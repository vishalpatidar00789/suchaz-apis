package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.RelationshipImage;


/**
 * Spring Data JPA repository for the RelationshipImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelationshipImageRepository extends JpaRepository<RelationshipImage, Long> {

}
