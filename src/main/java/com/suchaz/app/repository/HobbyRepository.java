package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.Hobby;


/**
 * Spring Data JPA repository for the Hobby entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {

}
