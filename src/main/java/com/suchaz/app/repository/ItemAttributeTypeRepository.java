package com.suchaz.app.repository;

import com.suchaz.app.domain.ItemAttributeType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ItemAttributeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAttributeTypeRepository extends JpaRepository<ItemAttributeType, Long> {

}
