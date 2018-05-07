package com.suchaz.app.repository;

import com.suchaz.app.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("Select category from Category category where category.parent.categoryCode =:categoryCode")
	List<Category> findAllByParentCategoryCode(String categoryCode);

}
