package com.payearly.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.AddressType;

import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address extends AbstractAuditingEntity implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

	@Column(name = "building_name", length = 100)
    private String buildingName;
    
    @Column(name = "location" , length = 100)
	private String location;
	
    @Column(name = "street", length = 100)
	private String street;
	
    @Column(name = "building_no", length = 100)
	private String buildingNo;

    @Column(name = "state", length = 50)
	private String state;
	
    @Column(name = "district", length = 50)
    private String district;
	
    @Column(name = "city", length = 100)
    private String city;
	
    @Column(name = "flate_no",length = 50)
    private String flateNo;
	
    @Column(name = "pin_code",length = 6)
    private String pinCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", length = 100)
    private AddressType addressType;
    
    @ManyToOne()
    @JoinColumn(
            name = "entity_detail_id",
            nullable = false
    )
    
    private EntityDetail entityDetail;

}
