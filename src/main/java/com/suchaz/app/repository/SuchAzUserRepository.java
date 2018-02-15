package com.suchaz.app.repository;

import com.suchaz.app.domain.SuchAzUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

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
