package com.payearly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payearly.domain.ScoreCardChild;

@Repository
public interface ScoreCardChildRepository extends JpaRepository<ScoreCardChild, UUID> {

}
