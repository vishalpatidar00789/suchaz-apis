package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ActivityList;


/**
 * Spring Data JPA repository for the ActivityList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityListRepository extends JpaRepository<ActivityList, Long> {
	
	 @Query("SELECT t FROM ActivityList t where t.suchAzUser.id = :userId")
	    public ActivityList findBySuchAzUserId(@Param("userId") Long userId);

}
