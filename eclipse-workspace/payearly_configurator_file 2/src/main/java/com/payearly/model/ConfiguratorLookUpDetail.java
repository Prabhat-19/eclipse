package com.payearly.model;

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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payearly.enums.CategoryType;

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

    @Column(name = "category_type", nullable = false, length = 200)
    private String categoryType;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "discription" , length = 255, nullable = true)
    private String discription;

    @ManyToOne
    @JoinColumn(
            name = "configurator_look_up_id",
            nullable = false
    )
    private ConfiguratorLookUp configuratorLookUp;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public ConfiguratorLookUp getConfiguratorLookUp() {
		return configuratorLookUp;
	}

	public void setConfiguratorLookUp(ConfiguratorLookUp configuratorLookUp) {
		this.configuratorLookUp = configuratorLookUp;
	}

	@Override
	public String toString() {
		return "ConfiguratorLookUpDetail [id=" + id + ", categoryType=" + categoryType + ", value=" + value
				+ ", discription=" + discription + ", configuratorLookUp=" + configuratorLookUp + "]";
	}

    
}
