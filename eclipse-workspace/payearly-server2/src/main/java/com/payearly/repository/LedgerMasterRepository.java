package com.payearly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.domain.LedgerMaster;

public interface LedgerMasterRepository extends JpaRepository<LedgerMaster, UUID> {

}
