package com.suchaz.app.repository;

import com.suchaz.app.domain.ActivityListItem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ActivityListItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityListItemRepository extends JpaRepository<ActivityListItem, Long> {

}
