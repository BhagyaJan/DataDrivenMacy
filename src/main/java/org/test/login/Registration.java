package org.test.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Registration {
	
	
	public Row readRegistrationDetails() throws IOException
	{
		File fRead=new File("D:\\LoginRegistrationMacy.xlsx");
		FileInputStream fis = new FileInputStream(fRead);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet1");
		
		Row r = sheet.getRow(2);
		
		return r;
	}
}
