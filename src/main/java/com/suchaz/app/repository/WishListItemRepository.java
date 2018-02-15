package com.suchaz.app.repository;

import com.suchaz.app.domain.WishListItem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the WishListItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {

}
