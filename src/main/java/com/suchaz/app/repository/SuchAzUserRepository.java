package com.suchaz.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.SuchAzUser;

/**
 * Spring Data JPA repository for the SuchAzUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuchAzUserRepository extends JpaRepository<SuchAzUser, Long> {
    @Query("select distinct such_az_user from SuchAzUser such_az_user left join fetch such_az_user.traits")
    List<SuchAzUser> findAllWithEagerRelationships();

    @Query("select such_az_user from SuchAzUser such_az_user left join fetch such_az_user.traits where such_az_user.id =:id")
    SuchAzUser findOneWithEagerRelationships(@Param("id") Long id);

}
