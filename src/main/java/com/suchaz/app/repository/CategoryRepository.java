package com.suchaz.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.Category;


/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("Select category from Category category where category.parent.categoryCode =:categoryCode")
	List<Category> findAllByParentCategoryCode(String categoryCode);

}
