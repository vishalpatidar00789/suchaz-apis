package com.suchaz.app.repository;

import com.suchaz.app.domain.Store;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Store entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("select distinct store from Store store left join fetch store.items")
    List<Store> findAllWithEagerRelationships();

    @Query("select store from Store store left join fetch store.items where store.id =:id")
    Store findOneWithEagerRelationships(@Param("id") Long id);

}
