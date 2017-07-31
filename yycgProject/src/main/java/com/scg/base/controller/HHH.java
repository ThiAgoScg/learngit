package com.scg.base.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class HHH {
	/**
	 * @author scg
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//创建一个SXSSFworkBook
		SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
		//创建一个sheet
		Sheet sheet = workbook.createSheet();
		for(int rownum = 0; rownum < 10001;rownum++){
			//创建行
			Row row = sheet.createRow(rownum);
			for(int cellnum = 0;cellnum<10;cellnum++){//创建单元格
				Cell cell = row.createCell(cellnum);
				String address = new CellReference(cell).formatAsString();//单元格地址
				cell.setCellValue(address+"---");
			}
			if(rownum %1000 == 0){
				SXSSFSheet sh = (SXSSFSheet) sheet;
				try {
					sh.flushRows(100);
					Thread.sleep(1000);
					System.out.println("写入.....");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		FileOutputStream fos = new FileOutputStream("d:/test.xlsx");
		workbook.write(fos);
		fos.close();
		workbook.dispose();
		
	}
}
