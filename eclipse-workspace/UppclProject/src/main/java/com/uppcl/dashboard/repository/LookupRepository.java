package com.uppcl.dashboard.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.uppcl.dashboard.domain.LookUp;


public interface LookupRepository extends  JpaRepository<LookUp, Long> {

}
