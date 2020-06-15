package com.payearly.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payearly.model.EntityDetails;

public interface EntityDetailsReposoitory extends JpaRepository<EntityDetails, Long>{

EntityDetails findBygstin(String id);

}
