package com.suchaz.app.repository;

import com.suchaz.app.domain.Vendor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Vendor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
