package com.payearly.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payearly.enums.DeletionType;

import lombok.Data;

@Data
@Entity
@Table(name = "ledger_master")
public class LedgerMaster  extends AbstractAuditingEntity{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", length = 200)
    private String name;

    @Size(max = 100)
    @Column(name = "file_name", length = 200)
    private String fileName;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "deletion_type", length = 50)
    private DeletionType deletionType = DeletionType.LIVE;

    @Size(max = 100)
    @Column(name = "file_path", length = 100)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "entity_detail_id")
    private EntityDetail entityDetail;

    @Column(name = "credit_period", length = 222)
    private String creditPeriod;
}
