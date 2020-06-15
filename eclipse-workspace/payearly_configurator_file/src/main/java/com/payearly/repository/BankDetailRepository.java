package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.payearly.model.BankDetail;

@Repository

public interface BankDetailRepository extends JpaRepository<BankDetail, Long>{

	@Query(value = "select * from bank_detail b where b.entity_detail_id = ?1 ", nativeQuery = true)
	List<BankDetail> findByEntityId(String id);
  // List<BankDetail> findByentityDetail_id(Long id);
	
	

//	@Modifying
//	@Query(value = "delete from bank_detail b where b.account_no = ?1 ", nativeQuery = true)
	//void deleteByaccountNoo(Long accountNumber);
	
	
   
}
