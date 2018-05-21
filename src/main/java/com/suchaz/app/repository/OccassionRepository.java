package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.Occassion;


/**
 * Spring Data JPA repository for the Occassion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OccassionRepository extends JpaRepository<Occassion, Long> {

}
