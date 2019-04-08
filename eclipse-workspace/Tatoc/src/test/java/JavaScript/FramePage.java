package JavaScript;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;


public class FramePage {
	
	WebDriver driver;
	JavascriptExecutor js;
	WebElement wb;
	public FramePage(WebDriver driver) {
		
		this.driver = driver;

	}
	
	public void BoxCheck() throws IOException {
		js = (JavascriptExecutor)driver;
		//es = new ElementSearch(driver,"FrameElements");
		Boolean check = true;
		driver.switchTo().frame("main");
		wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'answer']\");");
		Boolean box1 = wb.isEnabled();
		Assert.assertEquals(box1, check,"Assertion Failed: Box 1 is not found");
		Reporter.log("Assertion Passed: Box 1 is diplayed on the screen");
		driver.switchTo().frame("child");
		wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'answer']\");");
		Boolean box2 = wb.isEnabled();
		Assert.assertEquals(box2, check,"Assertion Failed: Box 2 is not found");
		Reporter.log("Assertion Passed: Box 2 is diplayed on the screen");
		driver.switchTo().parentFrame();
	
	}
	
	public void FrameErrorTest() throws IOException {
		
		wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'answer']\");");		
		String color3 = wb.getAttribute("class");
		driver.switchTo().frame("child");
		wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'answer']\");");
		String color4 = wb.getAttribute("class");
		driver.switchTo().parentFrame();
		js.executeScript("document.querySelector(\"a[onclick='gonext();'] \").click()");
		if(color3.equals(color4)) {
			
			Assert.assertEquals(driver.getTitle(), "Drag - Basic Course - T.A.T.O.C","Assertion Failed: Page title: Drag - Basic Course - T.A.T.O.C not found");
			Reporter.log("Assertion Passed: Color of Box 1 and Box 2 are same and Page gets redirects to the next Page");
		
		}
		else {
			
			Assert.assertEquals(driver.getTitle(), "Error - T.A.T.O.C","Assertion Failed: Page title: Error - T.A.T.O.C not found");
			Reporter.log("Assertion Passed: Color of Box 1 and Box 2 are not same and Page gets redirects to the Error Page");
		
		}
			
		
	}
	public void FrameColor() throws IOException {
		
		driver.navigate().back();
		
		driver.switchTo().frame("main");
		wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'answer']\");");
		String color = wb.getAttribute("class");
		String color2;
		do {
			driver.switchTo().frame("child");
			wb = (WebElement) js.executeScript("return document.querySelector(\"div[id = 'answer']\");");
			color2 = wb.getAttribute("class");
			driver.switchTo().parentFrame();
			if(color.equals(color2)) {
				
				js.executeScript("document.querySelector(\"a[onclick='gonext();'] \").click()");
				
			}
			else {
				
				js.executeScript("document.querySelector(\"a[onclick='reloadChildFrame();'] \").click()");
				
			}
			
		}while(!(color.equals(color2)));
		
		String title = driver.getTitle();
		Assert.assertEquals(title, "Drag - Basic Course - T.A.T.O.C","Assertion Failed: Page title: Drag - Basic Course - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Color of Box 1 and Box 2 are same and Page gets redirects to the next Page");
		
	}
}