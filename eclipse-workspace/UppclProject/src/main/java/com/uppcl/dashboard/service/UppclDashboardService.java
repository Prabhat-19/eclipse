package com.uppcl.dashboard.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.uppcl.dashboard.domain.EmployeeOfficalDetail;
import com.uppcl.dashboard.domain.LookUp;
import com.uppcl.dashboard.domain.LookupDetail;
import com.uppcl.dashboard.dto.MobileNumberDTO;

public interface UppclDashboardService {

	LookUp createLookup(LookUp lookupDetail);

	List<LookUp> getLookup();

	LookupDetail createLookupDetail(@Valid LookupDetail lookupDetail);

	List<LookupDetail> getLookupDetail();

	List<LookupDetail> getDetailByLookupId(int lookupId);

	List<LookupDetail> getChildDetail(int parentId);

	MobileNumberDTO verifyMobNo(@Valid String mobile);

	List<Map<String, Object>> employeeDetails(String firstName);

	void loadDataFromExcell(String fileName) throws IOException;

	String uploadDocument(@Valid @NotNull @NotBlank @NotEmpty MultipartFile file);

	

}
