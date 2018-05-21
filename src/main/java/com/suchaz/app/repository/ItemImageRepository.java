package com.suchaz.app.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ItemImage;


/**
 * Spring Data JPA repository for the ItemImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {

	
	 @Query("SELECT t FROM ItemImage t where t.item.id = :itemId")
	    public ArrayList<ItemImage> findItemImagesByItemId(@Param("itemId") Long itemId);
}
