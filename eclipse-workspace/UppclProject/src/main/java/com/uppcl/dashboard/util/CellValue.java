package com.uppcl.dashboard.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class CellValue {
	
	public static Object printCellValue(Cell cell) {
		Object result = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
             //   System.out.print(cell.getBooleanCellValue()+ "\t\t");
                result= cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
            //    System.out.print(cell.getRichStringCellValue().getString()+ "\t\t");
                result= cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
             //       System.out.print(cell.getDateCellValue()+ "\t\t");
                    result= cell.getDateCellValue();
                } else {
                   System.out.print(cell.getNumericCellValue()+ "\t\t");
                    result= cell.getNumericCellValue();
                    
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
            //    System.out.print(cell.getCellFormula()+ "\t\t");
                result= cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_BLANK:
            //    System.out.print("  "+ "\t\t");
                result= "  ";
                break;
            default:
             //   System.out.print(""+ "\t\t");
                result= "";
        }

        System.out.print("");
        return result;
        
    }
	
}
