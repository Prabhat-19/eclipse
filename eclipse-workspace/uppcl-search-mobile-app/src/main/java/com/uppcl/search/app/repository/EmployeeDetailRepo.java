package com.uppcl.search.app.repository;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uppcl.search.app.domain.EmployeeDetail;





public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetail, Long>{

	@Query(value="SELECT EXISTS(SELECT * from employee_detail WHERE mobile=?1)",nativeQuery = true)
	int verifyMobNo(@Valid String mobile);

	/*
	 * single value get
	 */
	@Query(value="select e.first_name, e.last_name, eo.substation ,e.employee_detail_id,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstName(String firstName);
	
	@Query(value="select e.first_name, e.last_name, eo.substation,e.employee_detail_id,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.last_name=?1",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsLastName(String lastName);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id, eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where eo.post=?1",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsPost(String post);
	
	@Query(value="select e.first_name, e.last_name, e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.mobile=?1",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsMobileNumber(String Mobile);
	
	/*
	 * double value get 
	 */
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and e.last_name=?2",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndLastName(String firstName,String lastName);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and eo.post=?2",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndPost(String firstName,String post);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and e.mobile=?2",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndMobile(String firstName,String mobile);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.last_name=?1 and eo.post=?2",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsLastNameAndPost(String lastName,String post);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.last_name=?1 and e.mobile=?2",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsLastNameAndMobile(String lastName,String mobile);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.mobile=?1 and eo.post=?2",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsMobileAndPost(String mobile,String post);
	
	
	/*
	 * three value get 
	 */
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and e.last_name=?2 and e.mobile=?3",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndLastNameAndMobile(String firstName,String lastName,String mobile);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and e.last_name=?2 and eo.post=?3",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndLastNameAndPost(String firstName,String lastName,String post);
	
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.last_name=?1 and e.mobile=?2 and eo.post=?3",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsLastNameAndPostAndMobile(String lastName,String mobile,String post);
		
	@Query(value="select e.first_name, e.last_name,e.employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and e.mobile=?2 and eo.post=?3",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndMobileAndPost(String FirstName,String mobile,String post);
		
	
	/*
	 * four value get 
	 */
	@Query(value="select e.first_name, e.last_name,employee_detail_id,eo.substation,eo.team_id,eo.official_email,eo.official_contact,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1 and e.last_name=?2 and e.mobile=?3 and eo.post=?4",nativeQuery=true)
	List<Map<String,Object>> findEmployeeDetailsFirstNameAndLastNameAndMobileAndPost(String firstName,String lastName,String mobile,String post);
	
		
/*
 * Particular employee details
 */
   @Query(value="select ld.name as post from lookup_detail ld where employee_detail.employee_detail_id =?1",nativeQuery=true)
	List<Map<String,Object>> findParticularEmployeeDetails(int id);
   
 // Added by Prabhat
   
   @Query(value="SELECT employee_detail_id from employee_detail WHERE mobile=?1",nativeQuery = true)
	long findEmpId(String phone);

	@Query(value="SELECT EXISTS(SELECT * from employee_detail WHERE email=?1)",nativeQuery = true)
	int verifyEmail(String email);
	
}	
