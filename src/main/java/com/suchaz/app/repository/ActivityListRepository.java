package com.suchaz.app.repository;

import com.suchaz.app.domain.ActivityList;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ActivityList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityListRepository extends JpaRepository<ActivityList, Long> {

}
