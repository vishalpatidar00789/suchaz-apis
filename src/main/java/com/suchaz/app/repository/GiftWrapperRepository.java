package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.GiftWrapper;


/**
 * Spring Data JPA repository for the GiftWrapper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiftWrapperRepository extends JpaRepository<GiftWrapper, Long> {

}
