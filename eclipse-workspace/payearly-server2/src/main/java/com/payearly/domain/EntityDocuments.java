package com.payearly.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payearly.enums.DeletionType;
import com.payearly.enums.DocumentType;
import lombok.Data;

@Data
@Entity
@Table(name = "entity_document")
public class EntityDocuments extends AbstractAuditingEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name="document_type", length = 20)
    private DocumentType documentType; 

    @Column(name="label", length = 20)
    private String label; 

    @Column(name="comment", length = 255)
    private String comment; 

    @Column(name="document_ref", length = 255)
    private String documentRef; 

    @Column(name="deletion_type", length = 50)
    private DeletionType deletionType = DeletionType.LIVE; 
    
    @ManyToOne()
    @JoinColumn(
            name = "entity_details_id"
    )
    private EntityDetail entityDetail;

}
