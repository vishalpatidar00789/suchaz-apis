package com.suchaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suchaz.app.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
