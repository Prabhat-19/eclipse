package com.uppcl.dashboard.serviceImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.uppcl.dashboard.domain.EmployeeDetail;
import com.uppcl.dashboard.domain.EmployeeOfficalDetail;
import com.uppcl.dashboard.domain.LookUp;
import com.uppcl.dashboard.domain.LookupDetail;
import com.uppcl.dashboard.dto.MobileNumberDTO;
import com.uppcl.dashboard.repository.EmployeeDetailRepo;
import com.uppcl.dashboard.repository.EmployeeOfficialDetailRepo;
import com.uppcl.dashboard.repository.LookupDetailRepository;
import com.uppcl.dashboard.repository.LookupRepository;
import com.uppcl.dashboard.service.UppclDashboardService;
import com.uppcl.dashboard.util.CellValue;

@Service
@Transactional
public class UppclDashboardServiceImpl implements UppclDashboardService {

	private LookupDetailRepository lookupDetailRepo;

	private LookupRepository lookupRepo;

	private EmployeeDetailRepo empRepo;

	private EmployeeOfficialDetailRepo empDetailOffRepo;

	

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
	public List<Map<String, Object>> employeeDetails(String firstName) {

		String value1 = null;
		
		List<String> list = new ArrayList<>();

		List<Map<String, Object>> listObj = new ArrayList<Map<String, Object>>();

		List<Map<String, Object>> emp = (List<Map<String, Object>>) empRepo.findEmployeeDetails(firstName);
		for (Map<String, Object> map : emp) {
			Map<String, Object> mapObj = new HashMap<String, Object>();

			String val = (String) map.get("substation");
			int postValue = Integer.parseInt(val);
			value1 = empRepo.getValue(postValue);
			list.add(value1);

			mapObj.put("firstName", map.get("first_name"));
			mapObj.put("lastName", map.get("last_name"));
			mapObj.put("post", map.get("post"));
			mapObj.put("path", value1);
			listObj.add(mapObj);

		}

		return listObj;
	}

	@Override
	public void loadDataFromExcell(String fileName) throws IOException {
		FileInputStream file = new FileInputStream(new File("/Users/prabhat/Excel/ReadFile/" + fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		String subdivisionId = null;

		// Get first sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		System.out.println("last rows ==> " + sheet.getLastRowNum());
		
		String[] LookupArr = {"DISCOM","ZONE", "CIRCLE", "DIVISION", "SUBDIVISION", "SUBSTATION","POST"};
		
		
		for(int i =0 ; i<LookupArr.length; i++)
		{
		LookUp lookup = new LookUp();
		lookup.setName(LookupArr[i]);
		lookupRepo.save(lookup);
		System.out.println("saving lookup detail");
		
		}
		
		String[] postNames = {"ASSISTANT ENGINEER", "CHAIRMAN", "CHEIF ENGINEER", "CUSTOMER CARE EXECUTIVE", "DIRECTOR", "DIVISIONAL ACCOUNTANT(REV)", "EXECUTIVE ENGINEER", "JUNIOR ENGINEER", "MANAGING DIRECTOR", "SUPRETENDING ENGINEER" };

		for(int i =0 ; i<postNames.length; i++)
		{
		LookupDetail lookupDetail = new LookupDetail();
		lookupDetail.setName(postNames[i]);
		lookupDetail.setLookUpId(new LookUp((long) 7, "POST", null));
		lookupDetailRepo.save(lookupDetail);
		
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
			
			if (!discom.equalsIgnoreCase(lookupDetailRepo.checkName(discom)) ) {
				LookupDetail ld = new LookupDetail();
				ld.setName(discom);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(null);
				ld.setLookUpId(new LookUp((long) 1, "DISCOM", "NULL"));
				lookupDetailRepo.save(ld);
			} else {
				System.out.println("discom already present");
			}
			if (!zone.equalsIgnoreCase(lookupDetailRepo.checkName(zone))) {
				// System.out.println("verified " + zone);
				String parentId = lookupDetailRepo.getParentId(discom);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(zone);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 2, "ZONE", "NULL"));
				lookupDetailRepo.save(ld);
			} else {
				System.out.println("Zone already present");
			}
			if (!circle.equalsIgnoreCase(lookupDetailRepo.checkName(circle))) {
				System.out.println("verified " + circle);
				String parentId = lookupDetailRepo.getParentId(zone);
				LookupDetail ld = new LookupDetail();

				ld.setName(circle);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 3, "CIRCLE", "NULL"));
				lookupDetailRepo.save(ld);
			} else {
				System.out.println("Circle already present");
			}

			if (!division.equalsIgnoreCase(lookupDetailRepo.checkName(division))) {
				System.out.println("verified " + division);
				String parentId = lookupDetailRepo.getParentId(circle);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(division);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 4, "DIVISION", "NULL"));
				System.out.println("???????????????????");
				lookupDetailRepo.save(ld);
			} else {
				System.out.println("DIVISION already present");
			}

			if (lookupDetailRepo.checkSubDiv(subdivision)==0) {
				System.out.println("verified " + subdivision);
				String parentId = lookupDetailRepo.getParentId(division);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(subdivision);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 5, "SUBDIVISION", "NULL"));
				
				lookupDetailRepo.save(ld);
				System.out.println("New SUBDIVISION ADDED");
				subdivisionId = lookupDetailRepo.getParentId(subdivision);
				
			}
			else if(lookupDetailRepo.checkSubDiv(subdivision)==1) {
				
				System.out.println("Subdivision already present");
				System.out.println("verified " + subdivision);
				String parentId = lookupDetailRepo.getParentId(division);
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();
				
				ld.setName(subdivision);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 5, "SUBDIVISION", "NULL"));
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				lookupDetailRepo.save(ld);
				System.out.println("printttting last inserted Id ===> " + lookupDetailRepo.getLastInsertedtId());
				System.out.println("Copy of SUBDIVISION ADDED");
				subdivisionId = lookupDetailRepo.getLastInsertedtId();
				System.out.println("id of "+ subdivision+ "is ======>" +subdivisionId);
			}
			
			

			if (!substation.equalsIgnoreCase(lookupDetailRepo.checkName(substation))) {
				System.out.println("verified " + substation);
				String parentId = lookupDetailRepo.getLastInsertedtId();
				System.out.println("parentId is " + parentId);
				LookupDetail ld = new LookupDetail();

				ld.setName(substation);
				ld.setCreatedBy("User");
				ld.setCode("NULL");
				ld.setLastModifiedBy("NULL");
				ld.setParentId(parentId);
				ld.setLookUpId(new LookUp((long) 6, "SUBSTATION", "NULL"));
				lookupDetailRepo.save(ld);
			} else {
				System.out.println("SUBSTATION already present");
			}
			System.out.println("Phone number is " + phone);
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
				System.out.println("fname ===> " + fname);
				System.out.println("lname ==> " + lname);
			} else if (nameArr.length == 2) {
				fname = nameArr[0];
				lname = nameArr[1];
			} else {
				fname = name;
				lname = "";
			}

			System.out.println("printing email ===>");
			String post = lookupDetailRepo.getParentId("JUNIOR ENGINEER");
			System.out.println("fetching post detail ===> ");
			EmployeeDetail emp = new EmployeeDetail();
			emp.setFirstName(fname);
			emp.setLastName(lname);
			emp.setMobile(phone);
			emp.setEmail(email);
			System.out.println("email is " + email);
			
			if (empRepo.verifyEmail(email) == 0) {
				empRepo.save(emp);
				System.out.println("emp details saved");

				String discomId = lookupDetailRepo.getParentId(discom);
				String zoneId = lookupDetailRepo.getParentId(zone);
				String circleId = lookupDetailRepo.getParentId(circle);
				String divisionId = lookupDetailRepo.getParentId(division);
				String substationId = lookupDetailRepo.getParentId(substation);

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
				System.out.println("printing emp object ==>" + emp);
				empDetailOffRepo.save(empOfficial);
				System.out.println("saving empOfficialDetailRepo");
			} else {
				System.out.println("Emp record found");

			}

		} 
		file.close();

	}

	@Override
	public String uploadDocument(@Valid @NotNull @NotBlank @NotEmpty MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		Path fileStorageLocation = Paths.get("/Users/prabhat/Excel/ReadFile/").toAbsolutePath().normalize();
		File directory=new File("/Users/prabhat/Excel/ReadFile/");
		if(directory.exists())
		{
			
			
				try {
					Path targetLocation  = fileStorageLocation.resolve(fileName);
					Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return fileStorageLocation.toString().concat("/").concat(fileName);
				
			
		}
		
		else
		{			try {
				Files.createDirectories(fileStorageLocation);
				Path targetLocation  = fileStorageLocation.resolve(fileName);
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return fileStorageLocation.toString().concat("/").concat(fileName);
		}
		
	}

	

}
