package com.suchaz.app.repository;

import com.suchaz.app.domain.ItemCommonAttribute;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItemCommonAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemCommonAttributeRepository extends JpaRepository<ItemCommonAttribute, Long> {

}
