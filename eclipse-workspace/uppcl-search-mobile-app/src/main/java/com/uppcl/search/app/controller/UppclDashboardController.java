package com.uppcl.search.app.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uppcl.search.app.domain.LookUp;
import com.uppcl.search.app.domain.LookupDetail;
import com.uppcl.search.app.dto.MobileNumberDTO;
import com.uppcl.search.app.error.RecordNotFoundException;
import com.uppcl.search.app.service.UppclDashboardService;
import com.uppcl.search.app.config.Constants;

@RestController
@RequestMapping("api/details")
@CrossOrigin(origins = Constants.API_URL)

public class UppclDashboardController {

	private UppclDashboardService uppclService;

	public UppclDashboardController(UppclDashboardService uppclService) {
		super();
		this.uppclService = uppclService;
	}

	@PostMapping("/lookUp")
	public ResponseEntity<LookUp> creatlookUp(@Valid @RequestBody LookUp lookup) throws InputMismatchException {

		LookUp lookUpData = uppclService.createLookup(lookup);
		return new ResponseEntity<LookUp>(lookUpData, HttpStatus.OK);
	}

	@GetMapping("/getLookup")
	public ResponseEntity<List<LookUp>> getLookup() {

		List<LookUp> lookup = uppclService.getLookup();
		if (lookup.size() == 0) {
			throw new RecordNotFoundException("Record not found");
		}
		return new ResponseEntity<List<LookUp>>(lookup, HttpStatus.OK);

	}

	@PostMapping("/lookUpDetail")
	public ResponseEntity<LookupDetail> creatlookUpDetail(@Valid @RequestBody LookupDetail lookupDetail)
			throws InputMismatchException {

		LookupDetail lookUpDetailData = uppclService.createLookupDetail(lookupDetail);
		return new ResponseEntity<LookupDetail>(lookUpDetailData, HttpStatus.OK);
	}

	@GetMapping("/getLookupDetailAll")
	public ResponseEntity<List<LookupDetail>> getLookupDetail() {

		List<LookupDetail> lookupDetail = uppclService.getLookupDetail();
		if (lookupDetail.size() == 0) {
			throw new RecordNotFoundException("Record not found");
		}
		return new ResponseEntity<List<LookupDetail>>(lookupDetail, HttpStatus.OK);

	}

	@GetMapping("/getDetailByLookupId")
	public ResponseEntity<List<LookupDetail>> getDetailByLookupId(@RequestParam int lookupId) {

		List<LookupDetail> lookupDetail = uppclService.getDetailByLookupId(lookupId);
		if (lookupDetail.size() == 0) {
			throw new RecordNotFoundException("Record not found");
		}
		return new ResponseEntity<List<LookupDetail>>(lookupDetail, HttpStatus.OK);

	}

	@GetMapping("/getChildDetail")
	public ResponseEntity<List<LookupDetail>> getChildDetail(@RequestParam int parentId) {

		List<LookupDetail> lookupDetail = uppclService.getChildDetail(parentId);
		if (lookupDetail.size() == 0) {
			throw new RecordNotFoundException("Record not found");
		}
		return new ResponseEntity<List<LookupDetail>>(lookupDetail, HttpStatus.OK);

	}

	@GetMapping("/verifyMobNo")
	public MobileNumberDTO verifyMobNumber(@Valid @RequestParam String mobile) {

		MobileNumberDTO message = uppclService.verifyMobNo(mobile);

		return message;

	}

	@GetMapping("/employee/details")
	public List<Map<String, Object>> details(@RequestParam(defaultValue = "") String firstName,
			@RequestParam(defaultValue = "") String lastName, @RequestParam(defaultValue = "") String post,
			@RequestParam(defaultValue = "") String mobile) {

		if (!firstName.equals("") && lastName.equals("") && post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsFirstName(firstName);
			return emp;
		} else if (firstName.equals("") && !lastName.equals("") && post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsLastName(lastName);
			return emp;
		} else if (firstName.equals("") && lastName.equals("") && !post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsPost(post);
			return emp;
		} else if (firstName.equals("") && lastName.equals("") && post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsMobile(mobile);
			return emp;
		} else if (!firstName.equals("") && !lastName.equals("") && post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsfirstNameAndLastName(firstName, lastName);
			return emp;
		} else if (!firstName.equals("") && lastName.equals("") && !post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsFirstNameAndPost(firstName, post);
			return emp;
		} else if (!firstName.equals("") && lastName.equals("") && post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsFirstNameAndMobile(firstName, mobile);
			return emp;
		} else if (firstName.equals("") && !lastName.equals("") && !post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsLastNameAndPost(lastName, post);
			return emp;
		}

		else if (firstName.equals("") && !lastName.equals("") && post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsLastNameAndMobile(lastName, mobile);
			return emp;
		}

		else if (firstName.equals("") && lastName.equals("") && !post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsMobileAndPost(mobile, post);
			return emp;
		} else if (!firstName.equals("") && !lastName.equals("") && !post.equals("") && mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsfirstNameAndLastNameAndPost(firstName, lastName,
					post);
			return emp;
		} else if (!firstName.equals("") && !lastName.equals("") && post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsfirstNameAndLastNameAndMobile(firstName,
					lastName, mobile);
			return emp;
		} else if (firstName.equals("") && !lastName.equals("") && !post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailslastNameAndMobileAndPost(lastName, mobile,
					post);
			return emp;
		} else if (!firstName.equals("") && lastName.equals("") && !post.equals("") && !mobile.equals("")) {
			List<Map<String, Object>> emp = uppclService.employeeDetailsFirstNameAndMobileAndPost(firstName, mobile,
					post);
			return emp;
		}

		else {
			List<Map<String, Object>> emp = uppclService.employeeDetailsfirstNameAndLastNameAndMobileAndPost(firstName,
					lastName, mobile, post);
			return emp;
		}

	}

	@PostMapping("/fileupload")
	public ResponseEntity<String> DocumentUpload(@Valid @NotNull @NotBlank @NotEmpty @RequestBody MultipartFile file) {
		String string = uppclService.uploadDocument(file);
		return ResponseEntity.ok(string);
	}

	@PostMapping("/loadDataFromExcell")
	public void loadDataFromExcell(@RequestParam String fileName) throws IOException {
		uppclService.loadDataFromExcell(fileName);

	}

}
