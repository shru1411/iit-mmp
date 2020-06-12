package org.iit.mmp.patientModule.test;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class patientLoginModule {

	public void validateLogin() 
	{
		instantiateDriver();
		launchApplicationURL();
		clickpatientLoginButton();
		patientLoginUsernamePass("clientName","password");
		clickLogin();
		clickLogout();
		
	}
	
	WebDriver driver;
	
		
	@Test(priority=1, description="US_001_Patient Login Page",groups={"US_001","regression","sanity","patientLoginmodule"})
	public void instantiateDriver()
	{
			
		driver  = new FirefoxDriver();
	}
	
	@Test(priority=2)
	public void launchApplicationURL()
	{
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test (priority=3)
	public void clickpatientLoginButton() 
	{
		driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[2]/a")).click();
		driver.findElement(By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]")).click();			
	}
	
	@Parameters({"clientName", "password"}) 
	@Test (dependsOnMethods="clickpatientLoginButton")
	public void patientLoginUsernamePass(String clientName, String password) 
	{
	
		//System.out.println("User name:" + clientName);
		//System.out.println("Password:" + password);
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(clientName);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
				
	}
	
	@Test (dependsOnMethods ="patientLoginUsernamePass")
	public void clickLogin()
	{
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/p[6]/input")).click();
	}
	
	@Test (dependsOnMethods ="clickLogin")
	public void clickLogout()
	{
		driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();
	}
	
	
	
	}
	

