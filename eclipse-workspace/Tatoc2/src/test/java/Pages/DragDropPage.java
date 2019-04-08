package Pages;

import java.io.IOException;
import Pages.Utilities.ElementSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

public class DragDropPage {
	
	WebDriver driver;
	
	public DragDropPage(WebDriver driver) {
		
		this.driver = driver;

	}
	ElementSearch es;
	public void DragButton() throws IOException {
		
		es = new ElementSearch(driver,"DragDropElements");
		Boolean check = true;
		Boolean button = es.findElement("Drag_Me").isEnabled();
		Assert.assertEquals(button, check, "Assertion Failed: Drag Me link is not found");
		Reporter.log("Assertion Passed: Drag Me link is diplayed");
		
	}
	public void DragErrorTest() throws IOException {
		
		es.findElement("Proceed").click();
        Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page Title: Error - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to Error page after clicking Proceed without performing dragging action correctly");
               
	}
	public void DragTest() throws IOException {
        
		driver.navigate().back(); 
		WebElement From=es.findElement("Drag_Me");	
        WebElement To= es.findElement("Dropbox");									
        Actions act=new Actions(driver);							
        act.dragAndDrop(From, To).build().perform();
        es.findElement("Proceed").click();
        Assert.assertEquals(driver.getTitle(), "Windows - Basic Course - T.A.T.O.C","Assertion Failed: Page Title: Windows - Basic Course not found");
        Reporter.log("Assertion Passed: Page redirects to next Page after performing Drag Me action correctly and clicking Proceed");
		
	}

}
