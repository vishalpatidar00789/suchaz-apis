package com.suchaz.app.repository;

import com.suchaz.app.domain.Hobby;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Hobby entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {

}
