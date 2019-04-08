package JavaScript;

import java.io.IOException;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

public class DragDropPage {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebElement wb;
	public DragDropPage(WebDriver driver) {
		
		this.driver = driver;

	}
	//ElementSearch es;
	public void DragButton() throws IOException {
		js = (JavascriptExecutor)driver;
		//es = new ElementSearch(driver,"DragDropElements");
		Boolean check = true;
		wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'dragbox']\");");
		Boolean button = wb.isEnabled();
		Assert.assertEquals(button, check, "Assertion Failed: Drag Me link is not found");
		Reporter.log("Assertion Passed: Drag Me link is diplayed");
		
	}
	public void DragErrorTest() throws IOException {
		
		js.executeScript("document.querySelector(\"a[onclick='gonext();'] \").click()");
        Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page Title: Error - T.A.T.O.C not found");
        Reporter.log("Assertion Passed: Page redirects to Error page after clicking Proceed without performing dragging action correctly");
               
	}
	public void DragTest() throws IOException {
        
		driver.navigate().back(); 
		WebElement From= (WebElement) js.executeScript("return document.querySelector(\"div[id = 'dragbox']\");");	
        WebElement To= (WebElement) js.executeScript("return document.querySelector(\"div[id = 'dropbox']\");");									
        Actions act=new Actions(driver);							
        act.dragAndDrop(From, To).build().perform();
        js.executeScript("document.querySelector(\"a[onclick='gonext();'] \").click()");
        Assert.assertEquals(driver.getTitle(), "Windows - Basic Course - T.A.T.O.C","Assertion Failed: Page Title: Windows - Basic Course not found");
        Reporter.log("Assertion Passed: Page redirects to next Page after performing Drag Me action correctly and clicking Proceed");
		
	}

}
