package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.domain.EntityDocuments;
import com.payearly.enums.DeletionType;

public interface EntityDocumentsRepository extends JpaRepository<EntityDocuments, UUID>{

    List<EntityDocuments> findAllByEntityDetail_id(UUID id);

	List<EntityDocuments> findBydeletionType(DeletionType live);

}
