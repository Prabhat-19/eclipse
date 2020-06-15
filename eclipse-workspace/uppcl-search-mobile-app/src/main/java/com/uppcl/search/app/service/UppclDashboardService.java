package com.uppcl.search.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.uppcl.search.app.domain.LookUp;
import com.uppcl.search.app.domain.LookupDetail;
import com.uppcl.search.app.dto.EmployeeDetailsDTO;
import com.uppcl.search.app.dto.MobileNumberDTO;



public interface UppclDashboardService {

	LookUp createLookup(LookUp lookupDetail);

	List<LookUp> getLookup();

	LookupDetail createLookupDetail(@Valid LookupDetail lookupDetail);

	List<LookupDetail> getLookupDetail();

	List<LookupDetail> getDetailByLookupId(int lookupId);

	List<LookupDetail> getChildDetail(int parentId);

	MobileNumberDTO verifyMobNo(@Valid String mobile);

	/*
	 * call for single value 
	 */
	List<Map<String,Object>> employeeDetailsFirstName(String firstName);
	
	List<Map<String,Object>> employeeDetailsLastName(String lastName);
	
	List<Map<String,Object>> employeeDetailsPost(String post);
	
	List<Map<String,Object>> employeeDetailsMobile(String Mobile);
	
	/*
	 * call for double value 
	 */
	List<Map<String,Object>> employeeDetailsfirstNameAndLastName(String firstName,String lastName);
	
	List<Map<String,Object>> employeeDetailsFirstNameAndPost(String firstName,String post);
	
	List<Map<String,Object>> employeeDetailsFirstNameAndMobile(String firstName,String mobile);
	
	List<Map<String,Object>> employeeDetailsLastNameAndPost(String lastName,String post);
	
	List<Map<String,Object>> employeeDetailsLastNameAndMobile(String lastName,String Mobile);
	
	List<Map<String,Object>> employeeDetailsMobileAndPost(String Mobile,String Post);
	
	
	/*
	 * call for three value 
	 */
	List<Map<String,Object>> employeeDetailsfirstNameAndLastNameAndPost(String firstName,String lastName,String post);
	
	List<Map<String,Object>> employeeDetailsfirstNameAndLastNameAndMobile(String firstName,String lastName,String mobile);
	
	List<Map<String,Object>> employeeDetailslastNameAndMobileAndPost(String lastName,String mobile,String post);
	
	List<Map<String,Object>> employeeDetailsFirstNameAndMobileAndPost(String lastName,String mobile,String post);
	

	
	/*
	 * call for four value 
	 */
	List<Map<String,Object>> employeeDetailsfirstNameAndLastNameAndMobileAndPost(String firstName,String lastName,String mobile,String post);

	List<Map<String,Object>> particularEmployeeDetail(int id);

	String uploadDocument(@Valid @NotNull @NotBlank @NotEmpty MultipartFile file);

	void loadDataFromExcell(String fileName) throws IOException;
}
