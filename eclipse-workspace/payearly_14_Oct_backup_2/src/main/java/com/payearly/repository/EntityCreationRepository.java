package com.payearly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.model.Process_Log;



@Repository
public interface EntityCreationRepository  extends JpaRepository<Process_Log, Integer>{

}
