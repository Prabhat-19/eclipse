package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.domain.EntityMapping;

public interface EntityMappingRepository  extends JpaRepository<EntityMapping, UUID>{

	List<EntityMapping> findByentityDetail_entityName(String entityName);

	List<EntityMapping> findByEntityDetail_id(Long id);

    List<EntityMapping> findByEntityDetail_id(UUID id);

}
