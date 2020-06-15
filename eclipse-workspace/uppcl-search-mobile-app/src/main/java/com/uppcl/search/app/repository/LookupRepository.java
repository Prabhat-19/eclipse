package com.uppcl.search.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.uppcl.search.app.domain.LookUp;
import com.uppcl.search.app.domain.LookupDetail;

public interface LookupRepository extends  JpaRepository<LookUp, Long> {
	
	@Query(value="select count(*) from lookup",nativeQuery = true)
	int checkRecord();


}
