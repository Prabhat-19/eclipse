package com.payearly.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "configurator_look_up_detail")
public class ConfiguratorLookUpDetail extends AbstractAuditingEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "category_type", nullable = false, length = 10)
    private String categoryType;

    @Column(name = "value", nullable = false, length = 50)
    private Float value;

    @Column(name = "discription" , length = 255, nullable = true)
    private String discription;
    
    @Column(name = "tenor" , length = 100, nullable = true)
    private String tenor;

    @ManyToOne()
    @JoinColumn(
            name = "configurator_look_up_id",
            nullable = false
    )
    private ConfiguratorLookUp configuratorLookUp;
    
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "entity_category_id")
    private EntityCategory entityCategory;
    
}
