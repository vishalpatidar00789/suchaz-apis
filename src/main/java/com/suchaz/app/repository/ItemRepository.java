package com.suchaz.app.repository;

import com.suchaz.app.domain.Item;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Item entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select distinct item from Item item left join fetch item.offers")
    List<Item> findAllWithEagerRelationships();

    @Query("select item from Item item left join fetch item.offers where item.id =:id")
    Item findOneWithEagerRelationships(@Param("id") Long id);
    
    @Query("select item from Item item where item.category.categoryCode IN (:listCateogryCodes)")
    List<Item> findAllforCategory(String[] listCateogryCodes);
    
    @Query("select item.id from Item item where item.category.categoryCode IN (:listCateogryCodes)")
    List<Long> findAllItemsIdsforCategory(String[] listCateogryCodes);

}
