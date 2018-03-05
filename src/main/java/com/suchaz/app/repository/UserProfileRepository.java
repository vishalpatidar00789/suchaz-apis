package com.suchaz.app.repository;

import com.suchaz.app.domain.BuyLaterList;
import com.suchaz.app.domain.UserProfile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the UserProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	 @Query("SELECT t FROM UserProfile t where t.suchAzUser.id = :userId")
	    public UserProfile findBySuchAzUserId(@Param("userId") Long userId);

}
