package com.suchaz.app.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ItemReview;


/**
 * Spring Data JPA repository for the ItemReview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemReviewRepository extends JpaRepository<ItemReview, Long> {
	
	 @Query("SELECT t FROM ItemReview t where t.item.id = :itemId")
	    public ArrayList<ItemReview> findItemReviewByItemId(@Param("itemId") Long itemId);

}
