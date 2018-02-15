package com.suchaz.app.repository;

import com.suchaz.app.domain.BuyLaterList;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the BuyLaterList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BuyLaterListRepository extends JpaRepository<BuyLaterList, Long> {

}
