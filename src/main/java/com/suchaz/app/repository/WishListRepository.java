package com.suchaz.app.repository;

import com.suchaz.app.domain.WishList;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the WishList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

}
