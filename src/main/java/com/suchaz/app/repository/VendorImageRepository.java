package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suchaz.app.domain.VendorImage;


/**
 * Spring Data JPA repository for the VendorImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorImageRepository extends JpaRepository<VendorImage, Long> {

}
