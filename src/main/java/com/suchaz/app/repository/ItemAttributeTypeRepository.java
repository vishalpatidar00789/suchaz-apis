package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.ItemAttributeType;


/**
 * Spring Data JPA repository for the ItemAttributeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAttributeTypeRepository extends JpaRepository<ItemAttributeType, Long> {

}
