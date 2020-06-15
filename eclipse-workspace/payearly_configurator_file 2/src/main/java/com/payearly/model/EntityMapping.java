package com.payearly.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "entity_mapping")
public class EntityMapping {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name="product", length = 20)
	private String product; 
	
	@Column(name="entityid", length = 20)
	private String entityid; 
	
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "entity_details_id"
    )
    @JsonIgnore
    private EntityDetails entityDetails;
	
	

	public String getEntityid() {
		return entityid;
	}


	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public EntityDetails getEntityDetails() {
		return entityDetails;
	}


	public void setEntityDetails(EntityDetails entityDetails) {
		this.entityDetails = entityDetails;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityDetails == null) ? 0 : entityDetails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityMapping other = (EntityMapping) obj;
		if (entityDetails == null) {
			if (other.entityDetails != null)
				return false;
		} else if (!entityDetails.equals(other.entityDetails))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
    
    
    
    
}
