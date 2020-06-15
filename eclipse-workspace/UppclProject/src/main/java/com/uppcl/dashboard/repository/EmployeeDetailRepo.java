package com.uppcl.dashboard.repository;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface EmployeeDetailRepo extends JpaRepository<com.uppcl.dashboard.domain.EmployeeDetail, Long>{

	@Query(value="SELECT EXISTS(SELECT * from employee_detail WHERE mobile=?1)",nativeQuery = true)
	int verifyMobNo(@Valid String mobile);

	@Query(value="select e.first_name, e.last_name, eo.substation ,ld.name as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id join lookup_detail ld on ld.id=eo.post where e.first_name=?1",nativeQuery=true)
	List<Map<String, Object>> findEmployeeDetails(String firstName);
	
//	@Query(value="select e.first_name, e.last_name, eo.substation ,eo.post as post from employee_detail e join employee_offical_detail eo on e.employee_detail_id = eo.employee_detail_id where e.first_name=?1",nativeQuery=true)
//	List<Map<String, Object>> findEmployeeDetails(String firstName);

	@Query(value="SELECT getpath(?1) AS path FROM lookup_detail limit 1",nativeQuery = true)
	String getValue(int postValue);

	@Query(value="SELECT employee_detail_id from employee_detail WHERE mobile=?1",nativeQuery = true)
	long findEmpId(String phone);

	@Query(value="SELECT EXISTS(SELECT * from employee_detail WHERE email=?1)",nativeQuery = true)
	int verifyEmail(String email);
	
	

	
	

}
