package com.suchaz.app.repository;

import com.suchaz.app.domain.ConsumerProfile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConsumerProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumerProfileRepository extends JpaRepository<ConsumerProfile, Long> {

}
