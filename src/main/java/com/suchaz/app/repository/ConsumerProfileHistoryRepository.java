package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ConsumerProfileHistory;


/**
 * Spring Data JPA repository for the ConsumerProfileHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumerProfileHistoryRepository extends JpaRepository<ConsumerProfileHistory, Long> {

}
