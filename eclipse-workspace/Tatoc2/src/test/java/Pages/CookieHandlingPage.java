package Pages;

import java.io.IOException;
import Pages.Utilities.ElementSearch;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class CookieHandlingPage {
	
	WebDriver driver; 
	
	public CookieHandlingPage(WebDriver driver) {
		
		this.driver = driver;

	}
	ElementSearch es;
	public void CookieGenerateErrorTest() throws IOException {
		es = new ElementSearch(driver,"CookieHandlingElements");
		es.findElement("Generate_Token").click();
		es.findElement("Proceed").click();
		Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page Title: Error - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page get redirects to Error Page without setting the cookie and clicks Proceed link");
		
	}
	
	public void CookieErrorTest() throws IOException {
		
		driver.navigate().back();
		es.findElement("Proceed").click();
		Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C", "Assertion Failed: Page Title: Error - T.A.T.O.C not found"); 
		Reporter.log("Assertion Passed: Page get redirects to Error Page without generating the Token and clicks Proceed link");
		
	}
	
	public void CookieGenerateTest() throws IOException {
		
		driver.navigate().back();
		es.findElement("Generate_Token").click();
		String cookie = es.findElement("token").getText();
		String[] cookiesplit = cookie.split(": ");
		
		Cookie ck = new Cookie("Token", cookiesplit[1]);
		driver.manage().addCookie(ck);
		es.findElement("Proceed").click();
		Assert.assertEquals(driver.getTitle(), "End - T.A.T.O.C","Assertion Failed: Page Title: End - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page gets redirects to next page after setting cookie and clicks on Proceed");
		
	}
}