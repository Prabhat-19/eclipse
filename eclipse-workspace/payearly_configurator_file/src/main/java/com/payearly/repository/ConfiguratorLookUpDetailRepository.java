package com.payearly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.model.ConfiguratorLookUpDetail;

@Repository
public interface ConfiguratorLookUpDetailRepository extends  JpaRepository<ConfiguratorLookUpDetail, UUID> {

	List<ConfiguratorLookUpDetail> findByconfiguratorLookUp_id(UUID discountigNatureId);
}
