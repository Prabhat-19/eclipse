package com.payearly.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.payearly.enums.DeletionType;

import lombok.Data;


@Data
@Entity
@Table(name = "entity_detail" , uniqueConstraints = {@UniqueConstraint(columnNames = {"gstin" , "entity_pan"}, name = "Uk_gstin_entity_pan")})
public class EntityDetail extends  AbstractAuditingEntity implements Serializable{

    private static final long serialVersionUID = 3862844434990016134L;

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Size(max = 50)
    @Column(name = "constitution", length = 50)
    private String constitution;

    @Size(max = 100)
    @Column(name = "entity_name", length = 100)
    private String entityName;

    @Size(max = 50)
    @Column(name = "entity_cin", length = 50)
    private String entityCin;

    @Size(max = 10)
    @Column(name = "entity_pan", length = 10, nullable = false, unique = true)
    private String entityPan;

    @Size(max = 200)
    @Column(name = "entity_website", length = 100)
    private String entityWebsite;

    @Size(max = 15)
    @Column(name = "gstin", length = 15, nullable = false, unique = true)
    private String gstin;

    @Column(name="deletion_type", length = 50)
    private DeletionType deletionType = DeletionType.LIVE; 
    
    @Size(max = 50)
    @Column(name = "entity_tin", length = 50)
    private String entityTin;

    @Size(max = 50)
    @Column(name = "nature_Of_Business", length = 50)
    private String natureOfBusiness;

    @Column(name = "flag", length = 50)
    private Integer flag;
    
    public void setFlag(Integer flag) {
        if (flag == 200) {
            this.flag = 1;
        } else if (flag == 400) {
            this.flag = 2;
        } else {
            this.flag = 3;
        }
    }

}
