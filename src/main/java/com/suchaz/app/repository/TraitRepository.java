package com.suchaz.app.repository;

import com.suchaz.app.domain.Trait;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Trait entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraitRepository extends JpaRepository<Trait, Long> {

}
