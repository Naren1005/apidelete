package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {
	 String filepath;
	 String sheetname;
	
	 public Excelutils(String filepath,String sheetname)
	 {
		 this.filepath=filepath;
		 this.sheetname=sheetname;
		
	 }
	 
	 public int getRowCount() throws IOException
	 {
		 FileInputStream fi= new FileInputStream(filepath);
		 
		 XSSFWorkbook workbook=new XSSFWorkbook(fi);
		 XSSFSheet sheet=workbook.getSheet(sheetname);
		 
		 return sheet.getLastRowNum() ;
	 }
	 
	 public int getCellCount(int rownum) throws IOException
	 {
		 FileInputStream fi= new FileInputStream(filepath);
		 
		 XSSFWorkbook workbook=new XSSFWorkbook(fi);
		 XSSFSheet sheet=workbook.getSheet(sheetname);
		 XSSFRow row=sheet.getRow(rownum);
		 return row.getLastCellNum() ;
		 
	 }
	 
	 public String getCellData(int rownum, int colnum) throws IOException {
	        try (FileInputStream fi = new FileInputStream(filepath); 
	             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {
	            XSSFSheet sheet = workbook.getSheet(sheetname);
	            XSSFRow row = sheet.getRow(rownum);
	            XSSFCell cell = row.getCell(colnum);
	            
	            DataFormatter formatter = new DataFormatter();
	            return formatter.formatCellValue(cell);
	        } catch (Exception e) {
	            return "";
	        }
	    }
	 public void setCellData(int rownum, int colnum, String data) throws IOException {
	        File xlfile = new File(filepath);
	        
	        if (!xlfile.exists()) {
	            try (XSSFWorkbook workbook = new XSSFWorkbook(); 
	                 FileOutputStream fo = new FileOutputStream(filepath)) {
	                workbook.write(fo);
	            }
	        }
	        
	        try (FileInputStream fi = new FileInputStream(filepath);
	             XSSFWorkbook workbook = new XSSFWorkbook(fi);
	             FileOutputStream fo = new FileOutputStream(filepath)) {
	            XSSFSheet sheet = workbook.getSheet(sheetname);
	            
	            if (sheet == null) {
	                sheet = workbook.createSheet(sheetname);
	            }
	            
	            XSSFRow row = sheet.getRow(rownum);
	            if (row == null) {
	                row = sheet.createRow(rownum);
	            }
	            
	            XSSFCell cell = row.createCell(colnum);
	            cell.setCellValue(data);
	            
	            workbook.write(fo);
	        }
	    }

}
