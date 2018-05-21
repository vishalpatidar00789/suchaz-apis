package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.StoreImage;


/**
 * Spring Data JPA repository for the StoreImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {

}
