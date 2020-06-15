package com.payearly.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payearly.model.EntityDetails;
import com.payearly.model.ProcessLog;

public interface EntityDetailsReposoitory extends JpaRepository<EntityDetails, Long>{

	
EntityDetails findBygstin(String id);

//Optional<ProcessLog> findByGstin(String getin);

@Query(value = "select * from entity_details p where p.gstin = ?1 ", nativeQuery = true)
EntityDetails findByGstin(String getin);

Optional<EntityDetails>  findByentityPan(String pan);

EntityDetails findByentityName(String entityname);
@Query(value = "select gstin from entity_details ", nativeQuery = true)
List<Object> findAllEntitygstin();

}
