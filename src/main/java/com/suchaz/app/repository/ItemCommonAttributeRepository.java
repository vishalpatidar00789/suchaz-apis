package com.suchaz.app.repository;

import com.suchaz.app.domain.Item;
import com.suchaz.app.domain.ItemCommonAttribute;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


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
