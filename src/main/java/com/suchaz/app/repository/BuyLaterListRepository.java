package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.BuyLaterList;


/**
 * Spring Data JPA repository for the BuyLaterList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyLaterListRepository extends JpaRepository<BuyLaterList, Long> {
	
	 @Query("SELECT t FROM BuyLaterList t where t.suchAzUser.id = :userId")
	    public BuyLaterList findBySuchAzUserId(@Param("userId") Long userId);

}
