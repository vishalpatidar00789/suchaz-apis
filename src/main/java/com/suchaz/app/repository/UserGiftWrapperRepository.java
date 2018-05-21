package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.UserGiftWrapper;


/**
 * Spring Data JPA repository for the UserGiftWrapper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserGiftWrapperRepository extends JpaRepository<UserGiftWrapper, Long> {

}
