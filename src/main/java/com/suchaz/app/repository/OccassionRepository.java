package com.suchaz.app.repository;

import com.suchaz.app.domain.Occassion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Occassion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OccassionRepository extends JpaRepository<Occassion, Long> {

}
