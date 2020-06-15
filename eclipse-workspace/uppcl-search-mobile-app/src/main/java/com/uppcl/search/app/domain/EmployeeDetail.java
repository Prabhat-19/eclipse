package com.uppcl.search.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmployeeDetail")
public class EmployeeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeDetailId;
	
	@Column(name="firstName",nullable = false,length=50)
    private String firstName;
    
    @Column(name = "lastName",nullable = false,length=50)
    private String lastName;

    @Column(name = "mobile",unique=true,nullable = false,length=10)
    private String mobile;

    @Column(name = "alternateNumber",nullable = true,length=10)
    private String alternateNumber;

    @Column(name = "email",nullable = true,length=100)
    private String email;

    @Column(name = "address",nullable = true,length=30)
    private String address;
    
    @Column(name = "city",nullable = true,length=30)
    private String city;
   
    @Column(name = "state",nullable = true,length=30)
    private String state;
    
    @Column(name = "district",nullable = true,length=30)
    private String district;
    
    @Column(name = "profilePicUrl",nullable = true,length=30)
    private String profilePicUrl;
}
