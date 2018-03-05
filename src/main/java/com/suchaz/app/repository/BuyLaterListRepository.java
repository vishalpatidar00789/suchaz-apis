package com.suchaz.app.repository;

import com.suchaz.app.domain.BuyLaterList;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the BuyLaterList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyLaterListRepository extends JpaRepository<BuyLaterList, Long> {
	
	 @Query("SELECT t FROM BuyLaterList t where t.suchAzUser.id = :userId")
	    public BuyLaterList findBySuchAzUserId(@Param("userId") Long userId);

}
