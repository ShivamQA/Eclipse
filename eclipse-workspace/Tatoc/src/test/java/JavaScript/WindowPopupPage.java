package JavaScript;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;


public class WindowPopupPage {
	
	WebDriver driver;
	HttpURLConnection huc = null;
	JavascriptExecutor js;
	WebElement wb;
	public WindowPopupPage(WebDriver driver) {
		
		this.driver = driver;

	}
	//ElementSearch es;
	
	public void WindowLinks() throws IOException {
		js = (JavascriptExecutor)driver;
		//es = new ElementSearch(driver,"WindowPopupElements");
		wb = (WebElement) js.executeScript("return document.querySelector(\"a[onclick = 'launchwindow();']\");");
		String launchLink = wb.getAttribute("href");
		huc = (HttpURLConnection)(new URL(launchLink).openConnection());
	    huc.connect();
	    int respCode = huc.getResponseCode();
	    Assert.assertEquals(respCode, 200,"Assertion Failed: Status Code - 200 not found");
	    Reporter.log("Assertion Passed: Launch Popup Window link is displayed");
	    wb = (WebElement) js.executeScript("return document.querySelector(\"a[onclick='gonext();'] \");");
	    String proceedLink = wb.getAttribute("href");
		huc = (HttpURLConnection)(new URL(proceedLink).openConnection());
	    huc.connect();
	    int respCode2 = huc.getResponseCode();
	    Assert.assertEquals(respCode2, 200,"Assertion Failed: Status Code - 200 not found");
	    Reporter.log("Assertion Passed: Proceed link is displayed");
	    
	}
	public void WindowButton() throws IOException {
		
		Boolean expected = true;
		js.executeScript("document.querySelector(\"a[onclick = 'launchwindow();']\").click();");
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		wb = (WebElement) js.executeScript("return document.getElementById('name');");
		Boolean bar = wb.isEnabled();
		Assert.assertEquals(bar, expected,"Assertion Failed: Name Text Box is not found");
		Reporter.log("Assertion Passed: Text Box Name is diplayed");
		wb = (WebElement) js.executeScript("return document.getElementById('submit');");
		Boolean bar2 = wb.isEnabled();
		Assert.assertEquals(bar2, expected,"Assertion Failed: Submit button is not found");
		Reporter.log("Assertion Passed: Submit Button is diplayed");
		driver.switchTo().window(tabs2.get(0));
		
	}
	public void WindowErrorTest() throws IOException {
		
		js.executeScript("document.querySelector(\"a[onclick='gonext();'] \").click();");
	    Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page Title: Error - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page redirects to Error Page without launching Popup Window");
	    
	}
	
	public void WindowNameTest() throws IOException {
		
		driver.navigate().back();
		js.executeScript("document.querySelector(\"a[onclick = 'launchwindow();']\").click();");
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		js.executeScript("document.getElementById('submit').click();");
	    driver.switchTo().window(tabs2.get(0));
	    js.executeScript("document.querySelector(\"a[onclick='gonext();']\").click();");
	    Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C", "Assertion Failed: Page Title: Error - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Page redirects to Error Page without inserting name in Text Box");
		
	}
	public void WindowTest() throws IOException {
		
		driver.navigate().back();
		js.executeScript("document.querySelector(\"a[onclick = 'launchwindow();']\").click();");
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		js.executeScript("document.getElementById('name').value='Shivam Agarwal';");
		js.executeScript("document.getElementById('submit').click();");
	    driver.switchTo().window(tabs2.get(0));
	    js.executeScript("document.querySelector(\"a[onclick='gonext();']\").click();");
	    Assert.assertEquals(driver.getTitle(), "Cookie Handling - Basic Course - T.A.T.O.C","Assertion Failed: Page Title: Cookie Handling - Basic Course - T.A.T.O.C not found");
	    Reporter.log("Assertion Passed: Page redirects to next page after submitting name in the Text Box and click Proceed");
	
	}

}
