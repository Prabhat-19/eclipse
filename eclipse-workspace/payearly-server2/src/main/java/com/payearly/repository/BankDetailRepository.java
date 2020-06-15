package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.BankDetail;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetail, UUID>{
//   List<BankDetail> findByentityDetail_id(Long id);
	
	
	List<BankDetail> findAllByEntityDetail_id(UUID id);
}
