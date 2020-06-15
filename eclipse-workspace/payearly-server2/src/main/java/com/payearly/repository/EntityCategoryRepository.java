package com.payearly.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.EntityCategory;

@Repository
public interface EntityCategoryRepository extends JpaRepository<EntityCategory, UUID>{

	Optional<EntityCategory> findBycategory(String category);

}
