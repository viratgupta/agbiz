/**
 * 
 */
package com.agilebiz.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author virat
 *
 */
public class ExcelSetValue {
	
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook setworkbook = null;
	private XSSFSheet setsheet = null;
	private XSSFRow setrow = null;
	private XSSFCell setcell = null;

	public ExcelSetValue(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(new File(path));
			setworkbook = new XSSFWorkbook(fis);
			setsheet = setworkbook.getSheetAt(0);
			//fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
