package com.uppcl.search.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uppcl.search.app.domain.LookupDetail;




@Repository
public interface LookupDetailRepository extends JpaRepository<LookupDetail, Long>{

	@Query(value="select * from  lookup_detail where `lookup_detail`.`lookup_id`=?1",nativeQuery = true)
	List<LookupDetail> findByLookupId(int lookupId);

	@Query(value="select * from  lookup_detail where `lookup_detail`.`parent_id`=?1",nativeQuery = true)
	List<LookupDetail> findByParentId(int parentId);

	@Query(value="SELECT getpath(?1) AS path FROM lookup_detail limit 1",nativeQuery = true)
	String getValue(int no);
	
// Added by Prabhat
	
	@Query(value="select name from  lookup_detail where `lookup_detail`.`name`=?1",nativeQuery = true)
	String checkName(String name);

	@Query(value="select id from  lookup_detail where `lookup_detail`.`name`=?1",nativeQuery = true)
	String getId(String name);
	
	@Query(value="SELECT EXISTS(SELECT * from lookup_detail WHERE name=?1)",nativeQuery = true)
	int checkSubDiv( String subdivision);

	@Query(value="SELECT max(id) FROM lookup_detail",nativeQuery = true)
	String getLastInsertedtId();
	
	
	
	@Query(value="select count(*) from lookup_detail",nativeQuery = true)
	int checkRecord();
}

