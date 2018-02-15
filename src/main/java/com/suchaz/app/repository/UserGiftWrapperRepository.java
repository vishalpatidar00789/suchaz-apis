package com.suchaz.app.repository;

import com.suchaz.app.domain.UserGiftWrapper;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserGiftWrapper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserGiftWrapperRepository extends JpaRepository<UserGiftWrapper, Long> {

}
