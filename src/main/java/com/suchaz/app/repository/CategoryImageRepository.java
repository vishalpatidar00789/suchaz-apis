package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.CategoryImage;


/**
 * Spring Data JPA repository for the CategoryImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImage, Long> {

}
