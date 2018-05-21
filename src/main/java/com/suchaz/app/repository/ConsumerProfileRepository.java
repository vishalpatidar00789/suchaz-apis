package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ConsumerProfile;


/**
 * Spring Data JPA repository for the ConsumerProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsumerProfileRepository extends JpaRepository<ConsumerProfile, Long> {

	@Query("Select consumerProfile from ConsumerProfile consumerProfile where consumerProfile.suchAzUser.email like CONCAT(?1,'%') and isLoggedInUser = 'Y'")
	ConsumerProfile findOneWithSuchAzEmailId(String email);

	@Query("Select consumerProfile from ConsumerProfile consumerProfile where consumerProfile.suchAzUser.email like CONCAT(?1,'%') and isLoggedInUser = 'N'")
	ConsumerProfile findOneWithUniqueIdentifierForNonLoggedInUser(String userIdentifier);

}
