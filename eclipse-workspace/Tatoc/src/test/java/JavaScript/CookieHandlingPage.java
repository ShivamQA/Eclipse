package JavaScript;

import java.io.IOException;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class CookieHandlingPage {
	
	WebElement wb;
	WebDriver driver; 
	JavascriptExecutor js;
	public CookieHandlingPage(WebDriver driver) {
		
		this.driver = driver;

	}
	
	public void CookieGenerateErrorTest() throws IOException {
		js = (JavascriptExecutor)driver;
		js.executeScript("document.querySelector(\"a[onclick = 'generateToken();']\").click();");
		js.executeScript("document.querySelector(\"a[onclick='gonext();']\").click();");
		Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page Title: Error - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page get redirects to Error Page without setting the cookie and clicks Proceed link");
		
	}
	
	public void CookieErrorTest() throws IOException {
		
		driver.navigate().back();
		js.executeScript("document.querySelector(\"a[onclick='gonext();']\").click();");
		Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C", "Assertion Failed: Page Title: Error - T.A.T.O.C not found"); 
		Reporter.log("Assertion Passed: Page get redirects to Error Page without generating the Token and clicks Proceed link");
		
	}
	
	public void CookieGenerateTest() throws IOException {
		
		driver.navigate().back();
		js.executeScript("document.querySelector(\"a[onclick = 'generateToken();']\").click();");
		wb = (WebElement) js.executeScript("return document.querySelector(\"span[id = 'token']\");");
		String cookie = wb.getText();
		String[] cookiesplit = cookie.split(": ");
		
		Cookie ck = new Cookie("Token", cookiesplit[1]);
		driver.manage().addCookie(ck);
		js.executeScript("document.querySelector(\"a[onclick='gonext();']\").click();");
		Assert.assertEquals(driver.getTitle(), "End - T.A.T.O.C","Assertion Failed: Page Title: End - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page gets redirects to next page after setting cookie and clicks on Proceed");
		
	}
}