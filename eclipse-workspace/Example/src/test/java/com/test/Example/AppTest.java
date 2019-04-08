package com.test.Example;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AppTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
		driver.manage().window().maximize();
		
		
	}

	@Test
	public void GridErrorTest() throws IOException {
		
		driver.findElement(By.className("redbox")).click();
		String errorPage = driver.getTitle();
        Assert.assertEquals(errorPage, "Error - T.A.T.O.C","Assertion Failed: Page title: Error - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to error page after clicking Red Box");
	}
	@Test
	public void GridPassTest() throws IOException {
	
		driver.navigate().back();
		driver.findElement(By.className("greenbox")).click();
		String nextPage = driver.getTitle();
        Assert.assertEquals(nextPage, "Frame Dungeon - Basic Course - T.A.T.O.C","Assertion Failed: Page title: Frame Dungeon - Basic Course - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to next page after clicking Green Box");
	}
	@AfterTest
	public void close() {
		
		driver.close();
		
	}
}
