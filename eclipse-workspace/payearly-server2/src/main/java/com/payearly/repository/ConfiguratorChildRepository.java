package com.payearly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.ConfiguratorChild;

@Repository
public interface ConfiguratorChildRepository  extends JpaRepository<ConfiguratorChild, UUID>{

}
