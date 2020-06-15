package com.uppcl.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "createdBy", nullable = false, length = 30, updatable = false)
    @JsonIgnore
    private String createdBy = "User";

    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "modifiedBy", length = 50, nullable = true)
    @JsonIgnore
    private String modifiedBy="";

    @LastModifiedDate
    @Column(name = "modifiedDate")
    @JsonIgnore
    private Instant modifiedDate = Instant.now();

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return modifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.modifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return modifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.modifiedDate = lastModifiedDate;
    }
}
