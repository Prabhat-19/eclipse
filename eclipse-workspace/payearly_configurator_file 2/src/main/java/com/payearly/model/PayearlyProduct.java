package com.payearly.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "payearly_product")
public class PayearlyProduct {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "product", length = 100, unique = true)
    private String product;

    @Column(name = "product_code", length = 100, unique = true)
    private String productCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "configurator_master")
    private ConfiguratorMaster configuratorMaster;
}
