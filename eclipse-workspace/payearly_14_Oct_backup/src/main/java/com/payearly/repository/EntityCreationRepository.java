package com.payearly.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.model.Process_Log;



@Repository
public interface EntityCreationRepository  extends JpaRepository<Process_Log, Integer>{
	
	
	  Optional<Process_Log> findByentityPAN(String pan);
	  
	  
	  


}
