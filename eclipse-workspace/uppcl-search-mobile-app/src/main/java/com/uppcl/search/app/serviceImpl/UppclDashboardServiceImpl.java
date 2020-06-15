package com.uppcl.search.app.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.uppcl.search.app.domain.EmployeeDetail;
import com.uppcl.search.app.domain.EmployeeOfficalDetail;
import com.uppcl.search.app.domain.LookUp;
import com.uppcl.search.app.domain.LookupDetail;
import com.uppcl.search.app.dto.EmployeeDetailsDTO;
import com.uppcl.search.app.dto.MobileNumberDTO;
import com.uppcl.search.app.repository.EmployeeDetailRepo;
import com.uppcl.search.app.repository.EmployeeOfficialDetailRepo;
import com.uppcl.search.app.repository.LookupDetailRepository;
import com.uppcl.search.app.repository.LookupRepository;
import com.uppcl.search.app.service.UppclDashboardService;
import com.uppcl.search.app.util.CellValue;

@Service
@Transactional
public class UppclDashboardServiceImpl implements UppclDashboardService {

	private LookupDetailRepository lookupDetailRepo;

	private LookupRepository lookupRepo;

	private EmployeeDetailRepo empRepo;

	private EmployeeOfficialDetailRepo empDetailOffRepo;
	
	private final Logger log = LoggerFactory.getLogger(UppclDashboardServiceImpl.class);

	public UppclDashboardServiceImpl(LookupDetailRepository lookupDetailRepo, LookupRepository lookupRepo,
			EmployeeDetailRepo empRepo, EmployeeOfficialDetailRepo empDetailOffRepo) {
		super();
		this.lookupDetailRepo = lookupDetailRepo;
		this.lookupRepo = lookupRepo;
		this.empRepo = empRepo;
		this.empDetailOffRepo = empDetailOffRepo;
	}

	@Override
	public LookUp createLookup(LookUp lookupDetail) {
		lookupRepo.save(lookupDetail);
		return lookupDetail;

	}

	@Override
	public List<LookUp> getLookup() {

		return lookupRepo.findAll();
	}

	@Override
	public LookupDetail createLookupDetail(@Valid LookupDetail lookupDetail) {
		lookupDetailRepo.save(lookupDetail);
		return lookupDetail;
	}

	@Override
	public List<LookupDetail> getLookupDetail() {

		return lookupDetailRepo.findAll();
	}

	@Override
	public List<LookupDetail> getDetailByLookupId(int lookupId) {

		return lookupDetailRepo.findByLookupId(lookupId);
	}

	@Override
	public List<LookupDetail> getChildDetail(int parentId) {
		return lookupDetailRepo.findByParentId(parentId);
	}

	@Override
	public MobileNumberDTO verifyMobNo(@Valid String mobile) {
		int result = empRepo.verifyMobNo(mobile);
		MobileNumberDTO mobileNumberDTO = new MobileNumberDTO();

		if (result == 1) {
			mobileNumberDTO.setCheckMobileNumber("Present");
		} else {
			mobileNumberDTO.setCheckMobileNumber("Absent");
		}

		return mobileNumberDTO;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsFirstName(String firstName) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetailsFirstName(firstName);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsLastName(String lastName) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetailsLastName(lastName);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;

	}

	@Override
	public List<Map<String, Object>> employeeDetailsPost(String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetailsPost(post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsMobile(String mobile) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetailsMobileNumber(mobile);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;

	}

	@Override
	public List<Map<String, Object>> employeeDetailsfirstNameAndLastName(String firstName, String lastName) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndLastName(firstName, lastName);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsFirstNameAndPost(String firstName, String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndPost(firstName, post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsFirstNameAndMobile(String firstName, String mobile) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndMobile(firstName, mobile);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsLastNameAndPost(String lastName, String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetailsLastNameAndPost(lastName,
				post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsLastNameAndMobile(String lastName, String mobile) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsLastNameAndMobile(lastName, mobile);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsMobileAndPost(String mobile, String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetailsMobileAndPost(mobile,
				post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsfirstNameAndLastNameAndPost(String firstName, String lastName,
			String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndLastNameAndPost(firstName, lastName, post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsFirstNameAndMobileAndPost(String firstName, String mobile,
			String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndMobileAndPost(firstName, mobile, post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsfirstNameAndLastNameAndMobile(String firstName, String lastName,
			String mobile) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndLastNameAndMobile(firstName, lastName, mobile);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailslastNameAndMobileAndPost(String lastName, String mobile,
			String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsLastNameAndPostAndMobile(lastName, mobile, post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> employeeDetailsfirstNameAndLastNameAndMobileAndPost(String firstName,
			String lastName, String mobile, String post) {
		String value1 = null;
		List<String> list = new ArrayList<>();
		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo
				.findEmployeeDetailsFirstNameAndLastNameAndMobileAndPost(firstName, lastName, mobile, post);

		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();
			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = lookupDetailRepo.getValue(postValue);
			list.add(value1);
			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			mapObj.put("employeeDetailId", map.get("employee_detail_id"));
			mapObj.put("teamId", map.get("team_id"));
			mapObj.put("officialEmail", map.get("official_email"));
			mapObj.put("offficialMobile", map.get("official_contact"));
			listObj.add(mapObj);
		}
		return listObj;
	}

	@Override
	public List<Map<String, Object>> particularEmployeeDetail(int id) {

		return null;
	}

	@Override
	public String uploadDocument(@Valid @NotNull @NotBlank @NotEmpty MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		Path fileStorageLocation = Paths.get("/root/ReadFile/").toAbsolutePath().normalize();
		File directory = new File("/root/ReadFile/");
		if (directory.exists()) {

			try {
				Path targetLocation = fileStorageLocation.resolve(fileName);
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {

				e.printStackTrace();
			}
			return fileStorageLocation.toString().concat("/").concat(fileName);

		}

		else {
			try {
				Files.createDirectories(fileStorageLocation);
				Path targetLocation = fileStorageLocation.resolve(fileName);
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String loc = fileStorageLocation.toString().concat("/").concat(fileName);
			return fileName + " uploadad at location " + loc;
		}
	}

	@Override
	public void loadDataFromExcell(String fileName) throws IOException {
		FileInputStream file = new FileInputStream(new File("/Users/prabhat/Excel/ReadFile/" + fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		String subdivisionId = null;

		// Get first sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		

		String[] LookupArr = { "DISCOM", "ZONE", "CIRCLE", "DIVISION", "SUBDIVISION", "SUBSTATION", "POST" };

		if (lookupRepo.checkRecord() == 0) {
			for (int i = 0; i < LookupArr.length; i++) {
				LookUp lookup = new LookUp();
				lookup.setName(LookupArr[i]);
				lookupRepo.save(lookup);
				

			}
		} else {
			log.info("Record found in Lookup table");
	
		}

		String[] postNames = { "ASSISTANT ENGINEER", "CHAIRMAN", "CHEIF ENGINEER", "CUSTOMER CARE EXECUTIVE",
				"DIRECTOR", "DIVISIONAL ACCOUNTANT(REV)", "EXECUTIVE ENGINEER", "JUNIOR ENGINEER", "MANAGING DIRECTOR",
				"SUPRETENDING ENGINEER" };

		if (lookupDetailRepo.checkRecord() == 0) {
			for (int i = 0; i < postNames.length; i++) {
				LookupDetail lookupDetail = new LookupDetail();
				lookupDetail.setName(postNames[i]);
				lookupDetail.setLookUpId(new LookUp((long) 7, "POST", null));
				lookupDetailRepo.save(lookupDetail);
			}
		} else {
			log.info("Record found in LookupDetail table");
		}

		
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

			XSSFRow row = sheet.getRow(i);
			String discom = (String) CellValue.printCellValue(row.getCell(0));

			String zone = (String) CellValue.printCellValue(row.getCell(1));

			String circle = (String) CellValue.printCellValue(row.getCell(2));

			String division = (String) CellValue.printCellValue(row.getCell(3));

			String subdivision = (String) CellValue.printCellValue(row.getCell(4));

			String substation = (String) CellValue.printCellValue(row.getCell(5));

			DataFormatter formatter = new DataFormatter();
			String phone = formatter.formatCellValue(row.getCell(6));
			String name = (String) CellValue.printCellValue(row.getCell(7));

			String email = (String) CellValue.printCellValue(row.getCell(8));

			
			
			if (!discom.equalsIgnoreCase(lookupDetailRepo.checkName(discom)) && !discom.equalsIgnoreCase("") ) {
				LookupDetail ld = new LookupDetail();
				ld.setName(discom);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(null);
				ld.setLookUpId(new LookUp((long) 1, "DISCOM", "NULL"));
				lookupDetailRepo.save(ld);
			} else if(discom.equalsIgnoreCase("") )
			{
				log.info("discom field is blank");
			}
			
			else {
				log.info("discom already present");
			}
			

			if (!zone.equalsIgnoreCase(lookupDetailRepo.checkName(zone)) && !zone.equalsIgnoreCase("") ) {
				log.info("zone ==>" );
				log.info("zone ==>" +zone);
				String parentId = lookupDetailRepo.getId(discom);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(zone);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 2, "ZONE", "NULL"));
				lookupDetailRepo.save(ld);
			} 
			else if(zone.equalsIgnoreCase("") )
			{
				log.info("Zone field is blank");
			}
			
			else {
				log.info("Zone already present");
			}
			
			
			if (!circle.equalsIgnoreCase(lookupDetailRepo.checkName(circle)) && !circle.equalsIgnoreCase("") ) {
				log.info("circle ==>");
				log.info("circle ==>" +circle);
				String parentId = lookupDetailRepo.getId(zone);
				LookupDetail ld = new LookupDetail();

				ld.setName(circle);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 3, "CIRCLE", "NULL"));
				lookupDetailRepo.save(ld);
			}else  if(circle.equalsIgnoreCase(""))
			{
				log.info("circle value is blank");
			}
			else {
				log.info("Circle already present");
			}

			if (!division.equalsIgnoreCase(lookupDetailRepo.checkName(division))  && !division.equalsIgnoreCase("") ) {
				log.info("division ==>");
				log.info("division ==>" +division);
				String parentId = lookupDetailRepo.getId(circle);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(division);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 4, "DIVISION", "NULL"));
				lookupDetailRepo.save(ld);
			}
			else if( division.equalsIgnoreCase("") )
			{
				log.info("Division field is blank");
			}
			
			
			else {
				log.info("Division already present");
			}



			if (lookupDetailRepo.checkSubDiv(subdivision) == 0 &&  !subdivision.equalsIgnoreCase("") ) {
				log.info("subdivision ==>" );
				log.info("subdivision ==>" +subdivision);
				String parentId = lookupDetailRepo.getId(division);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(subdivision);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 5, "SUBDIVISION", "NULL"));

				lookupDetailRepo.save(ld);
				log.info("New subdivision added");
				subdivisionId = lookupDetailRepo.getId(subdivision);

			} else if (lookupDetailRepo.checkSubDiv(subdivision) == 1 && !subdivision.equalsIgnoreCase("")) {
				log.info("subdivision copy ==>" );
				log.info("subdivsion" + subdivision+"already present");
				String parentId = lookupDetailRepo.getId(division);
				
				LookupDetail ld = new LookupDetail();

				ld.setName(subdivision);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 5, "SUBDIVISION", "NULL"));
				lookupDetailRepo.save(ld);
				log.info("printttting last inserted Id ===> " + lookupDetailRepo.getLastInsertedtId());
				log.info("another copy of subdivision added");
				subdivisionId = lookupDetailRepo.getLastInsertedtId();
			}
			else if(subdivision.equalsIgnoreCase(""))
			{
				log.info("subdivision field is blank");
			}
			
			log.info("no of rows "+sheet.getPhysicalNumberOfRows());

			if (!substation.equalsIgnoreCase(lookupDetailRepo.checkName(substation)) && !substation.equalsIgnoreCase("")) {
				log.info("substation ==>" );
				log.info("substation ==>" +substation);
				String parentId = lookupDetailRepo.getLastInsertedtId();
				LookupDetail ld = new LookupDetail();

				ld.setName(substation);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 6, "SUBSTATION", "NULL"));
				lookupDetailRepo.save(ld);
			} else if(substation.equalsIgnoreCase(""))
			{
				log.info("Substation field is blank");
			}
			else
			
			{
				log.info("SUBSTATION already present");
			}
			String fname1;
			String fname2;
			String fname;
			String lname;
			String[] nameArr = name.split(" ");
			if (nameArr.length == 3) {

				fname1 = nameArr[0];
				fname2 = nameArr[1];
				fname = fname1 + " " + fname2;

				lname = nameArr[2];
				log.info("fname ===> " + fname);
				log.info("lname ==> " + lname);
				
			} else if (nameArr.length == 2) {
				fname = nameArr[0];
				lname = nameArr[1];
			} else {
				fname = name;
				lname = "";
			}

			// Need to modify
			log.info("Setting post value");
			String post = "1";
	
			EmployeeDetail emp = new EmployeeDetail();
			emp.setFirstName(fname);
			emp.setLastName(lname);
			emp.setMobile(phone);
			emp.setEmail(email);
			

			if (empRepo.verifyEmail(email) == 0) {
				empRepo.save(emp);
				log.info("Emp detail saved");
				

				String discomId = lookupDetailRepo.getId(discom);
				String zoneId = lookupDetailRepo.getId(zone);
				String circleId = lookupDetailRepo.getId(circle);
				String divisionId = lookupDetailRepo.getId(division);
				String substationId = lookupDetailRepo.getId(substation);

				EmployeeOfficalDetail empOfficial = new EmployeeOfficalDetail();
				long empId = empRepo.findEmpId(phone);
				emp.setEmployeeDetailId(empId);
				empOfficial.setDiscoIid(discomId);
				empOfficial.setZone(zoneId);
				empOfficial.setCircle(circleId);
				empOfficial.setDivision(divisionId);
				empOfficial.setSubdivision(subdivisionId);
				empOfficial.setSubstation(substationId);
				empOfficial.setOfficialEmail(email);
				empOfficial.setPost(post);
				empOfficial.setEmployeeDetail(emp);
				empOfficial.setOfficialContact(phone);
			
				empDetailOffRepo.save(empOfficial);
				log.info("saving empOfficialDetailRepo dtails" + empOfficial);
				
				
			} else {
				System.out.println("Emp record found");

			}

		}
		file.close();
		

	}

}
