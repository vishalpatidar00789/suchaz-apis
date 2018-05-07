package com.suchaz.app.repository;

import com.suchaz.app.domain.ConsumerProfileHistory;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConsumerProfileHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumerProfileHistoryRepository extends JpaRepository<ConsumerProfileHistory, Long> {

}
