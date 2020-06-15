package com.payearly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.model.ConfiguratorLookUp;

@Repository
public interface ConfiguratorLookUpRepository  extends JpaRepository<ConfiguratorLookUp, UUID>{

	ConfiguratorLookUp findByname(String name);
	
}
