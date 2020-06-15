package com.payearly.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "entity_address")
public class EntityAddress {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "building_name")
    private String buildingName;
    
    @Column(name = "location")
	private String location;
	
    @Column(name = "street")
	private String street;
	
    @Column(name = "buildingNo")
	private String buildingNo;
	
    @Column(name = "state")
	private String state;
	
    @Column(name = "district")
	private String district;
	
    @Column(name = "city")
	private String city;
	
    @Column(name = "flateNo")
	private String flateNo;
	
    @Column(name = "pincode")
	private String pincode;
	
    @Column(name = "isPrimary")
	private Boolean isPrimary;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "entity_detail_id"
    )
    @JsonIgnore
    private EntityDetails entityDetails;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFlateNo() {
		return flateNo;
	}

	public void setFlateNo(String flateNo) {
		this.flateNo = flateNo;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
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
		result = prime * result + ((buildingName == null) ? 0 : buildingName.hashCode());
		result = prime * result + ((buildingNo == null) ? 0 : buildingNo.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((flateNo == null) ? 0 : flateNo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isPrimary == null) ? 0 : isPrimary.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		EntityAddress other = (EntityAddress) obj;
		if (buildingName == null) {
			if (other.buildingName != null)
				return false;
		} else if (!buildingName.equals(other.buildingName))
			return false;
		if (buildingNo == null) {
			if (other.buildingNo != null)
				return false;
		} else if (!buildingNo.equals(other.buildingNo))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (flateNo == null) {
			if (other.flateNo != null)
				return false;
		} else if (!flateNo.equals(other.flateNo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPrimary == null) {
			if (other.isPrimary != null)
				return false;
		} else if (!isPrimary.equals(other.isPrimary))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntityAddress [id=" + id + ", buildingName=" + buildingName + ", location=" + location + ", street="
				+ street + ", buildingNo=" + buildingNo + ", state=" + state + ", district=" + district + ", city="
				+ city + ", flateNo=" + flateNo + ", pincode=" + pincode + ", isPrimary=" + isPrimary + "]";
	}
	
	
}
