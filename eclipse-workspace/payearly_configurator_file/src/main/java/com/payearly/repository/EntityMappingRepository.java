package com.payearly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.model.EntityDetails;
import com.payearly.model.EntityMapping;

public interface EntityMappingRepository  extends JpaRepository<EntityMapping, Long>{

	List<EntityMapping> findByentityDetails_entityName(String entityName);

	List<EntityMapping> findByentityDetails_id(Long id);

	List<EntityMapping> findByEntityDetails_id(Long id);

}
