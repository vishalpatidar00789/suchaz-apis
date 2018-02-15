package com.suchaz.app.repository;

import com.suchaz.app.domain.VendorImage;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the VendorImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorImageRepository extends JpaRepository<VendorImage, Long> {

}
