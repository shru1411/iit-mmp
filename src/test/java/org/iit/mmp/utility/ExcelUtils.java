package org.iit.mmp.utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getting row count from excel sheet
	public static int getRowCount() {
		int rowCount = 0;
		try {
			rowCount = sheet.getPhysicalNumberOfRows();
			//System.out.println("No of Rows: " + rowCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return rowCount;
	}

	// Getting total number of columns from excel sheet
	public static int getcolCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			//System.out.println("No of Columns: " + colCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return colCount;
	}

	// Getting cell data from excel spreadsheet
	public static String getCellDataString(int rowCount, int colCount) {
		String cellData = null;
		try {
			
			cellData = sheet.getRow(rowCount).getCell(colCount).getStringCellValue();
			//System.out.println("Using Excel util"+cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return cellData;
	}

	//To get cell data number
	public static void getCellDataNumber(int rowNum, int colNum) {
		try {
			double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(cellData);
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
}
