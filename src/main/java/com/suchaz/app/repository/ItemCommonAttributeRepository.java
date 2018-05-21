package com.suchaz.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ItemCommonAttribute;


/**
 * Spring Data JPA repository for the ItemCommonAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemCommonAttributeRepository extends JpaRepository<ItemCommonAttribute, Long> {

	 @Query("select distinct itemCommonAttribute from ItemCommonAttribute itemCommonAttribute where itemCommonAttribute.item.id =:id")
	    List<ItemCommonAttribute> findItemCommonAttributeByItemId(@Param("id") Long id);
	 
	 @Query("select distinct itemCommonAttribute from ItemCommonAttribute itemCommonAttribute where itemCommonAttribute.isQuickViewEnabled = true AND itemCommonAttribute.item.id =:id")
	    List<ItemCommonAttribute> findItemCommonAttributeQuickViewEnabledForItem(@Param("id") Long id);
}
