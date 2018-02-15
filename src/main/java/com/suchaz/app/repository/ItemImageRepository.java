package com.suchaz.app.repository;

import com.suchaz.app.domain.ItemImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItemImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {

}
