package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.Trait;


/**
 * Spring Data JPA repository for the Trait entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraitRepository extends JpaRepository<Trait, Long> {

}
