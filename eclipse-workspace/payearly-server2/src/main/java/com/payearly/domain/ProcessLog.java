package com.payearly.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "process_log")
public class ProcessLog extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "process_instance_id", unique = true, nullable = false)
    private Integer ProcessInstanceId;

    @NotEmpty
    @Column(name = "process_id", nullable = false)
    private String processId;

    @Column(name = "parent_instance_id", nullable = false)
    private Integer parentInstanceId;

    @OneToOne
    @JoinColumn(name = "entity_detail_id")
    private EntityDetail entityDetail;

}
