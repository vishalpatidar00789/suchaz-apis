package com.suchaz.app.repository;

import com.suchaz.app.domain.BuyLaterListItem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the BuyLaterListItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyLaterListItemRepository extends JpaRepository<BuyLaterListItem, Long> {
	
}
