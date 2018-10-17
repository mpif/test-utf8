package com.codefans.apache.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html
 * 
 * @author caishengzhi
 * @date 2016年9月21日下午9:57:27
 *
 */
public class ExcelReaderAndWriter {

	private String rootDir = "H:/tmp/";
	@Test
	public void test() {
		excelWrite();
		excelRead();
	}
	
	public void excelWrite() {
		
		writeHSSFWorkbook();
		writeXSSFWorkbook();
	    
	}
	
	public void writeHSSFWorkbook() {
		try {
			Workbook wb = new HSSFWorkbook();
			CreationHelper createHelper = wb.getCreationHelper();
			
			Sheet sheet = wb.createSheet("new sheet");
			
			Row row = sheet.createRow(0);
		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(0);
		    cell.setCellValue(1);

		    // Or do it on one line.
		    row.createCell(1).setCellValue(1.2);
		    row.createCell(2).setCellValue(
		         createHelper.createRichTextString("This is a string"));
		    row.createCell(3).setCellValue(true);
		    
		    // Create a cell and put a date value in it.  The first cell is not styled
		    // as a date.
		    Cell dateCell = row.createCell(4);
		    dateCell.setCellValue(new Date());

		    // we style the second cell as a date (and time).  It is important to
		    // create a new cell style from the workbook otherwise you can end up
		    // modifying the built in style and effecting not only this cell but other cells.
		    CellStyle cellStyle = wb.createCellStyle();
		    cellStyle.setDataFormat(
//		        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		    createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
		    dateCell.setCellStyle(cellStyle);
		    
			FileOutputStream fileOut = new FileOutputStream(rootDir + "HSSFWorkbook.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeXSSFWorkbook() {
		try {
			Workbook wb = new XSSFWorkbook();
			CreationHelper createHelper = wb.getCreationHelper();
			
			Sheet sheet = wb.createSheet("new sheet");
			
			Row row = sheet.createRow(0);
		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(0);
		    cell.setCellValue(1);

		    // Or do it on one line.
		    row.createCell(1).setCellValue(1.2);
		    row.createCell(2).setCellValue(
		         createHelper.createRichTextString("This is a string"));
		    row.createCell(3).setCellValue(true);
		    
		    // Create a cell and put a date value in it.  The first cell is not styled
		    // as a date.
		    Cell dateCell = row.createCell(4);
		    dateCell.setCellValue(new Date());

		    // we style the second cell as a date (and time).  It is important to
		    // create a new cell style from the workbook otherwise you can end up
		    // modifying the built in style and effecting not only this cell but other cells.
		    CellStyle cellStyle = wb.createCellStyle();
		    cellStyle.setDataFormat(
//		        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		    createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
		    dateCell.setCellStyle(cellStyle);
			
			FileOutputStream fileOut = new FileOutputStream(rootDir + "XSSFWorkbook.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excelRead() {
		
	}
	
	
	
	
	
	
	
}
