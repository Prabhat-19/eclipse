package com.payearly.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.GuaranteeType;
import com.payearly.enums.IntrestComputationType;

import lombok.Data;

@Data
@Entity
@Table(name = "entity_guarantee")
public class EntityGuarantee {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_guarantee", length = 20)
    private GuaranteeType entityGuarantee;

    @Column(name="value")
    private Float value; 

    @ManyToOne()
    @JoinColumn(name = "configurator_child")
    private ConfiguratorChild configuratorChild;

}
