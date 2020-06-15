package com.uppcl.dashboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmployeeOfficalDetail")
public class EmployeeOfficalDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeOfficalDetailId;

	@Size(max = 100)
    @Column(name = "officialEmail")
    private String officialEmail;

    @Size(max = 36)
    //@Column(name = "mobile",unique=true)
    @Column(name = "discoIid")
    private String discoIid;

    @Size(max = 36)
    @Column(name = "zone")
    private String zone;
    
    @Size(max = 36)
    //@Column(name = "email",unique=true)
    @Column(name = "circle")
    private String circle;

    @Size(max = 36)
    @Column(name = "division")
    private String division;
    
    @Size(max = 36)
    @Column(name = "subdivision")
    private String subdivision;

    @Size(max = 36)
    @Column(name = "substation")
    private String substation;
    
    @Size(max = 36)
    @Column(name = "officialContact")
    private String officialContact;
    
    @Size(max = 36)
    //@Column(name = "email",unique=true)
    @Column(name = "post")
    private String post;

    @Size(max = 36)
    @Column(name = "reportingOffice")
    private String reportingOffice;
    
    @Size(max = 36)
    @Column(name = "teamId")
    private String teamId;
    
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "employeeDetailId", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private EmployeeDetail employeeDetail;

}
