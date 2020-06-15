package com.payearly.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payearly.domain.EntityDetail;
import com.payearly.domain.ProcessLog;
import com.payearly.enums.DeletionType;

@Repository
public interface EntityDetailRepository extends JpaRepository<EntityDetail, UUID> {

    Optional<EntityDetail> findBygstin(String gstin);

    Optional<EntityDetail> findByentityPan(String gstin);

    Optional<ProcessLog> findByGstin(String getin);

    EntityDetail findByentityName(String entityName);

    @Query(value = "select p.id, p.entity_name from entity_detail p ", nativeQuery = true)
    List<Object[]> findAllEntityname();

    @Query(value = "select gstin from entity_detail ", nativeQuery = true)
    List<Object> findAllEntitygstin();

	Page<EntityDetail> findBydeletionType(Pageable pageable, DeletionType deleted);

}
