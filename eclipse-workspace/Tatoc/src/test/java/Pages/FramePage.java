package Pages;

import java.io.IOException;
import Pages.Utilities.ElementSearch;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;


public class FramePage {
	
	WebDriver driver;
	
	public FramePage(WebDriver driver) {
		
		this.driver = driver;

	}
	ElementSearch es;
	public void BoxCheck() throws IOException {
		
		es = new ElementSearch(driver,"FrameElements");
		Boolean check = true;
		driver.switchTo().frame("main");
		Boolean box1 = es.findElement("Box1").isEnabled();
		Assert.assertEquals(box1, check,"Assertion Failed: Box 1 is not found");
		Reporter.log("Assertion Passed: Box 1 is diplayed on the screen");
		driver.switchTo().frame("child");
		Boolean box2 = es.findElement("Box2").isEnabled();
		Assert.assertEquals(box2, check,"Assertion Failed: Box 2 is not found");
		Reporter.log("Assertion Passed: Box 2 is diplayed on the screen");
		driver.switchTo().parentFrame();
	
	}
	
	public void FrameErrorTest() throws IOException {
		
		
		String color3 = es.findElement("Box1").getAttribute("class");
		driver.switchTo().frame("child");
		String color4 = es.findElement("Box2").getAttribute("class");
		driver.switchTo().parentFrame();
		es.findElement("Proceed").click();
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
		String color = es.findElement("Box1").getAttribute("class");
		String color2;
		do {
			driver.switchTo().frame("child");
			color2 = es.findElement("Box2").getAttribute("class");
			driver.switchTo().parentFrame();
			if(color.equals(color2)) {
				
				es.findElement("Proceed").click();
				
			}
			else {
				
				es.findElement("Repaint_Box2").click();
				
			}
			
		}while(!(color.equals(color2)));
		
		String title = driver.getTitle();
		Assert.assertEquals(title, "Drag - Basic Course - T.A.T.O.C","Assertion Failed: Page title: Drag - Basic Course - T.A.T.O.C not found");
		Reporter.log("Assertion Passed: Color of Box 1 and Box 2 are same and Page gets redirects to the next Page");
		
	}
}