package com.payearly.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name = "entity_details")
public class EntityDetails {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name="gstin")
	private String gstin;
	
	@Column(name="entity_name")
	private String entityName;
	
	
	@Column(name="entity_pan")
	private String entityPan;
	
	@Column(name="entity_tin")
	private String entityTan;
	

	@Column(name="entity_cin")
	private String entityCin;
	
	
	@Column(name="entity_website")
	private String entityWebsite;
	
	@Column(name= "nature_Of_business")
	private String natureOfBusiness;
	

	@Column(name= "constitution")
	private String constitution;



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEntityName() {
		return entityName;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


	public String getEntityPan() {
		return entityPan;
	}


	public void setEntityPan(String entityPan) {
		this.entityPan = entityPan;
	}


	public String getEntityTan() {
		return entityTan;
	}


	public void setEntityTan(String entityTan) {
		this.entityTan = entityTan;
	}


	public String getEntityCin() {
		return entityCin;
	}


	public void setEntityCin(String entityCin) {
		this.entityCin = entityCin;
	}


	public String getEntityWebsite() {
		return entityWebsite;
	}


	public void setEntityWebsite(String entityWebsite) {
		this.entityWebsite = entityWebsite;
	}


	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}


	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}


	public String getConstitution() {
		return constitution;
	}


	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}


	public String getGstin() {
		return gstin;
	}


	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constitution == null) ? 0 : constitution.hashCode());
		result = prime * result + ((entityCin == null) ? 0 : entityCin.hashCode());
		result = prime * result + ((entityName == null) ? 0 : entityName.hashCode());
		result = prime * result + ((entityPan == null) ? 0 : entityPan.hashCode());
		result = prime * result + ((entityTan == null) ? 0 : entityTan.hashCode());
		result = prime * result + ((entityWebsite == null) ? 0 : entityWebsite.hashCode());
		result = prime * result + ((gstin == null) ? 0 : gstin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((natureOfBusiness == null) ? 0 : natureOfBusiness.hashCode());
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
		EntityDetails other = (EntityDetails) obj;
		if (constitution == null) {
			if (other.constitution != null)
				return false;
		} else if (!constitution.equals(other.constitution))
			return false;
		if (entityCin == null) {
			if (other.entityCin != null)
				return false;
		} else if (!entityCin.equals(other.entityCin))
			return false;
		if (entityName == null) {
			if (other.entityName != null)
				return false;
		} else if (!entityName.equals(other.entityName))
			return false;
		if (entityPan == null) {
			if (other.entityPan != null)
				return false;
		} else if (!entityPan.equals(other.entityPan))
			return false;
		if (entityTan == null) {
			if (other.entityTan != null)
				return false;
		} else if (!entityTan.equals(other.entityTan))
			return false;
		if (entityWebsite == null) {
			if (other.entityWebsite != null)
				return false;
		} else if (!entityWebsite.equals(other.entityWebsite))
			return false;
		if (gstin == null) {
			if (other.gstin != null)
				return false;
		} else if (!gstin.equals(other.gstin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (natureOfBusiness == null) {
			if (other.natureOfBusiness != null)
				return false;
		} else if (!natureOfBusiness.equals(other.natureOfBusiness))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EntityDetails [id=" + id + ", gstin=" + gstin + ", entityName=" + entityName + ", entityPan="
				+ entityPan + ", entityTan=" + entityTan + ", entityCin=" + entityCin + ", entityWebsite="
				+ entityWebsite + ", natureOfBusiness=" + natureOfBusiness + ", constitution=" + constitution + "]";
	}
	
	
}
