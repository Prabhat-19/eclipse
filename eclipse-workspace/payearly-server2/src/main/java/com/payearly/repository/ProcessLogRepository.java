package com.payearly.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.ProcessLog;

@Repository
public interface ProcessLogRepository extends JpaRepository<ProcessLog, UUID> {

	Optional<ProcessLog> findByEntityDetail_id(UUID gstnId);

//    Optional<ProcessLog> findBygstin(String gstin);
//
//    Optional<ProcessLog> findByentityPan(String gstin);

}
