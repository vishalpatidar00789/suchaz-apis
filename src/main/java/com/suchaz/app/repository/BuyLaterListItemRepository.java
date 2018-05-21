package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.BuyLaterListItem;


/**
 * Spring Data JPA repository for the BuyLaterListItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyLaterListItemRepository extends JpaRepository<BuyLaterListItem, Long> {
	
}
