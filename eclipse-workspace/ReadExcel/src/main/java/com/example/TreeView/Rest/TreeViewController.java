package com.example.TreeView.Rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TreeView.Service.TreeViewService;
import com.example.TreeView.dao.TreeViewRepository;
import com.example.TreeView.entity.Demo1;
import com.example.TreeView.entity.Demo2;
import com.example.TreeView.entity.ExcelEntity;
import com.example.TreeView.entity.Marker;
import com.example.TreeView.entity.TreeView;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fetch")

public class TreeViewController implements Marker {

	@Autowired
	private TreeViewRepository ts;
	
	private TreeViewService treeViewService;
	
	
	public static final String SAMPLE_XLSX_FILE_PATH = "/Users/prabhat/Downloads/sample-xls-file.xls";
	
	@Autowired
	public TreeViewController(TreeViewService treeViewService)
	{
		this.treeViewService = treeViewService;
	}
	
	@GetMapping("/all")
	public List<TreeView> getAllRow()
	{
			return treeViewService.findAll();
	}
	
	@GetMapping("/{id}")
	public TreeView findById(@PathVariable("id") int id )
	{
//		System.out.println(treeViewService.findById(id).getId()+" , "+treeViewService.findById(id).getName()+" , "+treeViewService.findById(id).getParent_id());
		
		return treeViewService.findById(id);
		
	}
	
	@GetMapping("/filter")
	public String getAllRows()
	{
			return ts.find();
	}
//  Reading the parameter	
//	@PostMapping("/save")
//	public String save(@RequestParam("name") String name, @RequestParam("parent_id") int parent_id )
//	{
//			treeViewService.saveData(name, parent_id);
//		
//			return "Saving the data";
//	}
//	
//	@PostMapping(path="/json")
//	public String readJson(@RequestBody String m)
//	{
//		
//	//m.forEach((k,v) -> System.out.println((k + ":" + v)));
//		System.out.println(" Printing :" + m);
//		System.out.println("I am checking");
//		//treeViewService.saveData(name , parent_id);
//		
//
//	return "Saving the data";
//	}
	
	//For saving the data into db
	
	@PostMapping(path="/json")
	public String readJson(@RequestBody Map<String, String> m)
	{
		
	//m.forEach((k,v) -> System.out.println((k + ":" + v)));
		
		ArrayList<String> l = new ArrayList<String>(m.values());
		String name = l.get(0);
		int parent_id =Integer.parseInt(l.get(1));
		
		treeViewService.saveData(name , parent_id);
		

	return "Saving the data";
	}
	
	@GetMapping("excel")
	public String readExcel() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		 Sheet sheet = workbook.getSheetAt(0);
	        
	        List al = new ArrayList();
	        Map<Integer, List> data = new HashMap<>();
	        System.out.println("Sheet last rownum " +sheet.getLastRowNum());
	        
	        
	        for(int i = 1 ; i <= sheet.getLastRowNum()-1 ; i++)
	        {
	        	for(int j=0 ; j<=1; j++)
	        	{
	        		System.out.println("Printing the name and pid");
	        		//System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
	        		Cell c =sheet.getRow(i).getCell(j);
	        		System.out.println(printCellValue(c));
	        		al.add(printCellValue(c));

	        	}
	        }
	        
	        for(int i=0; i<data.size(); i++)
	        {
	        System.out.println("saving the data into db ");
	        System.out.println("List is " +al);
	        String n = (String) al.get(0);
	        Double d = (Double) al.get(1);
	        int p = d.intValue();
	        data.get(1);
	    //treeViewService.saveData(n, p);
	        }
		
		return "Reading excell";
	}
	
	// Returning the type
	private static Object printCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
            
                return cell.getBooleanCellValue();
                
            
            case STRING:
                return	cell.getRichStringCellValue().getString();
                
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                	System.out.println(" Inside integer");
                    return cell.getNumericCellValue();
                }
                
            case FORMULA:
                return cell.getCellFormula();
                
            case BLANK:
                System.out.print("");
                
            default:
                System.out.print("");
        }

        return "No Output";
    }
	@GetMapping("/merge")
	public Map<List<Demo1>, List<Demo2>> merge()
	{
		List<Demo1> obj1 = new ArrayList<Demo1>();
		obj1.add(new Demo1("abc", 22));
		obj1.add(new Demo1("xyz", 34));
		
		List<Demo2> obj2 = new ArrayList<Demo2>();
		obj2.add(new Demo2("hello", 44));
		obj2.add(new Demo2("uuu", 66));
		
		
	
		
	List<Marker> l = new ArrayList<Marker>(obj1);
		l.addAll(obj2);
		

		Map<List<Demo1>, List<Demo2>> m1 = new LinkedHashMap();
		m1.put(obj1, obj2);
		m1.putAll(m1);
		
		
		return m1;
	}
	
}
