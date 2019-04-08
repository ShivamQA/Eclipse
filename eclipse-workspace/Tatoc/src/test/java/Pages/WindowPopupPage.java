package Pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import Pages.Utilities.ElementSearch;

public class WindowPopupPage {
	
	WebDriver driver;
	HttpURLConnection huc = null;
	
	public WindowPopupPage(WebDriver driver) {
		
		this.driver = driver;

	}
	ElementSearch es;
	
	public void WindowLinks() throws IOException {
		
		es = new ElementSearch(driver,"WindowPopupElements");
		String launchLink = es.findElement("Launch_Popup_Window").getAttribute("href");
		huc = (HttpURLConnection)(new URL(launchLink).openConnection());
	    huc.connect();
	    int respCode = huc.getResponseCode();
	    Assert.assertEquals(respCode, 200,"Assertion Failed: Status Code - 200 not found");
	    Reporter.log("Assertion Passed: Launch Popup Window link is displayed");
	    String proceedLink = es.findElement("Proceed").getAttribute("href");
		huc = (HttpURLConnection)(new URL(proceedLink).openConnection());
	    huc.connect();
	    int respCode2 = huc.getResponseCode();
	    Assert.assertEquals(respCode2, 200,"Assertion Failed: Status Code - 200 not found");
	    Reporter.log("Assertion Passed: Proceed link is displayed");
	    
	}
	public void WindowButton() throws IOException {
		
		Boolean expected = true;
		es.findElement("Launch_Popup_Window").click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Boolean bar = es.findElement("TextBox").isEnabled();
		Assert.assertEquals(bar, expected,"Assertion Failed: Name Text Box is not found");
		Reporter.log("Assertion Passed: Text Box Name is diplayed");
		Boolean bar2 = es.findElement("Submit").isEnabled();
		Assert.assertEquals(bar2, expected,"Assertion Failed: Submit button is not found");
		Reporter.log("Assertion Passed: Submit Button is diplayed");
		driver.switchTo().window(tabs2.get(0));
		
	}
	public void WindowErrorTest() throws IOException {
		
		es.findElement("Proceed").click();
	    Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page Title: Error - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page redirects to Error Page without launching Popup Window");
	    
	}
	
	public void WindowNameTest() throws IOException {
		
		driver.navigate().back();
		es.findElement("Launch_Popup_Window").click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		es.findElement("Submit").click();
	    driver.switchTo().window(tabs2.get(0));
	    es.findElement("Proceed").click();
	    Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C", "Assertion Failed: Page Title: Error - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page redirects to Error Page without inserting name in Text Box");
		
	}
	public void WindowTest() throws IOException {
		
		driver.navigate().back();
		es.findElement("Launch_Popup_Window").click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		es.findElement("TextBox").sendKeys("Shivam");
		es.findElement("Submit").click();
	    driver.switchTo().window(tabs2.get(0));
	    es.findElement("Proceed").click();
	    Assert.assertEquals(driver.getTitle(), "Cookie Handling - Basic Course - T.A.T.O.C","Assertion Failed: Page Title: Cookie Handling - Basic Course - T.A.T.O.C not found");
	    Reporter.log("Assertion Passed: Page redirects to next page after submitting name in the Text Box and click Proceed");
	
	}

}
