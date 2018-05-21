package com.suchaz.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.Store;

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
