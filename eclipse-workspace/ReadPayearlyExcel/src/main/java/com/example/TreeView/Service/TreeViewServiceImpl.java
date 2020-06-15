package com.example.TreeView.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.TreeView.dao.TreeViewRepository;
import com.example.TreeView.entity.TreeView;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;



@Component
public class TreeViewServiceImpl implements TreeViewService {
	
	private TreeViewRepository treeViewRepo;
	
	public static final String SAMPLE_XLSX_FILE_PATH = "/Users/prabhat/Downloads/sample-xls-file.xls";
	
	
	
	
	@Autowired
	public TreeViewServiceImpl(TreeViewRepository treeViewRepo) {
		this.treeViewRepo = treeViewRepo;
		
	}

	@Override
	public List<TreeView> findAll() {
		//System.out.println("inside find all service impl");
		//System.out.println(treeViewRepo.findAll().get(1).getName());
		
		for(TreeView t : treeViewRepo.findAll())
		{
			//System.out.println(t.getId()+" , "+t.getName()+" , "+t.getParent_id());
			if(t.getParent_id() == 1)
			{
				System.out.println("Children of node1 are :" +t.getName());
			}
		}
		
		return treeViewRepo.findAll();
	}
	
	@Override
	public TreeView findById(int id)
	{
		Optional<TreeView> result = treeViewRepo.findById(id);
		TreeView treeView = null;
		if(result.isPresent())
		{
			treeView = result.get();
			//System.out.println(treeView);
		}

		
		return treeView;
	}

	public String find() {
		System.out.println("I am inside new method");
		return null;
	}

	public String saveData(String name, int parent_id) {
		
		TreeView obj = new TreeView(name, parent_id);
		obj = treeViewRepo.save(obj);
		System.out.println(obj.toString());
		
		return "Data Saved";
	}
	
	


	}
