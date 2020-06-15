package com.payearly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payearly.model.ProcessLog;




@Repository 
public interface EntityCreationRepository  extends JpaRepository<ProcessLog, Integer>{
	
	Optional<ProcessLog> findByentityPAN(String pan);

	@Query(value="select * from process_log ORDER BY created_date DESC",nativeQuery = true)
	List<ProcessLog> findAllOrderBycreatedDateDesc();
	
	@Query(value = "select p.entity_name from process_log p ", nativeQuery = true)
    List<Object[]> findAllEntityname();
	
	@Query(value = "SELECT p.entity_name FROM process_log p WHERE p.entity_name <> ?1 "  , nativeQuery = true)
    List<Object[]> findAllByentityName(String name);

    @Query(value = "select * from process_log p where p.gstin = ?1 ", nativeQuery = true)
	//Optional<ProcessLog> findByGstin(String getin);
    ProcessLog findByGstin(String getin);
	
    @Query(value = "delete from process_log p where p.gstin = ?1", nativeQuery = true)
    ProcessLog deleteByGstin(String getin);
	
}