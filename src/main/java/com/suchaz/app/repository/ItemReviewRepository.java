package com.suchaz.app.repository;

import com.suchaz.app.domain.ItemReview;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItemReview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemReviewRepository extends JpaRepository<ItemReview, Long> {

}
