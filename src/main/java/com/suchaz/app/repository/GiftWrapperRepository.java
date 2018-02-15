package com.suchaz.app.repository;

import com.suchaz.app.domain.GiftWrapper;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the GiftWrapper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiftWrapperRepository extends JpaRepository<GiftWrapper, Long> {

}
