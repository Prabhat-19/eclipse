package com.payearly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.model.BankDetail;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetail, Long>{
   List<BankDetail> findByentityDetail_id(Long id);
}
