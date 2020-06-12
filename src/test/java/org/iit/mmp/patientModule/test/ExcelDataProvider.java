package org.iit.mmp.patientModule.test;

import java.util.concurrent.TimeUnit;

import org.iit.mmp.utility.ExcelUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProvider {

	WebDriver driver;
	
	@Test
	public void initiateBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver  = new FirefoxDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[2]/a")).click();
		driver.findElement(By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]")).click();
	}
	
	@Test(dataProvider="testData1")
	public void patientLogin(String username, String password)
	{
			
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/p[6]/input")).click();
		alertHandler();
	}
	
	@Test
	public void alertHandler()
	{
		Alert alert=driver.switchTo().alert();
		
		//WebDriverWait wait=new WebDriverWait (driver,10);
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
		}
			
	
	
	@DataProvider(name="testData1")
	public  Object[][] getData() {
		String excelPath="C:\\Users\\shrut\\eclipse-workspace\\mmp\\src\\test\\resources\\excel\\LoginData.xlsx";
		Object data[][]=testData(excelPath,"Sheet1");
		return data;
	}
	
	public  Object[][] testData(String excelPath, String sheetName)
	{
		
		ExcelUtils excel=new ExcelUtils(excelPath,sheetName);		
		int rowCount= excel.getRowCount();
		int colNum= excel.getcolCount();
		
		//Defining an object array
		Object data[][]=new Object[rowCount-1][colNum];
		
		for (int i=1; i<rowCount; i++)
		{
			for (int j=0; j<colNum; j++)
			{
				String cellData=excel.getCellDataString(i, j);
					//	System.out.println(cellData);
						
						//inserting data in Object array
						data[i-1][j]=cellData;
			}
		}
		return data;
	}
	
	
}

