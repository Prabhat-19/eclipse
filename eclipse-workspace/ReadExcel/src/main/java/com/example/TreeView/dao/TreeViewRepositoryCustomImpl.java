package com.example.TreeView.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.TreeView.entity.TreeView;

// Implemented the nativeQuery with parameter
public class TreeViewRepositoryCustomImpl implements TreeViewRepositoryCustom {

	@PersistenceContext
    EntityManager entityManager;
	
	//Custom Query
	public String find() {
		// TODO Auto-generated method stub
		
//		Query query = entityManager.createNativeQuery("select * from tree_view where parent_id='1'", TreeView.class);
		Query query = entityManager.createNativeQuery("select * from tree_view t where t.id=:id", TreeView.class);
		query.setParameter("id"	, 2);
		
		List<TreeView> t = query.getResultList();
		
		for(TreeView l : t )
		{
			
			System.out.println("Child nodes of N1 are : "+l.getName());
		}
		System.out.println(" I am inside TreeViewRepositoryCustomImpl");
		return null;
	}

}
