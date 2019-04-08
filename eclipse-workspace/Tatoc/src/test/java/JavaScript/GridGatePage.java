package JavaScript;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;


public class GridGatePage {
	
	WebDriver driver;
	JavascriptExecutor js;
	public GridGatePage(WebDriver driver) {

		this.driver = driver;

	}
	
	//ElementSearch es;
	
	public void GridErrorTest() throws IOException {
		//es = new ElementSearch(driver,"GridGateElements");
		//es.findElement("RedBox").click();
		js = (JavascriptExecutor)driver;
		js.executeScript("document.querySelector(\"div[onclick='failthru();'] \").click()");
		String errorPage = js.executeScript("return document.title;").toString();
        Assert.assertEquals(errorPage, "Error - T.A.T.O.C","Assertion Failed: Page title: Error - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to error page after clicking Red Box");
	}
	public void GridPassTest() throws IOException {
	
		driver.navigate().back();
		js.executeScript("document.querySelector(\"div[onclick='passthru();'] \").click()");
		String nextPage = js.executeScript("return document.title;").toString();
        Assert.assertEquals(nextPage, "Frame Dungeon - Basic Course - T.A.T.O.C","Assertion Failed: Page title: Frame Dungeon - Basic Course - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to next page after clicking Green Box");
	}
	
}
