package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.domain.AdditionalDetail;

public interface AdditionalDetailRepository extends JpaRepository<AdditionalDetail, UUID>{

    List<AdditionalDetail> findAllByEntityDetail_id(UUID id);

}
