package com.payearly.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.model.EntityAddress;
import com.payearly.model.EntityDetails;

public interface EntityAddressRepository  extends JpaRepository<EntityAddress, Long>{

	List<EntityAddress> findAllByEntityDetails_id(Long id);


}
