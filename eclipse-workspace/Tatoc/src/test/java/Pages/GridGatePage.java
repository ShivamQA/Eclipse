package Pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import Pages.Utilities.ElementSearch;
public class GridGatePage {
	
	WebDriver driver;
	
	public GridGatePage(WebDriver driver) {

		this.driver = driver;

	}
	
	ElementSearch es;
	
	public void GridErrorTest() throws IOException {
		es = new ElementSearch(driver,"GridGateElements");
		es.findElement("RedBox").click();
		String errorPage = driver.getTitle();
        Assert.assertEquals(errorPage, "Error - T.A.T.O.C","Assertion Failed: Page title: Error - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to error page after clicking Red Box");
	}
	public void GridPassTest() throws IOException {
	
		driver.navigate().back();
        es.findElement("GreenBox").click();
		String nextPage = driver.getTitle();
        Assert.assertEquals(nextPage, "Frame Dungeon - Basic Course - T.A.T.O.C","Assertion Failed: Page title: Frame Dungeon - Basic Course - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to next page after clicking Green Box");
	}
	
}
