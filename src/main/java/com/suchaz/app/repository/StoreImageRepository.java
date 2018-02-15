package com.suchaz.app.repository;

import com.suchaz.app.domain.StoreImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StoreImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {

}
