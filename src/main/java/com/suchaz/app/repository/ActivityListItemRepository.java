package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ActivityListItem;


/**
 * Spring Data JPA repository for the ActivityListItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityListItemRepository extends JpaRepository<ActivityListItem, Long> {

}
