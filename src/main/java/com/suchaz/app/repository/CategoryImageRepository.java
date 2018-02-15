package com.suchaz.app.repository;

import com.suchaz.app.domain.CategoryImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CategoryImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long> {

}
