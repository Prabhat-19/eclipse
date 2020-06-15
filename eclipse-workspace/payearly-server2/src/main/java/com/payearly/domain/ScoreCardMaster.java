package com.payearly.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.ScoreCardCategoryType;

import lombok.Data;

@Data
@Entity
@Table(name = "score_card_master")
public class ScoreCardMaster extends AbstractAuditingEntity{

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Type(type = "uuid-char")
        @Column(name = "id", updatable = false, nullable = false)
        private UUID id;

        @Enumerated(EnumType.STRING)
        @Column(name = "score_card_category_type" , length = 15, nullable = false)
        private ScoreCardCategoryType ScoreCardCategoryType;

        @OneToOne
        @JoinColumn(name = "criteria_look_up_id")
        private ConfiguratorLookUp criteriaLookUp;

        @Column(name = "rejection_criteria" , length = 100, nullable = true)
        private String rejectionCriteria;
        
        @Column(name = "weightage" , length = 10, nullable = true)
        private String weightage;

        @Column(name = "document_proof" , length = 50, nullable = true)
        private String documentProof;

        @Column(name = "remarks" , length = 255, nullable = true)
        private String remarks;
        
        @Column(name = "question" , length = 255, nullable = false)
        private String question;

}
