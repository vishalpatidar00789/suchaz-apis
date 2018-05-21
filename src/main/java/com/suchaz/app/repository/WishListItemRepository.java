package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.WishListItem;


/**
 * Spring Data JPA repository for the WishListItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {

}
