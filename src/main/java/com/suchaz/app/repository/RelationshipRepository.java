package com.suchaz.app.repository;

import com.suchaz.app.domain.Relationship;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Relationship entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

}
